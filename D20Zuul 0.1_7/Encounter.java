/**
 * Used for the battle, loads 2 parties (player and monster) and uses a combat object to simulate
 * the actual battle
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Encounter
{
    private Party players;
    private Party monsters;
    private Combat combat;
    private Parser parser;
    
    /**
     * default constructor for class Encounter
     */
    public Encounter()
    {
        Party players = new Party();
        MonParty monsters = new Party();
        Combat combat = new Combat();
        Parser parser = new Parser();
    }
    
    /**
     * the main combat method
     */
    private void fight()
    {
        boolean combatDone = false;
        while(!combatDone){
            Command command = parser.combatCommand();
            processCommand(command);
            combatDone = players.isDefeated();
        }
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")){
            printHelp();
        }
        else if (commandWord.equals("status")){
            printStatus();
        }
        else if (commandWord.equals("equip")){
            
        }
        else if (commandWord.equals("attack")){
            
        }
        else if(commandWord.equals("run")){
            
        }
    }
    
    /**
     * Created the encounter and loads the parties for the encounter/initializes the Combat object
     * @param Party - the players party
     * @param Party - the part of monsters to fight
     */
    public Encounter(Party players, Party monsters)
    {
        setPlayers(players);
        setMonsters(monsters);
        Combat combat = new Combat();
    }
    
    /**
     * loads the players party making sure that it is not a null object or an empty party
     * @param Party - the party of players
     */
    private void setPlayers(Party party)
    {
        if(party != null && !party.isEmpty()){
            players = party;
        }
    }
    
    /**
     * loads the monsters party making sure that it is not a null object or an empty party
     * @param Party - the monsters party
     */
    private void setMonsters(Party party)
    {
        if(party != null && !party.isEmpty()){
            monsters = party;
        }
    }
    
    /**
     * prints the help menu for combat
     */
    private void printHelp()
    {
        System.out.println("Combat Commands: ");
        System.out.println("attack run equip status");
    }
    
    /**
     * prints the status of the battle
     */
    private void printStatus()
    {
        System.out.println("Remaining Monsters:");
        System.out.println("-------------------------------");
        monsters.listAlive();
        System.out.println();
        System.out.println("Party Status:");
        System.out.println("-------------------------------");
        players.printStatus();
    }
}
