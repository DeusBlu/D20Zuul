/**
 * creates a Bow object, sets the proficiency for the weapon
 * @author DeusBlu
 * @version 0.1_8 
 *
 */
public class Bow extends TwoHanded 
{

	/**
	 * 
	 */
	public Bow() {
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
	public Bow(double weight, 
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
			 int statMod) {
	super(weight, value, name, dice, sides, plus, critMod, defense, damageMod,
				hitMod, statToMod, statMod, "Bow");
	}

}
