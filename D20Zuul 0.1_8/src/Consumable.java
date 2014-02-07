/**
 * 
 */

/**
 * @author desmond.jenkins
 *
 */
public class Consumable extends MagicDevice {
	private String[] EFFECT = {
			"damage", "healing", "buff", "misc"
	};
	private String effect;
	private DiceSet effectValue;
	private String type;
	
	/**
	 * default constructor for consumable items
	 */
	public Consumable() 
	{
		super();
		setEffect("misc");
		setEffectValue(new DiceSet());
		setType();
	}

	/**
	 * @param weight
	 * @param value
	 * @param name
	 * @param charges
	 * @param effect
	 * @param effectValue
	 */
	public Consumable(double weight, int value, String name, int charges, String effect, DiceSet effectValue) 
	{
		super(weight, value, name, charges);
		setEffect(effect);
		setEffectValue(effectValue);
		setType();
	}
	
	/**
	 * sets the effect value, a damage object to hold dice and modifiers
	 * @param effectValue
	 */
	private void setEffectValue(DiceSet effectValue)
	{
		if(effectValue != null){
			this.effectValue = effectValue;
		}
		else{
			throw new IllegalArgumentException("Damage item of " + getName() + " was null");
		}
	}
	
	/**
	 * sets the effect type of the item, throws exception if the type is not valid
	 * @param effect
	 */
	private void setEffect(String effect)
	{
		for(int i = 0; i < EFFECT.length; i++){
			if(effect.equalsIgnoreCase(EFFECT[i]))
				this.effect = effect;
		}
		if(this.effect == null){
			throw new IllegalArgumentException("Effect of item " + getName() + " is not a valid effect type");
		}
	}
	
	/**
	 * sets the item type
	 */
	private void setType()
	{
		type = "Consumable";
	}
	
	/**
	 * returns the effect value as an int result of the roll
	 * @return int
	 */
	public int getEffectValue()
	{
		return effectValue.getRoll();
	}
	
	/**
	 * returns the effect of the item as a string
	 * @return String effect
	 */
	public String getEffect()
	{
		return effect;
	}
	
	/**
	 * returns the type of item
	 * @return String type
	 */
	public String getType()
	{
		return type;
	}
}
