/**
 * 
 */

/**
 * creates a Axe object, sets the proficiency for the weapon
 * @author DeusBlu
 * @version 0.1_8 
 *
 */
public class OhAxe extends OneHand {

	/**
	 * 
	 */
	public OhAxe() {
		super();
	}

	/**
	 * @param weight
	 * @param value
	 * @param name
	 * @param dice
	 * @param sides
	 * @param plus
	 * @param defense
	 * @param damageMod
	 * @param hitMod
	 * @param statToMod
	 * @param statMod
	 * @param weapProf
	 */
	public OhAxe(double weight, 
				 int value, 
				 String name, 
				 int dice, 
				 int sides,
				 int plus, 
				 int defense, 
				 int damageMod, 
				 int hitMod, 
				 String statToMod,
				 int statMod) {
		super(weight, value, name, dice, sides, plus, defense, damageMod,
				hitMod, statToMod, statMod, "Axe");
	}

}
