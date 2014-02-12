/**
 * This class creates a main handed weapon
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class MainHand extends Weapon {
	/**
	 * default constructor for type MainHand
	 */
	public MainHand() {
		super();
	}
	
	/**
	 * creates an object of type Main Hand - a weapon used with your main hand
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
	public MainHand(double weight, int value, String name, int dice, int sides, int plus, int defense, 
			int damageMod, int hitMod, String statToMod, int statMod) {
		super(weight, value, name, dice, sides, plus, defense, damageMod,
				hitMod, statToMod, statMod, "mhweapon");
		sendEquipSpots();
	}
	/**
	 * adds the equip spots to the array
	 */
	private void sendEquipSpots()
	{
		setEquipSpots("Main Hand");
	}
}
