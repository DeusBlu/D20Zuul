/*
 * keeps track of the players XP and returns level up information
 * @author DeusBlu
 * @version 0.1_8
 */
public class XP {
	
	private int[] xp;
	int level;
	
	/**
	 * default constructor for XP items
	 */
	public XP() 
	{
		xp = new int[2];
		setXP(1);
		setLevel(1);
	}
	
	/**
	 * constructor for XP items with param starting level
	 * @param int
	 */
	public XP(int startingLevel)
	{
		xp = new int[2];
		setXP(startingLevel);
		setLevel(startingLevel);
	}
	
	/**
	 * initializes the XP
	 */
	private void setXP(int startingLevel)
	{
		for(int i = 1; i <= startingLevel; i++){
			xp[0] = xp[1];
			xp[1] += (i * 1000);
		}
	}
	
	/**
	 * sets the starting level of the player
	 * @param int starting level
	 */
	private void setLevel(int startingLevel)
	{
		if(startingLevel > 0){
			level = startingLevel;
		}
	}
	
	/**
	 * prints out the current exp and exp needed for next level
	 */
	public void printXP()
	{
		System.out.println("Level: " + level);
		System.out.println("XP: " + xp[0] + "/" + xp[1]);
	}
	
	/**
	 * levels up the character
	 */
	private void levelUp()
	{
		level++;
		xp[1] += level * 1000;
	}
	
	/**
	 * adds Xp to the character and calls level up when appropriate
	 * @param xp to add as an int
	 * @return boolean true if leveled
	 */
	public boolean addXP(int xp)
	{
		int startLevel = getLevel();
		this.xp[0] += xp;
		while(this.xp[0] >= this.xp[1]){
			levelUp();
		}
		if(startLevel != level){
			return true;
		}
		return false;
	}
	
	/**
	 * returns the level as an int
	 * @return int
	 */
	public int getLevel()
	{
		return level;
	}
}
