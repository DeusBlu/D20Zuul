/**
 * The basic Fighter class for players
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Fighter extends PlayerClass {
	
	public int bonusFeat;
	/**
	 * @param name
	 * @param hpDie
	 * @param forSave
	 * @param reflexSave
	 * @param willSave
	 * @param skillPoints
	 * @param attack
	 */
	public Fighter() {
		super("Warrior", 10, 2, 0, 0, 2, 1, 5, 5);
		setBonusFeat();
	}
	
	public void setBonusFeat()
	{
		bonusFeat = 1;
	}
	
	@Override
	public int[] getAttacks()
	{
		int numAttacks = getNumAttacks(getLevel());
		int[] attacks = new int[numAttacks];
		for(int i = 0; i <= getLevel(); i++){
			if(i > 0){
				attacks[0]++;
			}
			if(i > 5){
				attacks[1]++;
			}
			if(i > 10){
				attacks[2]++;
			}
			if(i > 15){
				attacks[3]++;
			}
		}
		return attacks;
	}
	
	private int getNumAttacks(int level)
	{
		int numAttacks;
		if((level%5) == 0){
			numAttacks = level/5;
		}
		else{
			numAttacks = (level/5)+1;
		}
		return numAttacks;
	}
}
