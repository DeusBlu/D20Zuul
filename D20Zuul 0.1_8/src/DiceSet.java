/**
 * holds values for damage/healing/anything that rolls that are rolled by dice objects
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class DiceSet {
	private Dice dice;
	private int number;
	private int sides;
	private int modifier;
	
	/**
	 * default constructor for damage objects
	 */
	public DiceSet()
	{
		dice = new Dice();
		setNumber(1);
		setSides(2);
		setModifier(0);
	}
	
	/**
	 * constructor for damage to be attached to weapons/monsters/traps/anything that deals damage
	 */
	public DiceSet(int number, int sides, int modifier)
	{
		dice = new Dice();
		setNumber(number);
		setSides(sides);
		setModifier(modifier);
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
		if(number >= 0){
			this.number = number;
		}
		else{
			throw new IllegalArgumentException("number of dice to roll was negative");
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
		if(sides >= 0){
			this.sides = sides;
		}
		else{
			throw new IllegalArgumentException("the sides of the dice was negative");
		}
	}

	/**
	 * returns the modifier to the roll
	 * @return int
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * sets the modifier to the roll (+5 damage)
	 * @param int
	 */
	private void setModifier(int modifier) 
	{
		this.modifier = modifier;
	}
	
	/**
	 * returns the damage done
	 * @return int
	 */
	public int getDamage()
	{
		return (dice.roll(number, sides)) + modifier;
	}
}
