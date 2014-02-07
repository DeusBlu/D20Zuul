/**
 * This class keeps track of the health of entites
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class HP {

	private Dice dice;
	private int[] hp;
	/**
	 * default constructor for HP
	 */
	public HP()
	{
		hp = new int[2];
		dice = new Dice();
		setHP(4, 0, 4);
	}
	
	/**
	 * constructor for HP
	 * @param int - maxHP
	 */
	public HP(int hpDie, int conMod, int startingMaxHP)
	{
		hp = new int[2];
		dice = new Dice();
		setHP(hpDie, conMod, startingMaxHP);
	}
	
	/**
	 * initializes the HP Die
	 * @param Dice hpDie
	 * @param int conMod
	 * @param int startingMaxHP
	 */
	private void setHP(int hpDie, int conMod, int startingMaxHP)
	{
		int maxHP = dice.roll(1, hpDie);
		if(maxHP + conMod > 0){
            hp[0] = maxHP + conMod;
            hp[1] = maxHP + conMod;
        }
        else{
            hp[0] = 4;
            hp[1] = 4;
        }
	}
	
	/**
	 * deal damage to the life total returns true if dead
	 * @param damage
	 * @return boolean
	 */
	public boolean takeDamage(int damage)
	{
		if(damage <= hp[0]){
			hp[0] -= damage;
			return true;
		}
		else{
			hp[0] = 0;
			return false;
		}
	}
	
	/**
	 * heals the player for the amount and returns the overheal
	 * @param healing
	 * @return int
	 */
	public int healDamage(int heal)
	{
		int overheal = 0;
		if(hp[0] + heal > hp[1]){
			overheal = heal - (hp[1] - hp[0]);
			hp[0] = hp[1];
		}
		else{
			hp[0] += heal;
		}
		return overheal;
	}
	
	/**
	 * increases the max HP of the player
	 * @param int side of HP Die
	 */
	public void levelUp(int hpDie)
	{
		int increase = dice.roll(1, hpDie);
		hp[1] += increase;
		hp[0] += increase;
	}
}
