/**
 * base object type for protective gear
 * @author Deus
 *
 */
public abstract class Armor extends Gear {

	public Armor() {
		super();
	}

	public Armor(double weight, int value, 
				 String name, int dice, 
				 int sides, int modifier, 
				 int defense, int damageMod, 
				 int hitMod, String statToMod, 
				 int statMod, String type) {
		super(weight, value, 
			  name, defense, 
			  damageMod, hitMod, 
			  statToMod, statMod, 
			  type);
		
	}

}
