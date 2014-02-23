/**
 * The basic Fighter class for players
 * @author DeusBlu
 * @version 0.1_9
 *
 */
public class Fighter extends PlayerClass {
	
	@SuppressWarnings("unused")
	private int bonusFeat;
	
	/**
	 * Constructor for the PlayerClass Fighter
	 */
	public Fighter() 
	{
		super("Fighter", 10, 2, 0, 0, 2, 0, 5, 1, 5, 5);
		setBonusFeat();
	}
	
	public void setBonusFeat()
	{
		bonusFeat = 1;
	}
}
