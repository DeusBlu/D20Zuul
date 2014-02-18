import java.util.Stack;

/**
 * This class is used to create players, it can make NPC's, PC's, and can be stored in the party for
 * controlling multiple characters
 * 
 * @author (DeusBlu) 
 * @version (0.1_8)
 */
public class Player extends Entity 
{
    private int age;
    private Proficiencies weapProf;
    private PlayerClass playerClass;

    /**
     * default constructor for testing players
     */
    public Player()
    {
    	super();
    	weapProf = new Proficiencies();
        setPlayerClass("fighter");
        setAge(Constant.MIN_AGE);
    }
    
    /**
     * the typical constructor for creating a player
     * @param name
     * @param str
     * @param dex
     * @param con
     * @param intel
     * @param wis
     * @param chr
     * @param numberDice
     * @param hpDie
     * @param fort
     * @param reflex
     * @param will
     * @param age
     */
	public Player(String name, 
				  int str, 
				  int dex, 
				  int con, 
				  int intel, 
				  int wis, 
				  int chr, 
				  int age, 
				  String playerClass) 
	{
		super(name, str, dex, con, intel, wis, chr, Constant.BASE_AC, 0, 0, 0, 1, 3, 0, 1);
		weapProf = new Proficiencies();
		setPlayerClass(playerClass);
		setAge(age);
		setHP();
		setMaxWeapProf(this.playerClass.getMaxWeapProf());
	}

	/**
	 * sets the age of the player throws exception if out of bounds
	 * @param age
	 */
	private void setAge(int age)
	{
		if(age >= Constant.MIN_AGE || age <= Constant.MAX_AGE){
			this.age = age;
		}
		else{
			throw new IllegalArgumentException("Age was out of bounds on " + getName());
		}
	}
	
	/**
	 * sets the class of the player eg "fighter"
	 * @param playerClass
	 */
	private void setPlayerClass(String playerClass)
	{
		if(playerClass.equalsIgnoreCase("fighter")){
			this.playerClass = new Fighter();
		}
	}
	
	/**
	 * sets the max value for a characters weapon proficiencies
	 * @param maxWeapProf
	 */
	private void setMaxWeapProf(int maxWeapProf)
	{
		weapProf.setMaxProf(maxWeapProf);
	}
	
	/**
	 * adds a weapon proficiency to the player
	 * @param prof
	 */
	public void learnWeapProf(String prof)
	{
		Proficiency proficiency = null;
		for(int i = 0; i < Constant.WEAP_PROF.length; i++){
			if(Constant.WEAP_PROF[i].equalsIgnoreCase(prof)){
				proficiency = new Proficiency(prof, 1);
				weapProf.learnProficiency(proficiency);
			}
		}
	}
	
	/**
	 * adds HP to the character, called during level up
	 */
	public void addHP()
	{
		DiceSet hpDice = new DiceSet(1, playerClass.getHpDie(), getStatMod("con"));
		int addHP = hpDice.getRoll();
		if(addHP > 0){
			super.addHP(addHP);
		}
		else{
			super.addHP(1);
		}
	}
	
	/**
	 * sets the starting HP of a character used once during creation
	 */
	public void setHP()
	{
		int startHP = playerClass.getHpDie() + getStatMod("con");
		if(startHP > 0){
			super.setHP(startHP);
		}
		else{
			super.setHP(1);
		}
	}
	
	/**
	 * returns the age of the player as an int
	 * @return age
	 */
	public int getAge()
	{
		return age;
	}
	
	/**
	 * prints the level and XP of the player
	 */
	public void showXP()
	{
		playerClass.showXP();
	}
	
	/**
	 * initiates the EquipUI menu for equiping items to the player
	 */
	public void equip()
	{
		new EquipUI(getGear(), getBackpack());
	}
	
	/**
	 * adds XP to the player
	 * @param earnedXP
	 */
	public void addXP(int earnedXP)
	{
		int lastLevel = playerClass.addXP(earnedXP);
		if(lastLevel < playerClass.getLevel()){
			int i = playerClass.getLevel() - lastLevel;
			for(; i > 0; i--){
				addHP();
				super.setMP(playerClass.getLevel());
			}
		}
	}
	
	/**
	 * prints the status of the player
	 */
	public void status()
	{
		System.out.println("Name: " + getName());
		System.out.println("Class: " + playerClass.getClassName());
		playerClass.printXP();
		System.out.println("Age: " + getAge());
		System.out.println("HP: " + getCurrentHP() + "/" + getMaxHP());
		System.out.println("MP: " + getCurrentMP() + "/" + getMaxMP());
		System.out.println();
		System.out.println("Stats");
		System.out.println("------");
		System.out.println("Str: " + getStat("str"));
		System.out.println("Dex: " + getStat("dex"));
		System.out.println("Con: " + getStat("con"));
		System.out.println("Int: " + getStat("intel"));
		System.out.println("Wis: " + getStat("wis"));
		System.out.println("Chr: " + getStat("chr"));
		System.out.println();
		System.out.println("Equipment:");
		getGear().showEquip();
	}
	
	/**
	 * gets the players bonus from proficiency with the current weapon
	 * @return to hit bonus with weapon as int from weapProf's
	 */
	public int getProfBonus()
	{
		int bonus = (weapProf.getProficiency(getGear().getWeapProf()));
		if(bonus == 0){
			return -4;
		}
		else{
			return bonus-1;
		}
	}
	
	/**
	 * returns the value of the resist
	 * @param resist
	 * @return int
	 */
	@Override
	public int getResist(String resist)
	{
		return super.getResist(resist) + playerClass.getResist(resist);
	}
	
	/**
	 * returns the melee attack bonus
	 */
	@Override
	public int getMeleeAttackMod()
	{
		return getGear().getAttackMod() + getStatMod("str") + getProfBonus();
	}
	
	/**
	 * returns a stack of attack bonus' for the combat to use, returns multiple values
	 * if there are multiple attacks for the person in a round
	 * @param attack
	 * @return attacks as Stack<Integer>
	 */
	public Stack<Integer> getAttacks()
	{
		int[] attack = playerClass.getAttacks();
		Stack<Integer> attacks = new Stack<Integer>();
		for(int i = attack.length-1; i >= 0; i--){
			attacks.push(attack[i] + getMeleeAttackMod());
		}
		return attacks;
	}
	
	public int getStartingProfs()
	{
		return playerClass.getStartingProfs();
	}
}
