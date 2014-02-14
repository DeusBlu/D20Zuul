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
    private CombatAI ai;
    private CombatUI ui;
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
        ai = new CombatAI();
        ui = new CombatUI();
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
        ai = new CombatAI(players);
        ui = new CombatUI(opponents);
    }
    
    /**
     * the main combat method
     */
    public void fight()
    {
        boolean combatDone = false;
        System.out.println("Enemies Encountered!");
        printStatus();
        int round = 1;
        while(!combatDone){
        	System.out.println("Round " + round + "!");
        	initiative = new Initiative().pcInit(players, opponents);
        	while(!initiative.isEmpty()){
        		if(initiative.peek().isDead()){
        			initiative.pop();
        		}
        		else{
        			if(!players.isDefeated() && !opponents.isDefeated())
        			takeTurn(initiative.pop());
        		}
        	}
        	if(players.isDefeated() || opponents.isDefeated()){
        		combatDone = true;
        	}
        	round++;
        }
        finishCombat();
    }
    
    /**
     * makes the entity take turn
     * @param takingTurn
     */
    private void takeTurn(Entity takingTurn)
    {
    	System.out.println(takingTurn.getName() + "'s turn");
    	if(takingTurn instanceof Player){
    		boolean turnDone = false;
    		while(!turnDone){
    			System.out.println("What do you do?");
    			Command command = parser.combatCommand();
    			turnDone = processCommand(command, ((Player)takingTurn));
    		}
    	}
    	else{
    		combat.attack(takingTurn, ai.attackWho());
    	}
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command, Player player) 
    {
    	boolean turnDone = false;
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
        	player.equip();
            turnDone = true;
        }
        else if (commandWord.equals("item")){
        	player.useItem(players, opponents);
        	turnDone = true;
        }
        else if (commandWord.equals("attack")){
        	Entity target = ui.getTarget();
        	combat.attack(player, target);
        	if(target.isDead()){
        		System.out.println(target.getName() + " has been defeated!");
        	}
            turnDone = true;
        }
        else if(commandWord.equals("run")){
            turnDone = true;
        }
        return turnDone;
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
    
    private void finishCombat()
    {
		System.out.println();
    	if(opponents.isDefeated()){
    		System.out.println("You have won!");
    		int earnedXP = 0;
    		for(int i = 0; i < opponents.getPlayers().length; i++){
    			if(opponents.getPlayers()[i] instanceof Creature){
    				Creature defeated = ((Creature)opponents.getPlayers()[i]);
    				earnedXP += defeated.getXpValue();
    			}
    		}
    		for(int i = 0; i < players.getPlayers().length; i++){
    			if(players.getPlayers()[i] instanceof Player){
    				Player player = ((Player)players.getPlayers()[i]);
    				if(!player.isDead()){
    					System.out.println(player.getName() + " earned " + earnedXP + "xp");
    					((Player)players.getPlayers()[i]).addXP(earnedXP);
    				}
    			}
    		}
    	}
    }
    
    /**
     * prints the help menu for combat
     */
    private void printHelp()
    {
        System.out.println("Combat Commands: ");
        for(int i = 0; i < CombatCommands.validCommands.length; i++){
        	System.out.println(CombatCommands.validCommands[i]+" ");
        }
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
