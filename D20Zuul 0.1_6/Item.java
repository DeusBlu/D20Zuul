import java.util.ArrayList;
/**
 * Item objects to be equipped or used by players or monsters
 * 
 * @author (DeusBlu) 
 * @version (0.1_6)
 */
public class Item
{
    // instance variables - replace the example below with your own
    //private static final String[] EQSLOTS = {
    //    "head", "chest", "hands", "legs", "feet", "main hand", "off hand", "both hands", "backpack"
    //};
    private static final String[] EFFECT = {
        "attack", "healing", "buff", "misc", "none"
    };
    public static final String[] ITEMTYPE = {
        "2hweapon", "mhweapon", "1hweapon", "shield", "helm", "shoulders", "chest", "gloves", "pants",
        "boots","misc", "key"
    }; //showEquip in PlayerCharacter and setEquipSpots below must be updated with this list!!!!!!
    private int[] damage;
    private int def;
    private ArrayList<String> equipSpots;
    private String type;
    private String effect;
    private int effectValue;
    private String name;

    /**
     * default constructor for testing items
     */
    public Item()
    {
        damage = new int[2];
        equipSpots = new ArrayList<String>();
        setDamage(0,0);
        setDefense(0);
        equipSpots.add("backpack");
        setEffect("none");
        setEffectValue(0);
        setName("unnamed");
    }
    
    /**
     * constructor that takes every value can be used to create a multitude of items
     * @param int - damage min
     * @param int - damage max
     * @param int - defense of the item
     * @param String - item type must be a value in ITEMTYPE if not set to "misc"
     * @param String - effect the item has must be in EFFECT or set to "none"
     * @param int - effect value, how much a potion heals for etc
     * @param String - the name of this particular item "potion of healing"
     */
    public Item(int damageMin, int damageMax, int def, String type, String effect,int effectValue, String name)
    {
        damage = new int[2];
        equipSpots = new ArrayList<String>();
        setDamage(damageMin, damageMax);
        setDefense(def);
        setType(type);
        setEffect(effect);
        setEffectValue(effectValue);
        setName(name);
    }

    /**
     * sets the minimum and maximum damage for the item, these will be stored in an array [0] min [1] max
     * @param int - minimum damage
     * @param int - maximum damage
     */
    private void setDamage(int damageMin, int damageMax)
    {
        if(damageMax >= damageMin){
            damage[0] = damageMin;
            damage[1] = damageMax;
        }
        else{
            System.out.println("Max damage must be greater than or equal to min damage " + 
            "damage has been set to 0-0");
            this.damage[0] = 0;
            this.damage[1] = 0;
        }
    }
    
    /**
     * sets the defense value of the item to int
     * @param int - items defense
     */
    private void setDefense(int def)
    {
        if(def >= 0){
            this.def = def;
        }
        else{
            System.out.println("Defence on an item cannot be 0, defence has been set to 0");
            this.def = 0;
        }
    }
    
    /**
     * Sets the places where an item can be equipped to based on the type of item it is
     * @param String - the type of item it is must be a value within ITEMTYPE
     */
    private void setEquipSpots(String type)
    {
        if(type.equalsIgnoreCase("helm")){
            equipSpots.add("head");
        }
        if(type.equalsIgnoreCase("chest")){
            equipSpots.add("chest");
        }
        if(type.equalsIgnoreCase("gloves")){
            equipSpots.add("hands");
        }
        if(type.equalsIgnoreCase("pants")){
            equipSpots.add("legs");
        }
        if(type.equalsIgnoreCase("boots")){
            equipSpots.add("feet");
        }
        if(type.equalsIgnoreCase("mhweapon")){
            equipSpots.add("main hand");
        }
        if(type.equalsIgnoreCase("1hweapon")){
            equipSpots.add("main hand");
            equipSpots.add("off hand");
        }
        if(type.equalsIgnoreCase("2hweapon")){
            equipSpots.add("both hands");
        }
        if(type.equalsIgnoreCase("shoulders")){
            equipSpots.add("shoulders");
        }
        if(type.equalsIgnoreCase("shield")){
            equipSpots.add("off hand");
        }
    }
    
    /**
     * sets the effect if it is contained within EFFECT if not sets it to "none"
     * @param String - the effect the item has when used
     */
    private void setEffect(String effect)
    {
        for(int i = 0; i < EFFECT.length; i++){
            if(EFFECT[i].equalsIgnoreCase(effect)){
                this.effect = effect;
            }
        }
        if(this.effect == null){
            this.effect = "none";
        }
    }
    
    /**
     * sets the effect value, the potency of the item, 10 will heal for 10 hp etc
     * @param int - the potency of the item
     */
    private void setEffectValue(int effectValue)
    {
        if(effectValue >= 0){
            this.effectValue = effectValue;
        }
        else{
            this.effectValue = 0;
        }
    }
    
    /**
     * sets the type of the item, if not contained within ITEMTYPE then type is set to "misc"
     * @param String - the item type
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
        setEquipSpots(type);
    }
    
    /**
     * sets the name of the item, can be anything and is used for human interaction
     * @param String - the items name
     */
    private void setName(String name)
    {
        if(name != null){
            this.name = name;
        }
        else{
            this.name = "unnamed";
        }
    }
    
    /**
     * gets the minimum and maximum damage of the item returned as an int[2] for easy use
     * @return int[2] - [0] min damage [1] max damage
     */
    public int[] getDamage()
    {
        return damage;
    }
    
    /**
     * gets the defence value of the item
     * @return int - the defense
     */
    public int getDefense()
    {
        return def;
    }
    
    /**
     * gets the effect the item has
     * @return String - the items effect
     */
    public String getEffect()
    {
        return effect;
    }
    
    /**
     * gets the effect potency returned as an int
     * @return int - items effect potency
     */
    public int getEffectValue()
    {
        return effectValue;
    }
    
    /**
     * returns the type the item is as a string
     * @return String - the items type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * returns the name of the item as a string
     * @return String
     */
    public String getName()
    {
        return name;
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
    
    /**
     * shows the details of the item
     */
    public void getDetails()
    {
            System.out.print(getName() + "  ");
            if(getDamage()[1] != 0){
                System.out.print("Dmg: " + getDamage()[0] + "-" + getDamage()[1] + "  ");
            }
            if(getDefense() != 0){
                System.out.print("Def: " + getDefense() + "  ");
            }
            if(!getEffect().equalsIgnoreCase("none")){
                System.out.print("Effect: " + getEffect() + "  ");
                if(getEffectValue() > 0){
                    System.out.print("for " + getEffectValue());
                }
            }
            System.out.println();
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
     * This method attempts to use the item
     */
    public void use()
    {
        if(getEffect().equalsIgnoreCase("attack")){
        }
        if(getEffect().equalsIgnoreCase("healing")){
        }
        if(getEffect().equalsIgnoreCase("buff")){
        }
        if(getEffect().equalsIgnoreCase("key")){
        }
    }
}
