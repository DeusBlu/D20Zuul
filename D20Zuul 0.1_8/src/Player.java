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
		if(age < MIN_AGE || age > MAX_AGE){
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
	
	public void addXP(int earnedXP)
	{
		if(xp.addXP(earnedXP)){
			System.out.println(getName() + " has reached level " + 
		    xp.getLevel() + "!");
		}
	}
}