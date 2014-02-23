/**
 * Base item type of classes for the PlayerCharacter
 * @author DeusBlu
 * @version 0.1_9
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
	private int levelsPerAttack;
	private int levelsPerExtraAttack;
	private int baseAttack;
	
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
		setLevelsPerAttack(4);
		setLevelsPerExtraAttack(20);
		setBaseAttack(0);
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
					   int fortSave, 
					   int reflexSave,
					   int willSave, 
					   int skillPoints,
					   int levelsPerAttack,
					   int levelsPerExtraAttack,
					   int baseAttack,
					   int startingProfs,
					   int maxWeapProf)
	{
		xp = new XP();
		setClassName(name);
		setHpDie(hpDie);
		setFortSave(fortSave);
		setReflexSave(reflexSave);
		setWillSave(willSave);
		setSkillPoints(skillPoints);
		setStartingProfs(startingProfs);
		setMaxWeapProf(maxWeapProf);
		setLevelsPerAttack(levelsPerAttack);
		setLevelsPerExtraAttack(levelsPerExtraAttack);
		setBaseAttack(baseAttack);
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
	
	private void setBaseAttack(int baseAttack)
	{
		if(baseAttack >= 0){
			this.baseAttack = baseAttack;
		}
		else{
			throw new IllegalArgumentException("baseAttack cannot be negative");
		}
	}
	
	protected void setLevelsPerAttack(int levelsPerAttack)
	{
		if(levelsPerAttack >= 0){
			this.levelsPerAttack = levelsPerAttack;
		}
		else{
			throw new IllegalArgumentException("levelsPerAttack was out of bounds");
		}
	}
	
	protected void setLevelsPerExtraAttack(int levelsPerExtraAttack)
	{
		if(levelsPerExtraAttack >= 0){
			this.levelsPerExtraAttack = levelsPerExtraAttack;
		}
		else{
			throw new IllegalArgumentException("levelsPerExtraAttack was out of bounds");
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
	
	public int[] getAttacks()
	{
		int numAttacks = getNumAttacks(getLevel());
		int[] attacks = new int[numAttacks];
		for(int i = 0; i <= getLevel(); i++){
			if(levelsPerAttack == 0 || (i % levelsPerAttack == 0)){
				int x = 0;
				int attack = 0;
				while(x < i){
					attacks[attack]++;
					attack++;
					x = attack*levelsPerExtraAttack;
				}
			}
		}
		return attacks;
	}
	
	private int getNumAttacks(int level)
	{
		int numAttacks;
		if((level % levelsPerExtraAttack) == 0){
			numAttacks = level / levelsPerExtraAttack;
		}
		else{
			numAttacks = (level / levelsPerExtraAttack)+1;
		}
		return numAttacks;
	}
	
	public int getStartingProfs()
	{
		return startingProfs;
	}
	
	public int getMaxWeapProf()
	{
		return maxWeapProf;
	}
	
	public int getLevelsPerAttack()
	{
		return levelsPerAttack;
	}
	
	protected int getBaseAttack()
	{
		return baseAttack;
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
