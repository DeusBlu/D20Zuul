/**
 * This class creates gloves
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Gloves extends Armor {
	/**
	 * default constructor for type Gloves
	 */
	public Gloves() {
		super();
		sendEquipSpots();
	}
	
	/**
	 * creates an object of type gloves
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
	public Gloves(double weight, int value, String name, int dice, int sides, int plus, int defense, 
			int damageMod, int hitMod, String statToMod, int statMod) {
		super(weight, value, name, dice, sides, plus, defense, damageMod,
				hitMod, statToMod, statMod, "gloves");
		sendEquipSpots();
	}
	/**
	 * adds the equip spots to the array
	 */
	private void sendEquipSpots()
	{
		setEquipSpots("Hands");
	}
}
