/**
 * holds values for damage that are rolled by dice objects
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Damage {
	private Dice dice;
	private int number;
	private int sides;
	private int plus;
	
	/**
	 * default constructor for damage objects
	 */
	public Damage()
	{
		dice = new Dice();
		setNumber(1);
		setSides(2);
		setPlus(0);
	}
	
	/**
	 * constructor for damage to be attached to weapons/monsters/traps/anything that deals damage
	 */
	public Damage(int number, int sides, int plus)
	{
		dice = new Dice();
		setNumber(number);
		setSides(sides);
		setPlus(plus);
	}

	/**
	 * returns number of dice rolled as int
	 * @return int
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * sets the number of dice to roll
	 * @param number
	 */
	private void setNumber(int number) {
		if(number > 0){
			this.number = number;
		}
		else{
			this.number = 1;
		}
	}

	/**
	 * returns number of sides of the die
	 * @return int
	 */
	public int getSides() {
		return sides;
	}

	/**
	 * sets the number of sides of the dice
	 * @param int
	 */
	private void setSides(int sides) {
		if(sides > 0){
			this.sides = sides;
		}
		else{
			this.sides = 2;
		}
	}

	/**
	 * returns the modifier to the roll
	 * @return int
	 */
	public int getPlus() {
		return plus;
	}

	/**
	 * sets the modifier to the roll (+5 damage)
	 * @param int
	 */
	private void setPlus(int plus) {
		if(plus > 0){
			this.plus = plus;
		}
		else{
			this.plus = 0;
		}
	}
	
	/**
	 * returns the damage done
	 * @return int
	 */
	public int getDamage()
	{
		return (dice.roll(number, sides)) + plus;
	}
}
