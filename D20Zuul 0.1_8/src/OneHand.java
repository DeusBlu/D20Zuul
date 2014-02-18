/**
 * This class creates a one handed weapon
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class OneHand extends Weapon {
	/**
	 * default constructor for type OneHand
	 */
	public OneHand() {
		super();
	}
	
	/**
	 * creates an object of type OneHand - a weapon used with either hand
	 * @param double - weight in lbs
	 * @param int - value in copper
	 * @param String - name
	 * @param int - dice
	 * @param int - sides
	 * @param int - plus
	 * @param int - defense
	 * @param int - damageMod
	 * @param int - hitMod
	 * @param String - statToMod
	 * @param int - statMod
	 */
	public OneHand(double weight, 
				   int value, 
				   String name, 
				   int dice, 
				   int sides, 
				   int plus, 
				   int critMod,
				   int defense, 
				   int damageMod, 
				   int hitMod, 
				   String statToMod, 
				   int statMod, 
				   String weapProf) {
		super(weight, value, name, dice, sides, plus, critMod,
			  defense, damageMod, hitMod, statToMod, statMod, 
			  "1hweapon", weapProf);
		sendEquipSpots();
	}
	/**
	 * adds the equip spots to the array
	 */
	private void sendEquipSpots()
	{
		setEquipSpots("Main Hand");
		setEquipSpots("Off Hand");
	}
}
