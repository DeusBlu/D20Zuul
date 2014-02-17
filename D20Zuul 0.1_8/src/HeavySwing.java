import java.util.Stack;
/**
 * @author DeusBlu
 * @version 0.1_8
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
				"Heavy Swing");
		die = new Dice();
		setDescription();
	}
	
	/**
	 * returns the damage added by the ability
	 * @return dmgMod as int
	 */
	private int getDmgMod()
	{
		return (getRank()/2)+1;
	}
	
	/**
	 * sets the description so it can be updated easily
	 */
	public void setDescription()
	{
		setDescription("A heavy swing dealing +"+getDmgMod()+" damage and reducing hit by "+(getRank()-6));
	}

	@Override
	public Stack<Integer> use(Stack<Integer> attacks, Player player, Entity target) 
	{
		int roll = die.roll(1, 20);
		if(attacks.pop() + roll + getRank() - 6 > target.getArmor()){
			target.takeDamage(player.getDamage() + getDmgMod());
		}
		return attacks;
	}

	@Override
	public void passive(Player player, Entity target) {
		// TODO Auto-generated method stub
		
	}
}
