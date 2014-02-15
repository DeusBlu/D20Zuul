import java.util.HashMap;
/**
 * This class is the framework for creating skills
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class Skill 
{
	private String name;
	private int rank;
	private int maxRank;
	private DiceSet effectValue;
	private boolean offensive;
	private boolean active;
	private HashMap<String, Integer> requirements;
	private String validClass;
	private String description;
	
	/**
	 * default constructor for Skill objects
	 */
	public Skill()
	{
		setName("unknown");
		setRank(1);
		setMaxRank(1);
		setEffectValue(0, 0, 0);
		setOffensive(false);
		setActive(false);
		setValidClass("unknown");
		setDescription("unknown skill");
		requirements = new HashMap<String, Integer>();
	}
	
	/**
	 * constructor for useable objects of type item
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
	public Skill(String name, 
				 int maxRank,
				 int dmgDice,
				 int dmgDie,
				 int dmgMod,
				 boolean offensive,
				 boolean active,
				 String validClass,
				 String description)
	{
		requirements = new HashMap<String, Integer>();
		setName(name);
		setRank(1);
		setMaxRank(maxRank);
		setEffectValue(dmgDice, dmgDie, dmgMod);
		setOffensive(offensive);
		setActive(active);
		setValidClass(validClass);
		setDescription(description);
	}
	
	/**
	 * sets the name value, throws exception if null or empty
	 * @param name
	 */
	private void setName(String name)
	{
		if(name != null && !name.isEmpty()){
			this.name = name;
		}
		else{
			throw new IllegalArgumentException("item name was null or empty");
		}
	}
		
	/**
	 * set the current rank of the skill usually set to 1
	 * @param rank
	 */
	private void setRank(int rank)
	{
		if(rank >= 1){
			this.rank = rank;
		}
		else{
			throw new IllegalArgumentException(getName()+" rank was 0 or less");
		}
	}
	
	/**
	 * sets the max rank of the skill should be set by class skill subclass
	 * @param maxRank
	 */
	private void setMaxRank(int maxRank)
	{
		if(maxRank >= 1){
			this.maxRank = maxRank;
		}
		else{
			throw new IllegalArgumentException(getName()+" max rank was 0 or less");
		}
	}
	
	/**
	 * sets the effectValue of the skill to a set of dice
	 * @param dmgDice
	 * @param dmgDie
	 * @param dmgMod
	 */
	private void setEffectValue(int dmgDice, int dmgDie, int dmgMod)
	{
		if(dmgDice >= 0 && dmgDie >= 0 && dmgMod >= 0){
			effectValue = new DiceSet(dmgDice, dmgDie, dmgMod);
		}
		else if(dmgDice < 0){
			throw new IllegalArgumentException(getName()+" dmgDice was less than 0");
		}
		else if(dmgDie < 0){
			throw new IllegalArgumentException(getName()+" dmgDie was less than 0");
		}
		else if(dmgMod < 0){
			throw new IllegalArgumentException(getName()+" dmgMod was less than 0");
		}
	}
	
	/**
	 * sets if the ability is offensive of defensive
	 * @param offensive
	 */
	private void setOffensive(boolean offensive)
	{
		this.offensive = offensive;
	}
	
	/**
	 * sets if the skill is active of passive
	 * @param active
	 */
	private void setActive(boolean active)
	{
		this.active = active;
	}
	
	/**
	 * sets the class that can use the skill, should be set by the skill
	 * superclass eg FighterSkill, ClericSkill
	 * @param validClass
	 */
	private void setValidClass(String validClass)
	{
		if(validClass != null && !validClass.isEmpty()){
			this.validClass = validClass;
		}
		else{
			throw new IllegalArgumentException(getName()+" validClass string was empty or null");
		}
	}
	
	private void setDescription(String description)
	{
		if(description != null && !description.isEmpty()){
			this.description = description;
		}
		else{
			throw new IllegalArgumentException(getName()+" description was null or empty");
		}
	}
	
	/**
	 * sets the requirements to learn the skill eg 16 str
	 * @param stat
	 * @param value
	 */
	protected void setRequirements(String stat, int value)
	{
		requirements.put(stat, value);
	}
	
	/**
	 * returns the name of the skill as a string
	 * @return String name
	 */
	public String getName()	
	{
		return name;
	}
	
	/**
	 * returns the current rank of the skill
	 * @return int current rank
	 */
	public int getRank()
	{
		return rank;
	}
	
	/**
	 * returns the maximum rank of the skill
	 * @return int max rank
	 */
	public int getMaxRank()
	{
		return maxRank;
	}
	
	/**
	 * returns a roll of the effectValue DiceSet
	 * @return int effect roll
	 */
	public int getEffectValue()
	{
		return effectValue.getRoll();
	}
	
	/**
	 * returns the effect value as a string eg. 1d6+4
	 * @return String effectValue
	 */
	public String getEffectValueString()
	{
		return ""+effectValue.getNumber()+"d"+
				effectValue.getSides()+"+"+effectValue.getModifier();
	}
	
	/**
	 * returns boolean true if ability is offensive
	 * @return true if offensive
	 */
	public boolean getOffensive()
	{
		return offensive;
	}
	/**
	 * returns boolean true if ability is activated
	 * @return true if activated
	 */
	public boolean getActive()
	{
		return active;
	}
	
	/**
	 * returns the class that can use the skill as a String
	 * @return PlayerClass that can use as String
	 */
	public String getValidClass()
	{
		return validClass;
	}
	
	/**
	 * returns a HashMap of all the requirements of the skill
	 * eg. Str 10, Dex 12
	 * @return HashMap of requirements <String, int>
	 */
	public HashMap<String, Integer> getRequirements()
	{
		return requirements;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * the method to use an active skill prints cannot be used if 
	 * not activated
	 * @param target
	 */
	public abstract void use(Entity target);
	
	/**
	 * applies the static effects of the skill to the player
	 * @param player
	 */
	public abstract void passive(Player player);
}
