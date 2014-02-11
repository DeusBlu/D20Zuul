import java.util.Stack;
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
    private Party opponents;
    private Combat combat;
    private Parser parser;
    private Stack<Entity> initiative;
    /**
     * default constructor for class Encounter
     */
    public Encounter()
    {
        players = new Party();
        opponents = new Party();
        combat = new Combat();
        parser = new Parser();
        initiative = new Stack<Entity>();
    }
    
    /**
     * Created the encounter and loads the parties for the encounter/initializes the Combat object
     * @param Party - the players party
     * @param Party - the part of monsters to fight
     */
    public Encounter(Party players, Party opponents)
    {
        setPlayers(players);
        setMonsters(opponents);
        combat = new Combat();
        parser = new Parser();
        initiative = new Stack<Entity>();
    }
    
    /**
     * the main combat method
     */
    public void fight()
    {
        boolean combatDone = false;
        System.out.print("Enemies Encountered!");
        while(!combatDone){
            Command command = parser.combatCommand();
            processCommand(command);
        }
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        while(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            command = parser.combatCommand();
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
            opponents = party;
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
        opponents.shortStatus();;
        System.out.println();
        System.out.println("Party Status:");
        System.out.println("-------------------------------");
        players.shortStatus();
    }
}
