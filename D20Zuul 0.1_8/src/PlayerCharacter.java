
/**
 * This class is used to create players, it can make NPC's, PC's, and can be stored in the party for
 * controlling multiple characters
 * 
 * @author (DeusBlu) 
 * @version (0.1_3)
 */
public class PlayerCharacter extends Entity
{
    // instance variables - replace the example below with your own
    private static final int STARTING_HP = 10;
    private static final int STARTING_MP = 1;
    private static final int BASE_AC = 10;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 75;
    private static final int MAX_XP = 100;
    private String name;
    private int level;
    private int age;
    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int chr;
    private int init;
    private int[] hp;
    private int[] mp;
    private int[] xp;
    private Equipment gear;
    private Inventory backpack;
    /**
     * default constructor for testing players
     */
    public PlayerCharacter()
    {
        hp = new int[2];
        mp = new int[2];
        xp = new int[2];
        gear = new Equipment();
        backpack = new Inventory();
        setName("");
        setAge(MIN_AGE);
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
        init = 0;
    }
    
    /**
     * the constructor that is used to make valid player characters
     */
    public PlayerCharacter(String name, int age, int str, int dex, int con, int intel, int wis, int chr)
    {
        hp = new int[2];
        mp = new int[2];
        xp = new int[2];
        gear = new Equipment();
        backpack = new Inventory();
        setLevel();
        setName(name);
        setAge(age);
        setStr(str);
        setDex(dex);
        setCon(con);
        setIntel(intel);
        setWis(wis);
        setChr(chr);
        setHp();
        setMp();
        setXP();
        init = 0;
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
            "age set to 18");
            this.age = MIN_AGE;
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
        if(STARTING_HP + getStatMod(con) > 0){
            this.hp[0] = STARTING_HP + (getStatMod(con));
            this.hp[1] = STARTING_HP + (getStatMod(con));
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
        if(STARTING_MP + getStatMod(intel) >= 0){
            this.mp[0] = STARTING_MP + getStatMod(intel);
            this.mp[1] = STARTING_MP + getStatMod(intel);
        }
        else{
            this.mp[0] = 0;
            this.mp[1] = 0;
        }
    }
    
    /**
     * sets the players initiative for a round of combat
     * @param int - the roll of the dice
     */
    public void setInit(int roll)
    {
        init = roll + getStatMod(dex);
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
    public int getAge()
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
     * returns the stat mod value for the stat used for various effects
     */
    private int getStatMod(int stat)
    {
        return ((stat / 2) -5);
    }
    
    /**
     * this method returns the players initiate for turn order sequencing
     */
    public int getInit()
    {
        return init;
    }
    
    /**
     * returns the players HP as a string for status purposes
     * @return String
     */
    public String showHP()
    {
        return "" + hp[0] + "/" + hp[1];
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
     * adds an item to the equipment object interacting with the bag object for transfer
     */
    public void equip()
    {
        gear.equip(backpack, name);
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
    
    /**
     * prints the status of the player
     */
    public void status()
    {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp[0] + "/" + hp[1]);
        System.out.println("MP: " + mp[0] + "/" + mp[1]);
        System.out.println();
        System.out.println("Strength: " + str);
        System.out.println("Dexterity: " + dex);
        System.out.println("Constitution: " + con);
        System.out.println("Intelligence" + intel);
        System.out.println("Wisdom: " + wis);
        System.out.println("Charisma: " + chr);
        System.out.println();
        System.out.println("XP: " + xp[0] + "/" + xp[1]);
        System.out.println();
        System.out.println("Current Gear: ");
        System.out.println("-------------------------");
        gear.showEquip();
    }
}
