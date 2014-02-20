/**
 * 
 */

/**
 * @author desmond.jenkins
 *
 */
public class AttackUI 
{
	private Encounter encounter;
	private Party targets;
	private InputReader reader;
	private CombatAI ai;
	
	public AttackUI()
	{
		encounter = new Encounter();
		targets = new Party();
		reader = new InputReader();
		ai = new CombatAI();
	}
	
	public AttackUI(Party players,
					Party monsters, 
					Encounter encounter)
	{
		setTargets(monsters);
		setEncounter(encounter);
		reader = new InputReader();
		ai = new CombatAI(players);
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
	
	/**
	 * returns the target the user has selected to attack
	 * @return entity to attack
	 */
	private Entity getTarget()
	{
		Entity target = null;
		int input = 0;
		while(target == null){
			targets.shortStatus();
			System.out.println("Monster to attack?");
			System.out.print("#> ");
			input = reader.readInt();
			if(input-1 >= 0 && input-1 < targets.getPlayers().length && 
					targets.getPlayers()[input-1] != null){
				if(!targets.getPlayers()[input-1].isDead()){
					target = targets.getPlayers()[input-1];
				}
				else if(targets.getPlayers()[input-1].isDead()){
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
	
	public boolean attack(int hitMod, Entity player)
	{
		Entity target = getTarget();
    	if(encounter.successHit(hitMod, target)){
			int damage = encounter.attack(hitMod, player, target);
    		if(encounter.critHit()){
    			printCrit(player, target, damage);
    			reader.readString();
    			printDefeated(target);
    			reader.readString();
    			return true;
    		}
    		else{
    			printHit(player, target, damage);
    			reader.readString();
    			printDefeated(target);
    			reader.readString();
    			return true;
    		}
    	}
    	else{
    		printMiss(player, target);
    		return true;
    	}
	}
	
	public void attackUI(int hitMod, Entity player)
	{
		Entity target = ai.attackWho();
    	if(encounter.successHit(hitMod, target)){
			int damage = encounter.attack(hitMod, player, target);
    		if(encounter.critHit()){
    			printCrit(player, target, damage);
    			reader.readString();
    			printDefeated(target);
    			reader.readString();
    		}
    		else{
    			printHit(player, target, damage);
    			reader.readString();
    			printDefeated(target);
    			reader.readString();
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
    		reader.readString();
            Console.clearConsole();
    	}
	}
}
