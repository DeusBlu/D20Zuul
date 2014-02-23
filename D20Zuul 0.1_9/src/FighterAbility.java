/**
 * Fighter ability objects
 * @author DeusBlu
 * @version 0.1_9
 *
 */
public abstract class FighterAbility extends Ability 
{
	private String profType;

	/**
	 * 
	 */
	public FighterAbility() 
	{
		super();
		setProfType("Unarmed");
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
						String profType,
						String description) 
	{
		super(name, 5, dmgDice, dmgDie, dmgMod, offensive, active, "fighter", description);
		setProfType(profType);
	}
	
	/**
	 * activates the skill when used
	 * @param attacks
	 * @param player
	 * @param target
	 * @return Stack<Integer> the remaining attacks of the player
	 */
	public abstract int use(Entity target, Encounter encounter);
	
	/**
	 * the passive effect of the skill
	 * @param player
	 * @param target
	 */
	public abstract void passive(Player player, Entity target);
	
	private void setProfType(String profType)
	{
		for(int i = 0; i < Constant.WEAP_PROF.length; i++){
			if(Constant.WEAP_PROF[i].equals(profType)){
				this.profType = profType;
			}
		}
		if(this.profType == null){
			throw new IllegalArgumentException(profType+" was an unrecognized weapon prof");
		}
	}
	
	public String getProfType()
	{
		return profType;
	}
	
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
