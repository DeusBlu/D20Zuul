
/**
 * Item objects to be equipped or used by players or monsters
 * 
 * @author (DeusBlu) 
 * @version (0.1_6)
 */
public abstract class Item
{
    private double weight;
    private int value;
    private String type;
    private String name;
    
    /**
     * default constructor for class Item
     */
    public Item()
    {
    	setWeight(0);
    	setValue(0);
    	setType("misc");
    	setName("unknownItem");
    }
    
    /**
     * constructor for class Item passing values
     * @param double - the weight in lbs
     * @param int - the value in copper
     * @param String - the item type
     * @param String - the name of the item
     */
    public Item(double weight, int value, String type, String name)
    {
    	setWeight(weight);
    	setValue(value);
    	setType(type);
    	setName(name);
    }
    
    /**
     * set the weight of the item
     * @param String
     */
    private void setWeight(double weight)
    {
    	if(weight > 0){
    		this.weight = weight;
    	}
    	else{
    		this.weight = 0;
    	}
    }
    
    /**
     * set the value of the item
     * @param int - value in copper
     */
    private void setValue(int value)
    {
    	if(value > 0){
    		this.value = value;
    	}
    	else{
    		this.value = 0;
    	}    	
    }
	
	/**
	 * sets the type of gear this item is passed from child Class
	 * @param String
	 */
	public void setType(String type)
	{
		boolean set = false;
		for(int i = 0; i < Constant.ITEMTYPE.length; i++){
			if(Constant.ITEMTYPE[i].equals(type)){
				this.type = type;
				set = true;
			}
		}
		if(!set){
			throw new IllegalArgumentException(getName()+" type was unrecognized");
		}
	}
    
    /**
     * set the item name
     * @param String
     */
    private void setName(String name)
    {
    	if(name != null && !name.isEmpty()){
    		this.name = name;
    	}
    	else{
    		this.name = "unknown";
    	}
    }
    
    /**
     * returns the weight of the item in pounds
     * @return double
     */
    public double getWeight()
    {
    	return weight;
    }
    
    /**
     * returns the value of the item in copper
     * @return int
     */
    public int getValue()
    {
    	return value;
    }
    
    /**
     * returns the item type
     * @return String
     */
    public String getType()
    {
    	return type;
    }
    
    /**
     * returns the name of the item
     * @return String
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * returns the value as Xg Ys Zc
     * @return String
     */
    public String printValue()
    {
    	int copper = value % 10;
    	int silver = (value/10) % 10;
    	int gold = (value/100);
    	return "" + gold + "g " + silver + "s " + copper + "c";
    }
    
    /**
     * prints the details about the item
     */
    public void printDetails()
    {
    	System.out.println("Item Name: " + getName());
        System.out.println("Item Type: " + getType());
        System.out.println("Item Weight: " + getWeight() + "lbs");
        System.out.println("Value: " + printValue());
    }
}
