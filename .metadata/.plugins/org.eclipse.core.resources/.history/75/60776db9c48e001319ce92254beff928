
import java.util.ArrayList;
/**
 * This class creates a two handed weapon
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Gloves extends Gear {
	private String type;
	private ArrayList<String> equipSpots;
	
	/**
	 * default constructor for type TwoHanded
	 */
	public Gloves() {
		super();
		setType();
		equipSpots = new ArrayList<String>();
		setEquipSpots();
	}
	
	/**
	 * creates an object of type TwoHanded - a weapon used with both hands
	 * @param double - weight in lbs
	 * @param int - value in copper
	 * @param String - name
	 * @param int - dice
	 * @param int - sides
	 * @param int - plus
	 * @param int - defense
	 * @param int - magicBonus
	 * @param int - hitBonus
	 * @param String - statToMod
	 * @param int - statMod
	 */
	public Gloves(double weight, int value, String name, int dice, int sides, int plus, int defense, 
			int magicBonus, int hitBonus, String statToMod, int statMod) {
		super(weight, value, name, dice, sides, plus, defense, magicBonus,
				hitBonus, statToMod, statMod);
		setType();
		equipSpots = new ArrayList<String>();
		setEquipSpots();
	}
	
	/**
	 * sets the type of weapon this is
	 */
	private void setType()
	{
		this.type = "gloves";
	}
	
	/**
	 * adds the equip spots to the array
	 */
	private void setEquipSpots()
	{
		equipSpots.add("Hands");
	}
	
	/**
	 * returns the type of weapon this is
	 * @return String
	 */
	public String getType()
	{
		return type;
	}
	
    /**
     * prints the places that an item can be equipped
     */
    public void equipWhere()
    {
        String loc = "";
        for(String spot : equipSpots){
            loc += spot + ", ";
        }
        System.out.println(getName() + " can be go to your: " + loc);
    }
    
    /**
     * returns a string containing all the places an item can be equipped, most items this is only 1 location some
     * are more
     * @return String
     */
    public String getEquipString()
    {
        String loc = "";
        for(String spot : equipSpots){
            loc += spot;
        }
        return loc;
    }
}
