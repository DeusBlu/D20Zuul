import java.util.ArrayList;
/**
 * Item objects to be equipped or used by players or monsters
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * Constructor for objects of class Item
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
     * @param  damage the min and max damage of the item
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
     * @param def the defence value of the item
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
     * @param slot the slot that the item can be equipped, it will be checked for validity
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
     * @param effect represents any extra effect the item may have
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
     * @param effect value the potency of the effect the item has
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
     * @param the location the item can be equipped as determined by its type
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
     * @param name the name the item will be known by eg potion of healing
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
     * @return the min-max damage of the item
     */
    public int[] getDamage()
    {
        return damage;
    }
    
    /**
     * @return the defence value of the item
     */
    public int getDefense()
    {
        return def;
    }
    
    /**
     * @return the effect the item has when used
     */
    public String getEffect()
    {
        return effect;
    }
    
    /**
     * @return the value used for the effect such as healing done or increase to stats
     */
    public int getEffectValue()
    {
        return effectValue;
    }
    
    /**
     * @return the type of item this is, this will determine where it can be equipped all items can be 
     * stored in the backpack
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @return the name of the item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @param ArrayList<String> this returns the entire array object with the places an item can
     * be equipped
     */
    public ArrayList<String> getEquipSpots()
    {
        return equipSpots;
    }
    
    /**
     * @return String this method returns a string of the places that an item can be equipped
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
     * this method prints the places that an item can be equipped
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
    public void use(Item item)
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
