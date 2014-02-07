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
    public PlayerCharacter()
    {
    	super();
        xp = new XP();
        setAge(MIN_AGE);
        setXP();
    }

	/**
	 * @param name
	 * @param str
	 * @param dex
	 * @param con
	 * @param intel
	 * @param wis
	 * @param chr
	 * @param armor
	 * @param numberDice
	 * @param hpDie
	 * @param fort
	 * @param reflex
	 * @param will
	 */
	public Player(String name, int str, int dex, int con, int intel, int wis,
			int chr, int armor, int numberDice, int hpDie, int fort,
			int reflex, int will) {
		super(name, str, dex, con, intel, wis, chr, armor, numberDice, hpDie,
				fort, reflex, will);
		// TODO Auto-generated constructor stub
	}

}
