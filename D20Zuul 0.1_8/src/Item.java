import java.util.ArrayList;
/**
 * Item objects to be equipped or used by players or monsters
 * 
 * @author (DeusBlu) 
 * @version (0.1_6)
 */
public class Item
{
	public static final String[] ITEMTYPE = {
		"2hweapon", "mhweapon", "1hweapon", "shield", "helm", "shoulders", "chest", "gloves", "pants",
		"boots","misc", "key"
		}; //showEquip in PlayerCharacter and setEquipSpots below must be updated with this list!!!!!!
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
    public Item(double weight, int value,String type, String name)
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
     * sets the item type
     * @param String type
     */
    private void setType(String type)
    {
        for(int i=0; i < ITEMTYPE.length; i++){
            if(ITEMTYPE[i].equalsIgnoreCase(type)){
                this.type = type;
            }
        }
        if(this.type == null){
            this.type = "misc";
        }
    }
    
    /**
     * set the item name
     * @param String
     */
    private void setName(String name)
    {
    	if(name != null && name.isEmpty()){
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
    public String getItemType()
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
}
