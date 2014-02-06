/**
 * This class creates boots
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Boots extends Gear {
	/**
	 * default constructor for type boots
	 */
	public Boots() {
		super();
		sendEquipSpots();
	}
	
	/**
	 * creates an object of type boots
	 * @param double - weight in lbs
	 * @param int - value in copper
	 * @param String - name
	 * @param int - dice
	 * @param int - sides
	 * @param int - plus
	 * @param int - defense
	 * @param int - magicBonus
	 * @param int - hitBonus
	 * @param String - statToMod
	 * @param int - statMod
	 */
	public Boots(double weight, int value, String name, int dice, int sides, int plus, int defense, 
			int magicBonus, int hitBonus, String statToMod, int statMod) {
		super(weight, value, name, dice, sides, plus, defense, magicBonus,
				hitBonus, statToMod, statMod, "boots");
		sendEquipSpots();
	}
	/**
	 * adds the equip spots to the array
	 */
	private void sendEquipSpots()
	{
		setEquipSpots("Feet");
	}
}
