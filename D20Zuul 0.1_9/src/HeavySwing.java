import java.util.Stack;
/**
 * @author DeusBlu
 * @version 0.1_9
 *
 */
public class HeavySwing extends SwordAbility 
{
	private Dice die;
	/**
	 * constructor for the skill Heavy Swing
	 */
	public HeavySwing() {
		super("Heavy Swing", 
				0, 
				0, 
				0, 
				true, 
				true, 
				"Sword",
				"Heavy Swing");
		die = new Dice();
		setDescription();
	}
	
	/**
	 * returns the damage added by the ability
	 * @return dmgMod as int
	 */
	public int getDmgMod()
	{
		return (getRank()/2)+1;
	}
	
	public int getHitMod()
	{
		return (getRank()-6);
	}
	
	/**
	 * sets the description so it can be updated easily
	 */
	public void setDescription()
	{
		setDescription("A heavy swing dealing +"+getDmgMod()+
				" damage and reducing hit by "+(getRank()-6));
	}

	@Override
	public int use(Entity target, Encounter encounter) 
	{
		Player player = (Player)encounter.getCurrentTurn();
		Stack<Integer> attacks = encounter.getAttacks();
		int damage = player.getDamage() + getDmgMod();
		int roll = die.roll(1, 20);
		if(attacks.peek() + roll + getHitMod() > target.getArmor()){
			target.takeDamage(damage);
		}
		return damage;
	}

	@Override
	public void passive(Player player, Entity target) {
		
	}
}
