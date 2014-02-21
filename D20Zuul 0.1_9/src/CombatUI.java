/**
 * determines who the monster should attack
 * @author DeusBlu
 * @version 0.1_8
 *
 */

public class CombatUI
{
	private Encounter encounter;
    private Party players;
    private Party monsters;
	private InputReader reader;
    private Parser parser;
	
	/**
	 * default constructor for the CombatUI
	 */
	public CombatUI()
	{
		encounter = new Encounter();
		reader = new InputReader();
		parser = new Parser();
		setPlayers(new Party());
		setMonsters(new Party());
		
	}
	
	/**
	 * constructor for useable combatUI
	 * @param monsters
	 */
	public CombatUI(Party players, Party monsters)
	{
		encounter = new Encounter(players, monsters);
		reader = new InputReader();
		parser = new Parser();
		setPlayers(players);
		setMonsters(monsters);
		
	}
    
    /**
     * the main combat method
     */
    public boolean fight()
    {
    	printEncounter();
        printPlayers();
        int round = 1;
        while(!players.isDefeated() && !monsters.isDefeated()){
        	System.out.println("Round "+round+"!\n");
        	round++;
        	reader.readString();
            Console.clearConsole();
        	encounter.setInitiative();
        	while(!encounter.initEmpty() && !players.isDefeated() 
        			&& !monsters.isDefeated()){
            	encounter.setCurrentTurn();
        		if(encounter.getCurrentTurn() != null){
    				encounter.setAttacks(encounter.getCurrentTurn().getAttacks());
        			while(!encounter.attacksEmpty()  && !players.isDefeated() 
        					&& !monsters.isDefeated()){
        				takeTurn(encounter.getCurrentTurn());
        			}
        		}
        	}
        }
        return finishCombat();
    }
    
    private void printEncounter()
    {
        System.out.println("Enemies Encountered!\n");
        System.out.println("You see before you "+monsters.getNumberPlayers()+" creatures");
        monsters.shortStatus();
        reader.readString();
        Console.clearConsole();
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
            printMonsters();
            printPlayers();
        }
        else if (commandWord.equals("equip")){
        	EquipUI equip = new EquipUI(player);
            turnDone = equip.equipUI();
        }
        else if (commandWord.equals("item")){
        	ItemUI useItem = new ItemUI(encounter);
        	turnDone = useItem.useItem();
        }
        else if (commandWord.equals("attack")){
        	AttackUI attack = new AttackUI(encounter);
        	turnDone = attack.attack();
        }
        else if(commandWord.equals("ability")){
        	AbilityUI ability = new AbilityUI(encounter);
        	turnDone = ability.useAbility();
        }
        else if(commandWord.equals("run")){
            turnDone = true;
        }
        if(turnDone){
        	if(!encounter.attacksEmpty()){
        		encounter.getAttacks().pop();
        	}
        }
        return turnDone;
    }
    
    /**
     * makes the entity take turn
     * @param takingTurn
     */
    private void takeTurn(Entity takingTurn)
    {
    	if(takingTurn != null && !players.isDefeated() && !monsters.isDefeated()){
    		System.out.println(takingTurn.getName() + " has "+encounter.getAttacks().size()+" attacks left");
    		if(takingTurn instanceof Player){
    			boolean turnDone = false;
    			while(!turnDone && !players.isDefeated() && !monsters.isDefeated()){
    				System.out.println("What does "+takingTurn.getName()+" do?");
    				Command command = parser.combatCommand();
    				turnDone = processCommand(command, ((Player)takingTurn));
    			}
    		}
    		else{
            	AttackUI attack = new AttackUI(encounter);
            	attack.attackUI(encounter.getAttacks().pop(), takingTurn);
            	reader.readString();
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
        	System.out.print(CombatCommands.validCommands[i]+" ");
        }
        System.out.println();
    }
    
    /**
     * prints the status of the battle
     */
    private void printMonsters()
    {
        System.out.println("Remaining Monsters:");
        System.out.println("-------------------------------");
        monsters.shortStatus();
        System.out.println();
    }
    
    private void printPlayers()
    {
        System.out.println("Party Status:");
        System.out.println("-------------------------------");
        players.shortStatus();
        System.out.println();
    }
	
	private boolean finishCombat()
	{
		if(players.isDefeated()){
			System.out.println("You have been defeated");
			return false;
		}
		else if(monsters.isDefeated()){
			int totalXPGained = encounter.finishCombat();
			for(int i = 0; i < players.getPlayers().length; i++){
				if(!players.getPlayers()[i].isDead()){
					System.out.println(players.getPlayers()[i].getName()+" gained "
							+totalXPGained+" exp");
				}
			}
		}
		return true;
	}
}
