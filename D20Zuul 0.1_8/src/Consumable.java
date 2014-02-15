/**
 * 
 */

/**
 * @author desmond.jenkins
 *
 */
public abstract class Consumable extends MagicDevice {
	private String type;
	private String description;
	
	/**
	 * default constructor for consumable items
	 */
	public Consumable() 
	{
		super();
		setDescription("misc");
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
	public Consumable(double weight, 
					  int value, 
					  String name, 
					  int charges, 
					  String description, 
					  int damDice, 
					  int damDie, 
					  int damMod, 
					  boolean offensive) 
	{
		super(weight, value, name, charges, damDice, damDie, damMod, offensive);
		setDescription(description);
		setType();
	}
	
	/**
	 * sets the effect type of the item, throws exception if the type is not valid
	 * @param effect
	 */
	private void setDescription(String description)
	{
		if(description != null && !description.isEmpty()){
			this.description = description;
		}
		else{
			throw new IllegalArgumentException("Description string was empty or null on " + getName());
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
	 * returns the effect of the item as a string
	 * @return String effect
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * returns the type of item
	 * @return String type
	 */
	public String getType()
	{
		return type;
	}
	
	public abstract boolean use(Entity target);
}
