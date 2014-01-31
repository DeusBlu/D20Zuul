import java.util.HashMap;
/**
 * Write a description of class Equipment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipment
{
    private HashMap<String, Item> gear;
    /**
     * Constructor for objects of class Equipment
     */
    public Equipment()
    {
        gear = new HashMap<String, Item>();
    }
    
    /**
     * @param String the name of the person being equipped
     * @param Item the item being equipped
     * @param String the location the item is being equipped to
     * equips Item into location String based on an array of possible answers
     */
    public void equipPlayer(String name, Item item, String spot)
    {
        if(item != null && spot != null){
            if(item.getEquipString().contains(spot) && !item.getEquipString().isEmpty()){
                if(gear.containsKey(spot)){
                    System.out.println("Replaced " + name + "'s " + gear.get(spot).getName() + 
                    " with " + item.getName());
                }
                else{
                    System.out.println(item.getName() + " has been equipped to " + name + 
                    "'s " + spot);
                }
                gear.put(spot, item);
            }
            else{
                System.out.println("That item can only go to " + item.getEquipString());
            }
        }
    }
    
    /**
     * @param String the name of the person being equipped
     * @param Item the item being equipped
     * equips the item to the appropriate location
     */
    public void equipPlayer(String name, Item item)
    {
        if(item != null){
            if(gear.containsKey(item.getEquipString())){
                System.out.println("Replaced " + name + "'s " 
                + gear.get(item.getEquipString()).getName()  + " with " +  item.getName());
                }
            else{
                System.out.println(item.getName() + " has been equipped to " + name);
            }
            gear.put(item.getEquipString(), item);
        }
        else{
            System.out.println("That item can only go to " + item.getEquipString());
        }
    }
    
    /**
     * @param Item
     * @param String
     * a more simple equip method with no output for putting gear on a monster when battle starts
     */
    public void equipMonster(Item item, String spot)
    {
        if(item != null && !item.getType().equals("misc") && !item.getType().equals("key")){
            gear.put(spot, item);
        }
    }
    
    /**
     * @param Item
     * an even more simple equip method for equipping monsters with items that go only to one place
     */
    public void equipMonster(Item item)
    {
        if(item != null && !item.getType().equals("misc") && !item.getType().equals("key")){
            gear.put(item.getEquipString(), item);
        }
    }
    
    /**
     * @param String
     * prints the details of an equipped item based on the equipment spot string
     */
    public void equipDetails(String location)
    {
        if(gear.get(location) != null){
            gear.get(location).getDetails();
        }
        else{
            System.out.println("<none>");
        }
    }
    
    /**
     * @return boolean
     * returns true if the HashMap key contains the gear location
     */
    public boolean hasGear(String key)
    {
        boolean hasGear = false;
        if(gear.containsKey(key)){
            hasGear = true;
        }
        return hasGear;
    }
    
    /**
     * @return Item
     * @param String
     * takes a String and searches for gear corresponding to the equip local and returns the item
     */
    public Item getGear(String spot)
    {
        return gear.get(spot);
    }
    
    /**
     * @param String
     * @return Item
     * unequips the item from the given location and returns the item removed from the location
     */
    public Item unequip(String spot)
    {
        return gear.remove(spot);
    }
    
    /**
     * @return int[]
     * returns an int[2] where 0 is min damage and 1 is max damage of the items equipped
     */
    public int[] getDamage()
    {
        int[] damage = new int[2];
        for(String item : gear.keySet()){
            damage[0] += gear.get(item).getDamage()[0];
            damage[1] += gear.get(item).getDamage()[1];
        }
        return damage;
    }
    
    /**
     * @return int
     * returns the ToHit modifier and Damage modifier for the attack round
     */
    public int getAttackMod()
    {
        int mod = 0;
        for(String item : gear.keySet()){
            if(gear.get(item).getEffect().equals("buff") && gear.get(item).getType().equals("1hweapon")){
                mod += gear.get(item).getEffectValue();
            }
            if(gear.get(item).getEffect().equals("buff") && gear.get(item).getType().equals("2hweapon")){
                mod += gear.get(item).getEffectValue();
            }
            if(gear.get(item).getEffect().equals("buff") && gear.get(item).getType().equals("ohweapon")){
                mod += gear.get(item).getEffectValue();
            }
        }
        return mod;
    }
    
    /**
     * @return int
     * returns the Defence modifier for when player is attacked
     */
    public int getDefenseMod()
    {
        int mod = 0;
        for(String item : gear.keySet()){
            mod += gear.get(item).getDefense();
        }
        return mod;
    }
}
