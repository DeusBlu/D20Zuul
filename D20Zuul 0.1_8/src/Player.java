/**
 * This class is used to create players, it can make NPC's, PC's, and can be stored in the party for
 * controlling multiple characters
 * 
 * @author (DeusBlu) 
 * @version (0.1_8)
 */
public class Player extends Entity 
{
	private static final int BASE_AC = 10;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 75;
    private int age;
    private XP xp;

    /**
     * default constructor for testing players
     */
    public Player()
    {
    	super();
        xp = new XP();
        setAge(MIN_AGE);
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
	public Player(String name, int str, int dex, int con, 
				  int intel, int wis, int chr,  int numberDice, 
				  int hpDie, int fort, int reflex,
				  int will, int age) {
		super(name, str, dex, con, 
			  intel, wis, chr, BASE_AC, 
			  numberDice, hpDie, fort, 
			  reflex, will, 1, 3, 0);
		xp = new XP();
		setAge(age);
	}

	/**
	 * sets the age of the player throws exception if out of bounds
	 * @param age
	 */
	public void setAge(int age)
	{
		if(age >= MIN_AGE || age <= MAX_AGE){
			this.age = age;
		}
		else{
			throw new IllegalArgumentException("Age was out of bounds on " + getName());
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
		xp.printXP();
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
		if(xp.addXP(earnedXP)){
			System.out.println(getName() + " has reached level " + 
		    xp.getLevel() + "!");
		}
	}
	
	/**
	 * prints the status of the player
	 */
	public void status()
	{
		System.out.println("Name: " + getName());
		System.out.println("Age: " + getAge());
		System.out.println("HP: " + getCurrentHP() + "/" + getMaxHP());
		System.out.println("MP: " + getCurrentMP() + "/" + getMaxMP());
		System.out.println("Level: " + xp.getLevel());
		System.out.print("XP: ");
		xp.printXP();
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
}
