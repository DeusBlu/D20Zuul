import java.util.Stack;
/**
 * Fighter ability objects
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class FighterAbility extends Ability 
{

	/**
	 * 
	 */
	public FighterAbility() 
	{
		super();
	}

	/**
	 * @param name
	 * @param maxRank
	 * @param dmgDice
	 * @param dmgDie
	 * @param dmgMod
	 * @param offensive
	 * @param active
	 * @param validClass
	 * @param description
	 */
	public FighterAbility(String name, 
						int dmgDice, 
						int dmgDie,
						int dmgMod, 
						boolean offensive, 
						boolean active,
						String description) 
	{
		super(name, 5, dmgDice, dmgDie, dmgMod, offensive, active, "fighter", description);
		// TODO Auto-generated constructor stub
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

}
