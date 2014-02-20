/**
 * Base item type of classes for the PlayerCharacter
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class PlayerClass 
{
	private String className;
    private XP xp;
	private int hpDie;
	private int fortSave;
	private int reflexSave;
	private int willSave;
	private int skillPoints;
	private int startingProfs;
	private int maxWeapProf;
	
	/**
	 * default constructor for Class
	 */
	public PlayerClass()
	{
        xp = new XP();
		setClassName("");
		setHpDie(4);
		setFortSave(0);
		setReflexSave(0);
		setWillSave(0);
		setSkillPoints(0);
		setStartingProfs(1);
		setMaxWeapProf(1);
	}
	
	/**
	 * constructor used by most superclasses
	 * 
	 * @param name
	 * @param hpDie
	 * @param forSave
	 * @param reflexSave
	 * @param willSave
	 * @param skillPoints
	 * @param attack
	 */
	public PlayerClass(String name, 
					   int hpDie,
					   int forSave, 
					   int reflexSave,
					   int willSave, 
					   int skillPoints,
					   int attack,
					   int startingProfs,
					   int maxWeapProf)
	{
		xp = new XP();
		setClassName(name);
		setHpDie(hpDie);
		setFortSave(forSave);
		setReflexSave(reflexSave);
		setWillSave(willSave);
		setSkillPoints(skillPoints);
		setStartingProfs(startingProfs);
		setMaxWeapProf(maxWeapProf);
	}
	
	/**
	 * sets the name of the class eg "Warrior"
	 * @param ClassName
	 */
	private void setClassName(String ClassName)
	{
		if(ClassName != null && !ClassName.isEmpty()){
			this.className = ClassName;
		}
		else{
			throw new IllegalArgumentException("className was null or empty");
		}
	}
	
	/**
	 * sets the hpDie for the class
	 * @param hpDie
	 */
	private void setHpDie(int hpDie)
	{
		if(hpDie >= 4 && hpDie <= 12){
			this.hpDie = hpDie;
		}
		else{
			throw new IllegalArgumentException("hpDie out of range");
		}
	}
	
	/**
	 * sets the fort save bonus through the class
	 * @param fortSave
	 */
	private void setFortSave(int fortSave)
	{
		if(fortSave >= 0){
			this.fortSave = fortSave;
		}
		else{
			throw new IllegalArgumentException("fortSave was less than 0");
		}
	}
	
	/**
	 * sets the reflex save bonus through the class
	 * @param reflexSave
	 */
	private void setReflexSave(int reflexSave)
	{
		if(reflexSave >= 0){
			this.reflexSave = reflexSave;
		}
		else{
			throw new IllegalArgumentException("reflexSave was less than 0");
		}
	}
	
	/**
	 * sets the will save bonus through the class
	 * @param willSave
	 */
	private void setWillSave(int willSave)
	{
		if(willSave >= 0){
			this.willSave = willSave;
		}
		else{
			throw new IllegalArgumentException("willSave was less than 0");
		}
	}
	
	/**
	 * sets the skill points the player has to begin with
	 * @param skillPoints
	 */
	private void setSkillPoints(int skillPoints)
	{
		if(skillPoints >= 0){
			this.skillPoints = skillPoints;
		}
		else{
			throw new IllegalArgumentException("skill points was less than 0");
		}
	}
	
	private void setStartingProfs(int startingProfs)
	{
		if(startingProfs >= 1){
			this.startingProfs = startingProfs;
		}
		else{
			throw new IllegalArgumentException("startingProfs was 0 or less");
		}
	}
	
	/**
	 * sets the maximum for weapon proficiences based on the class
	 * @param maxWeapProf
	 */
	private void setMaxWeapProf(int maxWeapProf)
	{
		if(maxWeapProf >= 1){
			this.maxWeapProf = maxWeapProf;
		}
		else{
			throw new IllegalArgumentException("maxWeapProf as out of bounds");
		}
	}

	/**
	 * returns the className as a string
	 * @return className
	 */
	public String getClassName()
	{
		return className;
	}
	
	/**
	 * returns the sides of the classes HP Die
	 * @return hpDie
	 */
	public int getHpDie()
	{
		return hpDie;
	}
	
	/**
	 * returns the fort save bonus from the class
	 * @return fortSave
	 */
	public int getFortSave()
	{
		return fortSave;
	}
	
	/**
	 * returns the reflex save bonus from the class
	 * @return reflexSave
	 */
	public int getReflexSave()
	{
		return reflexSave;
	}
	
	/**
	 * returns he will save bonus from the class
	 * @return willSave
	 */
	public int getWillSave()
	{
		return willSave;
	}
	
	/**
	 * returns the skill points the player has to start with
	 * @return skillPoints
	 */
	public int getSkillPoints()
	{
		return skillPoints;
	}
	
	/**
	 * returns an array of attack bonus' for the combat to use, returns multiple values
	 * if there are multiple attacks for the person in a round
	 * @param attack
	 * @return attacks as Stack<Integer>
	 */
	public abstract int[] getAttacks();
	
	public int getStartingProfs()
	{
		return startingProfs;
	}
	
	public int getMaxWeapProf()
	{
		return maxWeapProf;
	}
	
	/**
	 * prints the level and XP of the player
	 */
	public void showXP()
	{
		xp.printXP();
	}
	
	/**
	 * adds XP to the player
	 * @param earnedXP
	 * @return the level before xp added
	 */
	public int addXP(int earnedXP)
	{
		int level = xp.getLevel();
		if(xp.addXP(earnedXP)){
			
		}
		return level;
	}
	
	/**
	 * returns the level of the class as an int
	 * @return level as an int
	 */
	public int getLevel()
	{
		return xp.getLevel();
	}
	
	public void printXP()
	{
		xp.printXP();
	}
	
	/**
	 * returns the requested resist
	 * @param resist
	 * @return fort as int
	 */
	public int getResist(String resist)
	{
		if(resist.equalsIgnoreCase("fort")){
			return fortSave;
		}
		if(resist.equalsIgnoreCase("reflex")){
			return reflexSave;
		}
		if(resist.equalsIgnoreCase("will")){
			return willSave;
		}
		throw new IllegalArgumentException("Resist not recognized");
	}
}
