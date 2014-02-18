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
    private CombatAI ai;
	
	/**
	 * default constructor for the CombatUI
	 */
	public CombatUI()
	{
		encounter = new Encounter();
		reader = new InputReader();
		ai = new CombatAI();
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
		ai = new CombatAI(players);
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
        	Entity turn = new Entity();
        	while(!encounter.initEmpty() && !players.isDefeated() 
        			&& !monsters.isDefeated()){
        		turn = encounter.getNextTurn();
        		if(turn != null){
        			takeTurn(turn);
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
        	EquipUI equip = new EquipUI(player.getGear(), player.getBackpack());
            turnDone = equip.equipUI();
        }
        else if (commandWord.equals("item")){
        	ItemUI useItem = new ItemUI(player.getBackpack(), players, monsters);
        	turnDone = useItem.useItem();
        }
        else if (commandWord.equals("attack")){
        	attack(player, getTarget());
            turnDone = true;
        }
        else if(commandWord.equals("run")){
            turnDone = true;
        }
        return turnDone;
    }
    
    /**
     * makes the entity take turn
     * @param takingTurn
     */
    private void takeTurn(Entity takingTurn)
    {
    	if(takingTurn != null){
    		System.out.println(takingTurn.getName() + "'s turn");
    		if(takingTurn instanceof Player){
    			boolean turnDone = false;
    			while(!turnDone){
    				System.out.println("What does "+takingTurn.getName()+" do?");
    				Command command = parser.combatCommand();
    				turnDone = processCommand(command, ((Player)takingTurn));
    			}
    		}
    		else{
    			attack(takingTurn, ai.attackWho());
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
	
	/**
	 * returns the target the user has selected to attack
	 * @return entity to attack
	 */
	private Entity getTarget()
	{
		Entity target = null;
		int input = 0;
		while(target == null){
			monsters.shortStatus();
			System.out.println("Monster to attack?");
			System.out.print("#> ");
			input = reader.readInt();
			if(input-1 >= 0 && input-1 < monsters.getPlayers().length && 
					monsters.getPlayers()[input-1] != null){
				if(!monsters.getPlayers()[input-1].isDead()){
					target = monsters.getPlayers()[input-1];
				}
				else if(monsters.getPlayers()[input-1].isDead()){
					System.out.println("That target is dead");
				}
			}
			else{
				System.out.println("That is not a valid target");
			}
		}
		return target;
	}
	
	private void printCrit(Entity attacker, Entity target, int damage)
	{
		System.out.println(attacker.getName()+" rolls "+encounter.getRoll());
		System.out.println(attacker.getName()+" crits "+target.getName()
				+" for "+damage+" damage!!");
		reader.readString();
        Console.clearConsole();
	}
	
	private void printHit(Entity attacker, Entity target, int damage)
	{
		System.out.println(attacker.getName()+" rolls "+encounter.getRoll());
		System.out.println(attacker.getName()+" hits "+target.getName()
				+" for "+damage+" damage!!");
		reader.readString();
        Console.clearConsole();
	}
	
	private void printMiss(Entity attacker, Entity target)
	{
		System.out.println(attacker.getName()+" rolls "+encounter.getRoll());
		System.out.println(attacker.getName()+" missed "+target.getName());
		reader.readString();
        Console.clearConsole();
	}
	
	private void attack(Entity player, Entity target)
	{
    	if(encounter.successHit(player, target)){
			int damage = encounter.attack(player, target);
    		if(encounter.critHit()){
    			printCrit(player, target, damage);
    		}
    		else{
    			printHit(player, target, damage);
    		}
    	}
    	else{
    		printMiss(player, target);
    	}
    	if(target.isDead()){
    		System.out.println(target.getName() + 
    				" has been defeated!");
    		reader.readString();
            Console.clearConsole();
    	}
	}
	
	private boolean finishCombat()
	{
		if(players.isDefeated()){
			System.out.println("You have been defeated");
			return false;
		}
		else if(monsters.isDefeated()){
			int expGained = encounter.finishCombat();
			Entity[] victors = players.getPlayers();
			for(int i = 0; i < victors.length; i++){
				if(!victors[i].isDead()){
					System.out.println(victors[i].getName()+" gained "
							+expGained+" exp");
				}
			}
		}
		return true;
	}
}
