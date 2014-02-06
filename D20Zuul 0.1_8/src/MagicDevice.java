/**
 * represents items that have magical properties that can be activated by a use command such as wand's
 * and potion's
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class MagicDevice extends Item {
	private int charges;
	private String type;

	public MagicDevice() {
		super();
		setCharges(0);
		setType();
		
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
	public MagicDevice(double weight, int value, String name, int charges) 
	{
		super(weight, value, name);
		setCharges(charges);
		setType();
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
	 * sets the item type
	 */
	private void setType()
	{
		type = "Magic Device";
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
	 * returns the item type
	 */
	public String getType()
	{
		return type;
	}
}