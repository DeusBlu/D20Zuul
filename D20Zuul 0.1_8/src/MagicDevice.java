/**
 * represents items that have magical properties that can be activated by a use command such as wand's
 * and potion's
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public abstract class MagicDevice extends Item {
	private int charges;
	private String type;
	private boolean offensive;
	private DiceSet effectValue;

	public MagicDevice() 
	{
		super();
		setEffectValue(new DiceSet());
		setCharges(0);
		setOffensive(false);
	}

	/**
	 * constructor for MagicDevices
	 * @param weight
	 * @param value
	 * @param name
	 * @param charges
	 * @param effect
	 * @param effectValue
	 */
	public MagicDevice(double weight, int value, String name, int charges,DiceSet effectValue, boolean offensive) 
	{
		super(weight, value, name);
		setCharges(charges);
		setOffensive(offensive);
		setEffectValue(effectValue);
	}
	
	/**
	 * sets the charges the item has, 0 is unlimited, sets to 0 if negative number given
	 * @param charges
	 */
	private void setCharges(int charges)
	{
		if(charges > 0){
			this.charges = charges;
		}
		else{
			throw new IllegalArgumentException("Charges of item " + getName() + " are negative");
		}
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
	 * sets if the item is offensive or defensive
	 * @param offensive
	 */
	private void setOffensive(Boolean offensive)
	{
		this.offensive = offensive;
	}
	
	/**
	 * returns the number of charges the device has
	 * @return int
	 */
	public int getCharges()
	{
		return charges;
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
	 * uses a charge of the item
	 */
	public void useCharge()
	{
		charges--;
	}
	
	/**
	 * returns the item type
	 */
	public String getType()
	{
		return type;
	}
    
	/**
	 * returns true if the item is offensive
	 * @return boolean true if offensive
	 */
    public boolean isOffensive()
    {
    	return offensive;
    }
	
	/**
	 * uses the item returns true if item can be used
	 * @return boolean true if can be used
	 */
	public abstract boolean use(Entity target);
}
