/**
 * This class creates a two handed weapon
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class TwoHanded extends Weapon {
	/**
	 * default constructor for type TwoHanded
	 */
	public TwoHanded() {
		super();
	}
	
	/**
	 * creates an object of type TwoHanded - a weapon used with both hands
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
	public TwoHanded(double weight, 
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
				defense, damageMod, hitMod, statToMod, 
				statMod, "2hweapon", weapProf);
		sendEquipSpots();
	}
	/**
	 * adds the equip spots to the array
	 */
	private void sendEquipSpots()
	{
		setEquipSpots("Both Hands");
	}
}
