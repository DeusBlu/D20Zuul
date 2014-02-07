/**
 * This class keeps track of the health of entites
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class MP {

	private int[] mp;
	/**
	 * default constructor for HP
	 */
	public MP()
	{
		mp = new int[2];
		setMP(0, 4);
	}
	
	/**
	 * constructor for HP
	 * @param int - maxHP
	 */
	public MP(int wisMod, int startingMaxMP)
	{
		mp = new int[2];
		setMP(wisMod, startingMaxMP);
	}
	
	/**
	 * initializes the HP Die
	 * @param Dice mpDie
	 * @param int conMod
	 * @param int startingMaxHP
	 */
	private void setMP(int wisMod, int startingMaxMP)
	{
        mp[0] = wisMod + startingMaxMP;
        mp[1] = wisMod = startingMaxMP;
        
	}
	
	/**
	 * spends the mp
	 * @param damage
	 */
	public void spend(int spent)
	{
		if(spent <= mp[0]){
			mp[0] -= spent;
		}
		else{
			mp[0] = 0;
		}
	}
	
	/**
	 * restores the mp
	 * @param healing
	 */
	public void restore(int restore)
	{
		if(mp[0] + restore > mp[1]){
			mp[0] = mp[1];
		}
		else{
			mp[0] += restore;
		}
	}
	
	/**
	 * returns the amount of free mp
	 * @return int
	 */
	public int freeMP()
	{
		return mp[1] - mp[0];
	}
}
