import java.util.ArrayList;
/**
 * a subclass of item that the player will equip
 * @author Deus
 * @version 0.1_8
 */
public class Gear extends Item{
	private static final String[] STATS = {
		"str", "dex", "con", "intel", "wis", "chr"
	};
	private Damage damage;
	private int defense;
	private int magicBonus;
	private int hitBonus;
	private String statToMod;
	private int statMod;
	private String type;
	private ArrayList<String> equipSpots;
	
	/**
	 * default constructor for Gear
	 */
	public Gear()
	{
		super();
		equipSpots = new ArrayList<String>();
		damage = new Damage();
		setDefense(0);
		setMagicBonus(0);
		setHitBonus(0);
		setStatBonus("none", 0);
		setType("");
	}
	
	/**
	 * constructor for making various pieces of gear that can be equipped
	 * @param double - weight of item in lbs
	 * @param int - value of item in copper
	 * @param String - item Name
	 * @param int - number of dice for damage
	 * @param int - number of sides of the dice
	 * @param int - the plus to damage
	 * @param int - defense of the item CAN BE NEGATIVE!!!
	 * @param int - magical bonus to hit/damage (Battle Axe +5 is 5 magical bonus)
	 * @param String - the type of item
	 * @param String - the stat that will be modded set to none if not recognized
	 * @param int - the amount the stat is modded, CAN BE NEGATIVE!
	 */
	public Gear(double weight, int value, String name, int dice, int sides, int plus, int defense, int magicBonus, int hitBonus, String statToMod, int statMod, String type)
	{
		super(weight, value, name);
		equipSpots = new ArrayList<String>();
		damage = new Damage(dice, sides, plus);
		setDefense(defense);
		setMagicBonus(magicBonus);
		setHitBonus(hitBonus);
		setStatBonus(statToMod, statMod);
		setType(type);
	}
	
	/**
	 * sets the defense value of the item
	 * @param int
	 */
	private void setDefense(int defense)
	{
		this.defense = defense;
	}
	
	/**
	 * sets the magic bonus of the weapon (Battle Axe +5 is 5 magical bonus)
	 */
	private void setMagicBonus(int magicBonus)
	{
		if(magicBonus > 0){
			this.magicBonus = magicBonus;
		}
		else{
			this.magicBonus = 0;
		}
	}
	
	/**
	 * sets the hit bonus without adding a damage bonus
	 */
	private void setHitBonus(int hitBonus)
	{
		if(hitBonus > 0){
			this.hitBonus = hitBonus;
		}
		else{
			this.hitBonus = 0;
		}
	}
	
	/**
	 * sets the stat bonus
	 * @param String - stat to mod valid (str, dex, con, intel, wis, chr)
	 * @param int - amount the stat is changed, CAN BE NEGATIVE!
	 */
	private void setStatBonus(String statToMod, int statMod)
	{
		for(int i = 0; i < STATS.length; i++){
			if(statToMod.equalsIgnoreCase(STATS[i])){
				this.statToMod = STATS[i];
				this.statMod = statMod;
			}
		}
		if(this.statToMod == null){
			this.statToMod = "none";
			this.statMod = 0;
		}
	}
	
	/**
	 * sets the type of gear this item is passed from child Class
	 * @param String
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	/**
	 * this method sets the equip spots 
	 * @param String
	 */
	public void setEquipSpots(String spot)
	{
		if(spot != null){
			equipSpots.add(spot);
		}
		else{
			throw new IllegalArgumentException("EquipSpot of " + getName() + 
					" was null");
		}
	}
	
	/**
	 * rolls the damage for the weapon and returns it modified with the damage mod
	 * @return int
	 */
	public int getDamage()
	{
		return (damage.getDamage() + damage.getPlus());
	}
	
	/**
	 * returns the damage as a string for display
	 * @return String
	 */
	public String showDamage()
	{
		if(damage != null){
			return "" + damage.getNumber() + "d" + damage.getSides() + "+" + (damage.getPlus() + magicBonus);
		}
		else{
			return null;
		}
	}
	
	/**
	 * returns the defense value of the item
	 * @return int
	 */
	public int getDefense()
	{
		return defense;
	}
	
	/**
	 * returns the magical bonus of the item (Battle Axe +5 returns 5)
	 */
	public int getMagicBonus()
	{
		return magicBonus;
	}
	
	/**
	 * returns the hit bonus of the weapon (magicBonus + hitBonus)
	 * @return int
	 */
	public int getHitBonus()
	{
		return hitBonus + magicBonus;
	}
	
	/**
	 * returns the stat that is moded by this item
	 * @return String
	 */
	public String getStatToMod()
	{
		return statToMod;
	}
	
	/**
	 * returns the amount the stat in statToMod is changed
	 * @return int
	 */
	public int getStatMod()
	{
		return statMod;
	}
	
	/**
	 * returns the item type
	 * @return String
	 */
	public String getType()
	{
		return type;
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
}
