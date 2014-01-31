
/**
 * an Inventory class that manages items for the player including sorting and looting
 * 
 * @author (DeusBlu) 
 * @version (0.1_3)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private static final int BACKPACK_SIZE = 12;
    private static final String[] SORT_ORDER = {
        "2hweapon", "mhweapon", "1hweapon", "shield", "helm", "shoulders", "chest", "gloves", "pants",
        "boots","misc"
    };
    private Item[] backpack;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        backpack = new Item[BACKPACK_SIZE];
    }
    
    /**
     * @param int - the number of items you want the inventory to hold
     * for creating larger inventory items that can be used to store items for monster loot tables and 
     * rooms for items dropped on the floor can
     */
    public Inventory(int size)
    {
        if(size > 0){
            backpack = new Item[size];
        }
        else{
            backpack = new Item[BACKPACK_SIZE];
        }
    }

    /**
     * @param Item loots the item and puts it into your backpack
     */
    public boolean lootItem(Item item)
    {
        boolean placed = false;
        for(int i = 0; i < backpack.length; i++){
            if(backpack[i] == null && placed == false){
                backpack[i] = item;
                placed = true;
                return true;
            }
        }
        if(placed == false){
            return false;
        }
        return false;
    }
    
    /**
     * displays the contents of your bag
     */
    public void showBag()
    {
        for(int i = 0; i < backpack.length; i++){
            if(backpack[i] != null){
                System.out.println((i+1) + ". " + backpack[i].getName());
            }
        }
    }
    
    /**
     * shows the details of the item at requested bag index
     */
    public void showBagItem(int spot)
    {
        if(spot > 0 && backpack[spot-1] != null){
            spot--;
            System.out.println("Item Name: " + backpack[spot].getName());
            System.out.println("Item Type: " + backpack[spot].getType());
            if(backpack[spot].getDamage()[1] != 0){
                System.out.println("Damage: " + backpack[spot].getDamage()[0] + "-" + 
                backpack[spot].getDamage()[1]);
            }
            if(backpack[spot].getDefense() != 0){
                System.out.println("Defense: " + backpack[spot].getDefense());
            }
            if(!backpack[spot].getEffect().equalsIgnoreCase("none")){
                System.out.println("Effect: " + backpack[spot].getEffect());
                if(backpack[spot].getEffectValue() > 0){
                    System.out.println("Potency: " + backpack[spot].getEffectValue());
                }
            }
        }
    }
    
    /**
     * this method checks the free space in the bag and returns the spaces as an int
     * @return int
     */
    public int bagSpace()
    {
        int space = 0;
        for(int i = 0; i < backpack.length; i++){
            if(backpack[i] == null){
                space++;
            }
        }
        return space;
    }
    
    /**
     * @return int
     * returns the total size of your backpack
     */
    public int length()
    {
        return backpack.length;
    }
    
    /**
     * @param int
     * @return Item
     * removes an item from the bag and returns it, returns null if no item found
     */
    public Item removeItem(int spot)
    {
        Item removed = null;
        if(spot >= 0 && backpack[spot] != null){
            removed = backpack[spot];
            backpack[spot] = null;
        }
        return removed;
    }
    
    /**
     * @param int
     * @return item
     * querries the inventory for the item at its position and returns the item at that location
     */
    public Item getItem(int spot)
    {
        if(backpack[spot] != null){
            return backpack[spot];
        }
        else{
            return null;
        }
    }
    
    /**
     * @param Inventory
     * @param int
     * takes an item from one inventory and transfers it to this inventory based on its location in
     * the originating inventory and returns true if it was able to be moved
     */
    public boolean transfer(int spot, Inventory from)
    {
        if(bagSpace() > 0){
            lootItem(from.removeItem(spot));
            return true;
        }
        return false;
    }
    
    /**
     * Sorts the inventory to be "2hweapon", "mhweapon", "1hweapon", "shield", "helm", "shoulders", "chest", 
     * "gloves", "pants", "boots","misc"
     */
    public void sort()
    {
        if(length() == BACKPACK_SIZE){
            Inventory temp = new Inventory();
            for(int i = 0; i < BACKPACK_SIZE; i++){
                temp.lootItem(removeItem(i));
            }
            for(int i = 0; i < Item.ITEMTYPE.length; i++){
                for(int x = 0; x < BACKPACK_SIZE; x++){
                    if(temp.getItem(x) != null){
                        if(temp.getItem(x).getType().equals(Item.ITEMTYPE[i])){
                            lootItem(temp.getItem(x));
                        }
                    }
                }
            }
        }
    }
}
