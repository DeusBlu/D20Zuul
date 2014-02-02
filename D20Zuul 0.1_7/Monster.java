/**
 * A class to create monsters for the PC to fight
 * 
 * @author (DeusBlu) 
 * @version (0.1_3)
 */
public class Monster
{
    private static final Dice dice = new Dice();
    private static final int BASE_MP = 5;
    private static final int MIN_XP = 1;
    private static final int MIN_DMG_MIN = 0;
    private static final int MIN_DMG_MAX = 1;
    private String name;
    private int[] hp;
    private int[] mp;
    private Inventory lootTable;
    private int[] damage;
    private int armor;
    private int level;
    private int hitBonus;
    private int xp;
    private boolean mythic;
    private boolean hasMagic;
    private int init;
    private int initMod;
    /**
     * default constructor for testing objects of class Monster
     */
    public Monster()
    {
        hp = new int[2];
        mp = new int[2];
        lootTable = new Inventory();
        damage = new int[2];
        setName("");
        setArmor(0);
        setLevel(1);
        setXP(1);
        setHitBonus(0);
        setMythic(false);
        setMagic(false);
        setHP(level, 6);
        setMP(level, false);
        setDamage(0, 1);
        setInitMod(0);
        init = 0;
    }
    
    /**
     * This is the constructor for any type of monster, all values can be taken
     * @param String - monsters name
     * @param int - armor
     * @param int - level
     * @param int - how many sides the hp die has
     * @param int - the minimum damage of the monster
     * @param int - the maximum damage of the monster
     * @param int - difficulty
     * @param boolean - if the monster is a special mythic monster
     * @param boolean - can the monster use magic
     */
    public Monster(String name, int armor, int level, int hpDie, int dmgMin, int dmgMax, 
    int xp, int hitBonus, int initMod, boolean isMythic, boolean hasMagic)
    {
        hp = new int[2];
        mp = new int[2];
        lootTable = new Inventory();
        damage = new int[2];
        setName(name);
        setArmor(armor);
        setLevel(level);
        setXP(xp);
        setMythic(isMythic);
        setMagic(hasMagic);
        setHitBonus(hitBonus);
        setHP(level, hpDie);
        setMP(level, hasMagic);
        setDamage(dmgMin, dmgMax);
        setInitMod(initMod);
        init = 0;
    }
    
    /**
     * sets the name of the monster
     * @param String
     */
    private void setName(String name)
    {
        if(name != null && !name.isEmpty()){
            this.name = name;
        }
        else{
            this.name = "unknown";
        }
    }
    
    /**
     * sets the monsters HP based on their level and base HP
     * @param int - level
     * @param int - baseHP
     */
    private void setHP(int level, int hpDie)
    {
        for(int i = 0; i < level; i++){
            int hp = dice.roll(level, hpDie);
            this.hp[0] += hp;
        }
        if(this.hp[0] <= 4){
            this.hp[0] = 4;
        }
        this.hp[1] = this.hp[0];
    }
    
    /**
     * checks if the monster has magic and if it does sets the MP randomly based on its level if it 
     * does not sets MP to 0
     * @param int
     * @param boolean
     */
    private void setMP(int level, boolean hasMagic)
    {
        if(hasMagic){
            for(int i = 0; i <= level; i++){
                int mp = dice.roll(1, BASE_MP);
                this.mp[0] += mp;
            }
            if(this.mp[0] == 0){
                this.mp[0] = 1;
            }
            this.mp[1] = this.mp[0];
        }
        else{
            this.mp[0] = 0;
            this.mp[1] = 0;
        }
    }
    
    /**
     * sets the damage range of the monster based on dmgMin and dmgMax and stores it in the damage array
     * @param int - dmgMin
     * @param int - dmgMax
     */
    private void setDamage(int dmgMin, int dmgMax)
    {
        if(dmgMin >= 0){
            damage[0] = dmgMin;
        }
        else{
            damage[0] = MIN_DMG_MIN;
        }
        if(dmgMax > 0){
            damage[1] = dmgMax;
        }
        else{
            damage[1] = MIN_DMG_MAX;
        }
    }
    
    /**
     * sets the armor value of the monster
     * @param int
     */
    private void setArmor(int armor)
    {
        if(armor >= 0){
            this.armor = armor;
        }
        else{
            armor = 0;
        }
    }
    
    /**
     * sets the bonus to hit rating for the monster
     * @param int
     */
    private void setHitBonus(int hitBonus)
    {
        if(hitBonus >= 0){
            this.hitBonus = hitBonus;
        }
        else{
            this.hitBonus = 0;
        }
    }
    
    /**
     * sets the level of the monster
     * @param int - level
     */
    private void setLevel(int level)
    {
        if(level > 0){
            this.level = level;
        }
        else{
            this.level = 1;
        }
    }
    
    /**
     * sets the difficulty level of the monster with the parameters MIN_DIFF and MAX_DIFF
     * @param int - difficulty
     */
    private void setXP(int XP)
    {
        if(xp >= MIN_XP){
            this.xp = xp;
        }
        else{
            this.xp = 1;
        }
    }
    
    /**
     * sets the monsters initiative for determing turn sequence
     * @param int - the die roll result
     */
    private void setInit(int roll)
    {
        init = roll = initMod;
    }
    
    /**
     * sets the monsters modifier to its initiative
     * @param int
     */
    private void setInitMod(int initMod)
    {
        this.initMod = initMod;
    }
    
    /**
     * sets if the monster is a mythical monster
     * @param boolean
     */
    private void setMythic(boolean mythic)
    {
        this.mythic = mythic;
    }
    
    /**
     * sets if the monster can cast magic which is used for mp determination
     * @param boolean
     */
    private void setMagic(boolean hasMagic)
    {
        this.hasMagic = hasMagic;
    }
    
    /**
     * returns the name of the monster
     * @return String
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * returns the hit bonus for the monster
     * @return int
     */
    public int getHitBonus()
    {
        return hitBonus;
    }
    
    /**
     * returns the current hp as an int ignoring the max HP
     * @return int
     */
    public int getHP()
    {
        return hp[0];
    }
    
    /**
     * returns the current mp as an int ignoring the max MP
     * @return int
     */
    public int getMP()
    {
        return mp[0];
    }
    
    /**
     * returns the min and max damage of the mob respectively as int[0] and int[1]
     * @return int[2]
     */
    public int[] getDamage()
    {
        return damage;
    }
    
    /**
     * returns the armor class of the monster as an int
     * @return int
     */
    public int getArmor()
    {
        return armor;
    }
    
    /**
     * returns the level of the monster as an int
     * @return int
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * returns amount of XP awarded for killing the monster as an int
     * @return int
     */
    public int getXP()
    {
        return xp;
    }
    
    /**
     * returns true if the monster is mythic
     * @return boolean
     */
    public boolean getMythic()
    {
        return mythic;
    }
    
    /**
     * returns true if the monster has magic
     * @return boolean
     */
    public boolean getHasMagic()
    {
        return hasMagic;
    }
    
    /**
     * returns the monsters initiative for turn sequencing
     * @return int
     */
    public int getInit()
    {
        return init;
    }
    
    /**
     * deals damage to the monster in the amount of the value passed
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
