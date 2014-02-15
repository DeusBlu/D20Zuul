/**
 * Fighter skill objects
 * @author desmond.jenkins
 *
 */
public abstract class FighterSkill extends Skill {

	/**
	 * 
	 */
	public FighterSkill() {
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
	public FighterSkill(String name, 
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

	/* (non-Javadoc)
	 * @see Skill#use(Entity)
	 */
	@Override
	public abstract void use(Entity target);

	/* (non-Javadoc)
	 * @see Skill#passive(Player)
	 */
	@Override
	public abstract void passive(Player player);

}
