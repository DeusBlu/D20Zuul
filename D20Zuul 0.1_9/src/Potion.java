/**
 * an item that heals when used
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Potion extends Consumable {

	/**
	 * default constructor for potions.
	 */
	public Potion() {
		super();
	}

	/**
	 * constructor to be used for potions
	 * @param weight
	 * @param value
	 * @param name
	 * @param charges
	 * @param effect
	 * @param effectValue
	 */
	public Potion(double weight, 
				  int value, 
				  String name, 
				  int charges,
				  String description, 
				  int damDice,
				  int damDie,
				  int damMod,
				  boolean offensive) {
		super(weight, value, name, charges, description, damDice, damDie, damMod, offensive, "potion");
		
	}

	/* returns true if the item is out of charges
	 * @see Consumable#use(Entity)
	 */
	@Override
	public boolean use(Entity target) {
		target.healDamage(getEffectValue());
		if(getCharges() == 1){
			useCharge();
			return true;
		}
		useCharge();
		return false;
	}

}
