/**
 * @author DeusBlu
 * @version 0.1_9
 *
 */
public class AttackUI 
{
	private Encounter encounter;
	private Party targets;
	private InputReader reader;
	private CombatAI ai;
	private Entity target;
	
	public AttackUI()
	{
		encounter = new Encounter();
		targets = new Party();
		reader = new InputReader();
		target = null;
		ai = new CombatAI();
	}
	
	public AttackUI(Encounter encounter)
	{
		setTargets(encounter.getMonsters());
		setEncounter(encounter);
		target = null;
		reader = new InputReader();
		ai = new CombatAI(encounter.getPlayers());
	}
	
	private void setTargets(Party targets)
	{
		if(targets != null){
			this.targets = targets;
		}
		else{
			throw new IllegalArgumentException("monsters was null");
		}
	}
	
	private void setEncounter(Encounter encounter)
	{
		if(encounter != null){
			this.encounter = encounter;
		}
		else{
			throw new IllegalArgumentException("encounter was null");
		}
	}
	
	private void printCrit(Entity attacker, Entity target, int damage)
	{
		System.out.println(attacker.getName()+" rolls "+encounter.getRoll());
		System.out.println(attacker.getName()+" crits "+target.getName()
				+" for "+damage+" damage!!");
		System.out.println();
	}
	
	private void printHit(Entity attacker, Entity target, int damage)
	{
		System.out.println(attacker.getName()+" rolls "+encounter.getRoll());
		System.out.println(attacker.getName()+" hits "+target.getName()
				+" for "+damage+" damage!!");
		System.out.println();
	}
	
	private void printMiss(Entity attacker, Entity target)
	{
		System.out.println(attacker.getName()+" rolls "+encounter.getRoll());
		System.out.println(attacker.getName()+" missed "+target.getName());
		System.out.println();
	}
	
	public boolean attack()
	{
		Entity player = encounter.getCurrentTurn();
		String selectTarget = null;
		while(selectTarget == null){
			targets.shortStatus();
			System.out.println("Who do you attack?");
			System.out.print("#/Name> ");
			selectTarget = selectTarget();
			if(selectTarget != null && selectTarget.equalsIgnoreCase("end")){
				return false;
			}
		}
		target = getTarget(selectTarget);
		encounter.setRoll();
    	if(encounter.successHit(encounter.getAttack(), target)){
			int damage = encounter.attack(encounter.getAttack(), player, target);
    		if(encounter.critHit()){
    			printCrit(player, target, damage);
    			reader.readString();
    			printDefeated(target);
    			return true;
    		}
    		else{
    			printHit(player, target, damage);
    			reader.readString();
    			printDefeated(target);
    			return true;
    		}
    	}
    	else{
    		printMiss(player, target);
    		reader.readString();
    		return true;
    	}
	}
	
	private String selectTarget()
	{
		String selectTarget = reader.readString();
		if(selectTarget.equalsIgnoreCase("end")){
			return selectTarget;
		}
		for(int i = 1; i <= targets.getPlayers().length; i++){
			if(selectTarget.equals(((Integer)i).toString()) && 
			targets.getPlayers()[i-1] != null && 
			!targets.getPlayers()[i-1].isDead()){
				return selectTarget;
			}
		}
		for(int i = 0; i < targets.getPlayers().length; i++){
			if(targets.getPlayers()[i] != null &&
					!targets.getPlayers()[i].isDead() &&
					targets.getPlayers()[i].getName().equalsIgnoreCase(selectTarget)){
				return selectTarget;
			}
		}
		System.out.println("That was not a valid target, please enter the name");
		System.out.println("or the # of the target or type end to exit");
		reader.readString();
		return null;
	}
	
	private Entity getTarget(String target)
	{
		for(int i = 1; i <= targets.getPlayers().length; i++){
			if(target.equals(((Integer)i).toString()) && 
			targets.getPlayers()[i-1] != null){
				return targets.getPlayers()[i-1];
			}
		}
		for(int i = 0; i < targets.getPlayers().length; i++){
			if(targets.getPlayers()[i] != null &&
					targets.getPlayers()[i].getName().equalsIgnoreCase(target)){
				return targets.getPlayers()[i];
			}
		}
		return null;
	}
	
	public void attackAI(int hitMod, Entity player)
	{
		Entity target = ai.attackWho();
		encounter.setRoll();
    	if(encounter.successHit(hitMod, target)){
			int damage = encounter.attack(hitMod, player, target);
    		if(encounter.critHit()){
    			printCrit(player, target, damage);
    			printDefeated(target);
    			reader.readString();
    			Console.clearConsole();
    		}
    		else{
    			printHit(player, target, damage);
    			printDefeated(target);
    			reader.readString();
    			Console.clearConsole();
    		}
    	}
    	else{
    		printMiss(player, target);
    	}
	}
	
	private void printDefeated(Entity target)
	{
    	if(target.isDead()){
    		System.out.println(target.getName() + 
    				" has been defeated!");
    		if(target.isDead()){
    			reader.readString();
    		}
            Console.clearConsole();
    	}
	}
}
