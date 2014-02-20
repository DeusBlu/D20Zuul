import java.util.Stack;
/**
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class SwordAbility extends FighterAbility 
{

	/**
	 * 
	 */
	public SwordAbility() 
	{
		super();
	}

	/**
	 * @param name
	 * @param dmgDice
	 * @param dmgDie
	 * @param dmgMod
	 * @param offensive
	 * @param active
	 * @param description
	 */
	public SwordAbility(String name, 
						int dmgDice, 
						int dmgDie, 
						int dmgMod,
						boolean offensive, 
						boolean active, 
						String profType,
						String description) 
	{
		super(name, dmgDice, dmgDie, dmgMod, offensive, active, profType, description);
		
	}
	
	/**
	 * activates the skill when used
	 * @param attacks
	 * @param player
	 * @param target
	 * @return Stack<Integer> the remaining attacks of the player
	 */
	public abstract int use(Stack<Integer> attacks, 
								Player player, Entity target);
	
	/**
	 * the passive effect of the skill
	 * @param player
	 * @param target
	 */
	public abstract void passive(Player player, Entity target);
	
	/**
	 * returns the hit modifier for the skill
	 * @return hit mod as an int
	 */
	public abstract int getHitMod();
	
	/**
	 * returns the damage mod for the skill
	 * @return damage modifier as an int
	 */
	public abstract int getDmgMod();
}
