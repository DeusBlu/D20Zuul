import java.util.HashMap;
/**
 * Write a description of class Equipment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipment
{
    private static final String[] MAINHAND_OPTIONS = {
        "mh", "main hand", "mainhand", "m h", "main", "m", "ma"
    };
    private static final String[] OFFHAND_OPTIONS = {
        "oh", "off hand", "offhand", "o h", "off", "o", "of"
    };
    private HashMap<String, Item> gear;
    private InputReader reader;
    private Inventory backpack;
    /**
     * Constructor for objects of class Equipment
     */
    public Equipment()
    {
        backpack = new Inventory();
        reader = new InputReader();
        gear = new HashMap<String, Item>();
    }
    
    /**
     * equips Item into location String based on an array of possible answers
     * @param String the name of the person being equipped
     * @param Item the item being equipped
     * @param String the location the item is being equipped to
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
     * equips the item to the appropriate location
     * @param String the name of the person being equipped
     * @param Item the item being equipped
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
     * a more simple equip method with no output for putting gear on a monster when battle starts
     * @param Item
     * @param String
     */
    public void equipMonster(Item item, String spot)
    {
        if(item != null && !item.getType().equals("misc") && !item.getType().equals("key")){
            gear.put(spot, item);
        }
    }
    
    /**
     * an even more simple equip method for equipping monsters with items that go only to one place
     * @param Item
     */
    public void equipMonster(Item item)
    {
        if(item != null && !item.getType().equals("misc") && !item.getType().equals("key")){
            gear.put(item.getEquipString(), item);
        }
    }
    
    /**
     * prints the details of an equipped item based on the equipment spot string
     * @param String
     */
    private void equipDetails(String location)
    {
        if(gear.get(location) != null){
            gear.get(location).getDetails();
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
        if(gear.containsKey("both hands")){
            System.out.print("Both Hands: ");
            equipDetails("both hands");
        }
        else{
            System.out.print("Main Hand: ");
            equipDetails("main hand");
            System.out.print("Off Hand: ");
            equipDetails("off hand");
        }
        System.out.print("Helm: ");
        equipDetails("head");
        System.out.print("Shoulders: ");
        equipDetails("shoulders");
        System.out.print("Chest: ");
        equipDetails("chest");
        System.out.print("Gloves: ");
        equipDetails("hands");
        System.out.print("Pants: ");
        equipDetails("legs");
        System.out.print("Boots: ");
        equipDetails("feet");
    }
    
    /**
     * returns true if the HashMap key contains the gear location
     * @return boolean
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
     * takes a String and searches for gear corresponding to the equip local and returns the item
     * @return Item
     * @param String
     */
    public Item getGear(String spot)
    {
        return gear.get(spot);
    }
    
    /**
     * unequips the item from the given location and returns the item removed from the location
     * @param String
     * @return Item
     */
    public Item unequip(String spot)
    {
        return gear.remove(spot);
    }
    
    /**
     * initiates the equip dialogue takes the item returned by equip stores it, removes the item you
     * equipped and puts the equipped item back into inventory
     */
    public void equip(Inventory backpack, String name)
    {
        this.backpack = backpack;
        int input = 0;
        while(input == 0){
            backpack.showBag();
            System.out.println();
            System.out.println("Which item # would you like to equip?");
            System.out.print("> ");
            input = reader.readInt();
        }
        if(input < backpack.length()){
            equipLogic(backpack.removeItem(input-1), name);
        }
        else{
            System.out.println("There is nothing there to equip");
        }
        backpack.sort();
    }
    
    /**
     * takes the item passed and finds out what kind of item it is, performs the needed logic to make sure
     * there are no logic collissions between this item and any other item, returns previously equipped
     * item to the inventory
     * @param Item
     */
    private void equipLogic(Item item, String name)
    {
        Inventory wasEquipped = new Inventory();
        if(backpack.bagSpace() <= 1 && item.getEquipString().equals("both hands")
        && hasGear("main hand") && hasGear("off hand")){
              System.out.println(name + "'s" + " backpack is full");
              wasEquipped.lootItem(item);
        }
        else if(item.getType().equals("misc") || item.getType().equals("key")){
              System.out.println("You cannot equip that type of item");
              wasEquipped.lootItem(item);
        }
        else{
            if(item.getType().equalsIgnoreCase("1hweapon")){
                boolean ask = true;
                while(ask){
                    System.out.println("Which hand to equip to?");
                    System.out.print("> ");
                    String input = reader.readString();
                    for(int i=0; i < MAINHAND_OPTIONS.length; i++){
                        if(input.equalsIgnoreCase(MAINHAND_OPTIONS[i])){
                            if(hasGear("main hand")){
                                System.out.println(name + "'s " + getGear("main hand").getName() + " has been removed");
                            }
                            checkWeapons(item, name);
                            wasEquipped.lootItem(unequip("main hand"));
                            wasEquipped.lootItem(unequip("both hands"));
                            equipPlayer(name, item, "main hand");
                            ask = false;
                        }
                    }
                    for(int i=0; i < OFFHAND_OPTIONS.length; i++){
                        if(input.equalsIgnoreCase(OFFHAND_OPTIONS[i])){
                            if(hasGear("off hand")){
                                System.out.println(name + "'s " + getGear("off hand").getName() + " has been removed");
                            }
                            checkWeapons(item, name);
                            wasEquipped.lootItem(unequip("off hand"));
                            wasEquipped.lootItem(unequip("both hands"));
                            equipPlayer(name, item, "off hand");
                            ask = false;
                        }
                    }
                    if(ask){
                        System.out.println("You can equip to main hand or off hand");
                    }
                }
            }
            else if(item.getEquipString().equals("both hands")){
                checkWeapons(item, name);
                wasEquipped.lootItem(unequip("both hands"));
                wasEquipped.lootItem(unequip("main hand"));
                wasEquipped.lootItem(unequip("off hand"));
                equipPlayer(name, item);
            }
            else{
                equipPlayer(name, item);
            }
        }
        if(backpack.transfer(0, wasEquipped)){
        }
        if(backpack.transfer(1, wasEquipped)){
        }
    }
    
    /**
     * takes the item passed and makes sure that no 
     * @param Item
     */
    private void checkWeapons(Item item, String name)
    {
        if(hasGear("both hands") && item.getType().equalsIgnoreCase("1hweapon")){
            System.out.println(name + "'s " + getGear("both hands").getName() + " has been removed");
        }
        if(hasGear("both hands") && item.getType().equalsIgnoreCase("mhweapon")){
            System.out.println(name + "'s " + getGear("both hands").getName() + " has been removed");
        }
        if(hasGear("off hand") && item.getType().equalsIgnoreCase("2hweapon")){
            System.out.println(name + "'s " + getGear("off hand").getName() + " has been removed");
        }
        if(hasGear("main hand") && item.getType().equalsIgnoreCase("2hweapon")){
            System.out.println(name + "'s " + getGear("main hand").getName() + " has been removed");
        }
        if(hasGear(item.getEquipString())){
            System.out.println(name + "'s " + getGear(item.getEquipString()).getName() + 
            " has been removed");
        }
    }
    
    /**
     * returns an int[2] where 0 is min damage and 1 is max damage of the items equipped
     * @return int[]
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
     * returns the ToHit modifier and Damage modifier for the attack round
     * @return int
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
     * returns the Defence modifier for when player is attacked
     * @return int
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
