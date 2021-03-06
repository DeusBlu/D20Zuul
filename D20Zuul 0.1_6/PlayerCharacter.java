import java.util.ArrayList;
/**
 * This class is used to create players, it can make NPC's, PC's, and can be stored in the party for
 * controlling multiple characters
 * 
 * @author (DeusBlu) 
 * @version (0.1_3)
 */
public class PlayerCharacter
{
    // instance variables - replace the example below with your own
    private static final int STARTING_HP = 10;
    private static final int STARTING_MP = 1;
    private static final int BASE_AC = 10;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 75;
    private static final int MAX_XP = 100;
    private static final String[] MAINHAND_OPTIONS = {
        "mh", "main hand", "mainhand", "m h", "main", "m", "ma"
    };
    private static final String[] OFFHAND_OPTIONS = {
        "oh", "off hand", "offhand", "o h", "off", "o", "of"
    };
    private String name;
    private int level;
    private int age;
    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int chr;
    private int[] hp;
    private int[] mp;
    private int[] xp;
    private Equipment gear;
    private Inventory backpack;
    private InputReader reader;
    /**
     * default constructor for testing players
     */
    public PlayerCharacter()
    {
        reader = new InputReader();
        hp = new int[2];
        mp = new int[2];
        xp = new int[2];
        gear = new Equipment();
        backpack = new Inventory();
        setName("");
        setLevel();
        setStr(3);
        setDex(3);
        setCon(3);
        setIntel(3);
        setWis(3);
        setChr(3);
        setHp();
        setMp();
        setXP();
    }
    
    /**
     * the constructor that is used to make valid player characters
     */
    public PlayerCharacter(String name, int str, int dex, int con, int intel, int wis, int chr)
    {
        reader = new InputReader();
        hp = new int[2];
        mp = new int[2];
        xp = new int[2];
        gear = new Equipment();
        backpack = new Inventory();
        setLevel();
        setName(name);
        setStr(str);
        setDex(dex);
        setCon(con);
        setIntel(intel);
        setWis(wis);
        setChr(chr);
        setHp();
        setMp();
        setXP();
    }
    
    /**
     * Sets the name for the PC
     * @param String
     */
    private void setName(String name)
    {
        if(name != null && !name.isEmpty()){
            this.name = name;
        }
        else{
            this.name = "Traveler";
        }
    }
    
    /**
     * Sets the age of the PC, uses a neutral non-altering age if the age is not acceptable
     * @param int
     */
    private void setAge(int age)
    {
        if(age >= MIN_AGE && age <= MAX_AGE){
            this.age = age;
        }
        else{
            System.out.println("Age was out of bounds of " + MIN_AGE + " and " + MAX_AGE + 
            "age set to 20");
            this.age = age;
        }
    }
    
    /**
     * sets the level for the PC initialized to 1
     */
    private void setLevel()
    {
        this.level = 1;
    }
    
    /**
     * sets the Str for the PC class
     * @param int
     */
    private void setStr(int str)
    {
        if(str >= 3 && str <= 18){
            this.str = str;
        }
        else{
            System.out.println("Str was out of bounds");
            this.str = 3;
        }
    }
    
    /**
     * set the dexterity for the character
     * @param int
     */
    private void setDex(int dex)
    {
        if(dex >= 3 && dex <= 18){
            this.dex = dex;
        }
        else{
            System.out.println("Dex was out of bounds");
            this.dex = 3;
        }
    }
    
     /**
     * set the constitution for the character
     * @param int
     */
    private void setCon(int con)
    {
        if(con >= 3 && con <= 18){
            this.con = con;
        }
        else{
            System.out.println("Con was out of bounds");
            this.con = 3;
        }
    }
    
     /**
     * set the intelligence for the character
     * @param int
     */
    private void setIntel(int intel)
    {
        if(intel >= 3 && intel <= 18){
            this.intel = intel;
        }
        else{
            System.out.println("Intel was out of bounds");
            this.intel = 3;
        }
    }
    
     /**
     * set the wisdom for the character
     * @param int 
     */
    private void setWis(int wis)
    {
        if(wis >= 3 && wis <= 18){
            this.wis = wis;
        }
        else{
            System.out.println("Wis was out of bounds");
            this.wis = 3;
        }
    }
    
     /**
     * set the charisma for the character
     * @param int
     */
    private void setChr(int chr)
    {
        if(chr >= 3 && chr <= 18){
            this.chr = chr;
        }
        else{
            System.out.println("Chr was out of bounds");
            this.chr = 3;
        }
    }
    
    /**
     * sets the players HP based on his Constitution modifier
     */
    private void setHp()
    {
        if(STARTING_HP + getStatMod(getCon()) > 0){
            this.hp[0] = STARTING_HP + (getStatMod(getCon()));
            this.hp[1] = STARTING_HP + (getStatMod(getCon()));
        }
        else{
            this.hp[0] = 1;
            this.hp[1] = 1;
        }
    }
    
    /**
     * sets the players MP based on his Intel modifier
     */
    private void setMp()
    {
        if(STARTING_MP + getStatMod(getIntel()) >= 0){
            this.mp[0] = STARTING_MP + getStatMod(getIntel());
            this.mp[1] = STARTING_MP + getStatMod(getIntel());
        }
        else{
            this.mp[0] = 0;
            this.mp[1] = 0;
        }
    }
    
    /**
     * this initializes the players exp used for leveling up.
     */
    private void setXP()
    {
        xp[0] = 0;
        xp[1] = MAX_XP;
    }
    
    /**
     * returns the characters name in a String format
     * @return String
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * returns the characters name in a String format
     * @return String
     */
    private int getAge()
    {
        return age;
    }
    
    /**
     * returns the characters current level
     * @return int
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * returns the characters Strength as an int
     * @return int
     */
    private int getStr()
    {
        return str;
    }
    
    /**
     * returns the characters Dexterity
     * @return int
     */
    private int getDex()
    {
        return dex;
    }
    
    /**
     * returns the characters Constitution
     * @return int
     */
    private int getCon()
    {
        return con;
    }
    
    /**
     * returns the characters Intelligence
     * @return int
     */
    private int getIntel()
    {
        return intel;
    }
    
    /**
     * returns the characters Wisdom
     * @return int
     */
    private int getWis()
    {
        return wis;
    }
    
    /**
     * returns the characters Charisma
     * @return int
     */
    private int getChr()
    {
        return chr;
    }
    
    /**
     * returns the stat mod value for the stat used for various effects
     */
    private int getStatMod(int stat)
    {
        return ((stat / 2) -5);
    }
    
    /**
     * takes the amount of XP earned and returns true if the player has leveled up
     * @param int
     */
    public void addXP(int xp)
    {
        if(this.xp[0] + xp >= MAX_XP){
            this.xp[0] = this.xp[0] + xp;
            while(this.xp[0] >= MAX_XP){
                System.out.println("Level Up!");
                levelUp();
                if(this.xp[0] - MAX_XP > 0){
                    this.xp[0] -= MAX_XP;
                }
            }
        }
        else{
            this.xp[0] += xp;
        }
    }
    
    /**
     * levels up the player character
     */
    private void levelUp()
    {
        getLevel();
        level++;
        System.out.println(getName() + " has reached level " + getLevel());
    }
    
    /**
     * takes the item passed and makes sure that no 
     * @param Item
     */
    private void checkWeapons(Item item)
    {
        if(gear.hasGear("both hands") && item.getType().equalsIgnoreCase("1hweapon")){
            System.out.println(getName() + "'s " + gear.getGear("both hands").getName() + " has been removed");
        }
        if(gear.hasGear("both hands") && item.getType().equalsIgnoreCase("mhweapon")){
            System.out.println(getName() + "'s " + gear.getGear("both hands").getName() + " has been removed");
        }
        if(gear.hasGear("off hand") && item.getType().equalsIgnoreCase("2hweapon")){
            System.out.println(getName() + "'s " + gear.getGear("off hand").getName() + " has been removed");
        }
        if(gear.hasGear("main hand") && item.getType().equalsIgnoreCase("2hweapon")){
            System.out.println(getName() + "'s " + gear.getGear("main hand").getName() + " has been removed");
        }
        if(gear.hasGear(item.getEquipString())){
            System.out.println(getName() + "'s " + gear.getGear(item.getEquipString()).getName() + 
            " has been removed");
        }
    }
    
    /**
     * loots an item to the players inventory
     * @param item
     */
    public void lootItem(Item item)
    {
        if(backpack.lootItem(item)){
            System.out.println(getName() + " has looted " + item.getName());
        }
        else{
            System.out.println(getName() + "'s inventory is full");
        }
        backpack.sort();
    }
    
    /**
     * Displays the contents of the players inventory
     */
    public void showInventory()
    {
        System.out.println(getName() + "'s Backpack: ");
        System.out.println("------------------------------");
        System.out.println("Free Space: " + backpack.bagSpace() + "/" + backpack.length());
        System.out.println();
        backpack.showBag();
    }
    
    /**
     * Drops an item from the player onto the floor of the room, it is passed to the rooms Inventory item
     * @param int
     */
    public void dropItem(int drop)
    {
        backpack.removeItem(drop);
        backpack.sort();
    }
    
    /**
     * initiates the equip dialogue takes the item returned by equip stores it, removes the item you
     * equipped and puts the equipped item back into inventory
     */
    public void equip()
    {
        int input = 0;
        while(input == 0){
            backpack.showBag();
            System.out.println();
            System.out.println("Which item would you like to equip?");
            System.out.print("> ");
            input = reader.readInt();
        }
        if(input < backpack.length()){
            equipLogic(backpack.removeItem(input-1));
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
    private void equipLogic(Item item)
    {
        Inventory wasEquipped = new Inventory();
        if(backpack.bagSpace() <= 1 && item.getEquipString().equals("both hands")
        && gear.hasGear("main hand") && gear.hasGear("off hand")){
              System.out.println(getName() + "'s" + " backpack is full");
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
                            if(gear.hasGear("main hand")){
                                System.out.println(getName() + "'s " + gear.getGear("main hand").getName() + " has been removed");
                            }
                            checkWeapons(item);
                            wasEquipped.lootItem(gear.unequip("main hand"));
                            wasEquipped.lootItem(gear.unequip("both hands"));
                            gear.equipPlayer(getName(), item, "main hand");
                            ask = false;
                        }
                    }
                    for(int i=0; i < OFFHAND_OPTIONS.length; i++){
                        if(input.equalsIgnoreCase(OFFHAND_OPTIONS[i])){
                            if(gear.hasGear("off hand")){
                                System.out.println(getName() + "'s " + gear.getGear("off hand").getName() + " has been removed");
                            }
                            checkWeapons(item);
                            wasEquipped.lootItem(gear.unequip("off hand"));
                            wasEquipped.lootItem(gear.unequip("both hands"));
                            gear.equipPlayer(getName(), item, "off hand");
                            ask = false;
                        }
                    }
                    if(ask){
                        System.out.println("You can equip to main hand or off hand");
                    }
                }
            }
            else if(item.getEquipString().equals("both hands")){
                checkWeapons(item);
                wasEquipped.lootItem(gear.unequip("both hands"));
                wasEquipped.lootItem(gear.unequip("main hand"));
                wasEquipped.lootItem(gear.unequip("off hand"));
                gear.equipPlayer(name, item);
            }
            else{
                gear.equipPlayer(name, item);
            }
        }
        if(backpack.transfer(0, wasEquipped)){
        }
        if(backpack.transfer(1, wasEquipped)){
        }
    }
    
    /**
     * a method to print out your currently equipped items
     */
    public void showEquip()
    {
        System.out.println("Equipment: " + name);
        gear.showEquip();
    }
    
    /**
     * returns the min and max damage for a combat round
     * @return int[2]
     */
    public int[] getDamage()
    {
        int[] mod = new int[2];
        mod[0] = getStatMod(str);
        mod[1] = getStatMod(str);
        mod[0] += gear.getDamage()[0];
        mod[1] += gear.getDamage()[1];
        if(mod[0] <= 0){
            mod[0] = 0;
        }
        if(mod[1] <= 0){
            mod[1] = 0;
        }
        mod[0]++;
        mod[1]++;
        return mod;
    }
    
    /**
     * returns the ToHit modifier and Damage modifier for the attack round
     * @return int
     */
    public int getAttackMod()
    {
        return gear.getAttackMod();
    }
    
    /**
     * returns the Defence modifier for when player is attacked
     * @return int
     */
    public int getDefenseMod()
    {
        return gear.getDefenseMod();
    }
    
    /**
     * deals damage to the player in the amount of the value passed
     * @param int
     */
    public void damage(int change)
    {
        if(hp[0] - change >= 0){
            hp[0] -= change;
        }
        else{
            hp[0] = 0;
        }
    }
    
    /**
     * heals the character for the amount of the value passed
     * @param int
     */
    public void heal(int change)
    {
        if(hp[0] + change < hp[1]){
            hp[0] += change;
        }
        else{
            hp[0] = hp[1];
        }
    }
    
    /**
     * returns the characters armor class for use when being attacked
     * @return int
     */
    public int getArmorClass()
    {
        int ac = BASE_AC + getDefenseMod() + getStatMod(dex);
        return ac;
    }
    
    /**
     * returns the characters attackBonus which will modify melee weapon damage and toHit
     * @return int
     */
    public int getAttackBonus()
    {
        return (getAttackMod() + getStatMod(str));
    }
    
    /**
     * returns true if the character is dead
     * @return boolean
     */
    public boolean isDead()
    {
        if(hp[0] == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
