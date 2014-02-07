import java.util.HashMap;
/**
 * takes gear in, holds it in a HashMap and returns gear
 * 
 * @author DeusBlu
 * @version 0.1_8
 */
public class Equipment
{
    private HashMap<String, Gear> equipment;
    /**
     * Constructor for objects of class Equipment
     */
    public Equipment()
    {
        equipment = new HashMap<String, Gear>();
    }
    
    /**
     * equips Gear and returns Gear if there was any in the spot equipped
     * @param String the name of the person being equipped
     * @param Gear the item to equip
     * @return Gear the item that was equipped
     */
    public Gear equip(String spot, Gear gear)
    {
        if(gear != null){
        	Gear oldGear = equipment.remove(spot);
        	equipment.put(spot, gear);
        	return oldGear;
        }
        return null;
    }
    
    /**
     * prints the details of an equipped item based on the equipment spot string
     * @param String
     */
    private void equipDetails(String location)
    {
        if(equipment.get(location) != null){
            equipment.get(location).printDetails();
        }
        else{
            System.out.println("<none>");
        }
    }
    
    /**
     * prints the current equipment of the player
     */
    public void showEquip()
    {
        System.out.println("--------------------------------");
        if(equipment.containsKey("Both Hands")){
            System.out.print("Both Hands: ");
            equipDetails("Both Hands");
        }
        else{
            System.out.print("Main Hand: ");
            equipDetails("Main Hand");
            System.out.print("Off Hand: ");
            equipDetails("Off Hand");
        }
        System.out.print("Helm: ");
        equipDetails("Head");
        System.out.print("Shoulders: ");
        equipDetails("Shoulders");
        System.out.print("Chest: ");
        equipDetails("Chest");
        System.out.print("Gloves: ");
        equipDetails("Hands");
        System.out.print("Pants: ");
        equipDetails("Legs");
        System.out.print("Boots: ");
        equipDetails("Feet");
    }
    
    /**
     * returns true if the HashMap key contains the gear location
     * @return boolean
     */
    public boolean hasGear(String spot)
    {
        boolean hasGear = false;
        if(equipment.containsKey(spot)){
            hasGear = true;
        }
        return hasGear;
    }
    
    /**
     * takes a String and searches for gear corresponding to the equip local and returns the item
     * @return Item
     * @param String
     */
    public Item getGear(String spot)
    {
        return equipment.get(spot);
    }
    
    /**
     * unequips the item from the given location and returns the item removed from the location
     * @param String
     * @return Item
     */
    public Item unequip(String spot)
    {
        return equipment.remove(spot);
    }
    
    /**
     * returns true if each hand equipped with gear
     * @param boolean
     */
    public boolean isDualWield()
    {
        if(hasGear("Main Hand") && hasGear("Off Hand")){
        	return true;
        }
        else{
        	return false;
        }
    }
    
    /**
     * returns a damage roll for the entire gears value
     * @return int
     */
    public int getDamage()
    {
        int damage = 0;
        for(String item : equipment.keySet()){
            damage += equipment.get(item).getDamage();
        }
        return damage;
    }
    
    /**
     * returns the ToHit modifier and Damage modifier for the attack round
     * @return int
     */
    public int getAttackMod()
    {
        int mod = 0;
        for(String gear : equipment.keySet()){
            mod += equipment.get(gear).getHitBonus();
        }
        return mod;
    }
    
    /**
     * returns the Defence modifier for when player is attacked
     * @return int
     */
    public int getDefenseMod()
    {
        int mod = 0;
        for(String gear : equipment.keySet()){
            mod += equipment.get(gear).getDefense();
        }
        return mod;
    }
}
