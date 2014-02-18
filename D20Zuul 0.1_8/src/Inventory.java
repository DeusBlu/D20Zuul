

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
    private Item[] backpack;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        backpack = new Item[BACKPACK_SIZE];
    }
    
    /**
     * for creating larger inventory items that can be used to store items for monster loot tables and 
     * rooms for items dropped on the floor can
     * @param int - the number of items you want the inventory to hold
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
     * loots an item into your inventory
     * @param Item - the item to loot
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
    
    public boolean isEmpty()
    {
    	boolean empty = true;
    	for(int i = 0; i < backpack.length; i++){
    		if(backpack[i] != null){
    			empty = false;
    		}
    	}
    	return empty;
    }
    
    /**
     * shows the details of the item at requested bag index
     * @param int - the item #
     */
    public void showBagItem(int spot)
    {
        if(spot > 0 && backpack[spot-1] != null){
            spot--;
            if(backpack[spot] instanceof TwoHanded){
            	((TwoHanded)backpack[spot]).printDetails();
            }
            if(backpack[spot] instanceof MainHand){
            	((MainHand)backpack[spot]).printDetails();
            }
            if(backpack[spot] instanceof OneHand){
            	((OneHand)backpack[spot]).printDetails();
            }
            if(backpack[spot] instanceof Shield){
            	((Shield)backpack[spot]).printDetails();
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
     * returns the total size of your backpack
     * @return int
     */
    public int length()
    {
        return backpack.length;
    }
    
    /**
     * removes an item from the bag and returns it, returns null if no item found
     * @param int
     * @return Item
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
     * querries the inventory for the item at its position and returns the item at that location else returns null
     * @param int
     * @return item
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
     * querries the spot for equipable gear returns null if not equipable or empty
     * @param int
     * @return gear
     */
    public Gear getGear(int spot)
    {
    	if(backpack[spot] != null && backpack[spot] instanceof Gear){
    		return (Gear)backpack[spot];
    	}
    	else{
    		return null;
    	}
    }
    
    /**
     * querries the spot for equipable gear returns null if not equipable or empty
     * @param int
     * @return gear
     */
    public Gear removeGear(int spot)
    {
    	if(backpack[spot] != null && backpack[spot] instanceof Gear){
    		Gear returned = (Gear)backpack[spot];
    		backpack[spot] = null;
    		return returned;
    	}
    	else{
    		return null;
    	}
    }
    
    /**
     * takes an item from one inventory and transfers it to this inventory based on its location in
     * the originating inventory and returns true if it was able to be moved
     * @param Inventory
     * @param int
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
     * Sorts the inventory to be the same order as set by Item.ITEMTYPE
     */
    public void sort()
    {
        if(length() == BACKPACK_SIZE){
            Inventory temp = new Inventory();
            for(int i = 0; i < BACKPACK_SIZE; i++){
                temp.lootItem(removeItem(i));
            }
            for(int i = 0; i < Constant.ITEMTYPE.length; i++){
                for(int x = 0; x < BACKPACK_SIZE; x++){
                    if(temp.getItem(x) != null){
                        if(temp.getItem(x).getType().equals(Constant.ITEMTYPE[i])){
                            lootItem(temp.getItem(x));
                        }
                    }
                }
            }
        }
    }
}
