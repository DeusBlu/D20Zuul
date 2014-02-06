import java.util.ArrayList;
/**
 * a subclass of item that the player will equip
 * @author Deus
 * @version 0.1_8
 */
public abstract class Gear extends Item{
	private static final String[] STATS = {
		"str", "dex", "con", "intel", "wis", "chr"
	};
	private static final String[] EQUIP_SPOTS = {
		"Main Hand", "Off Hand", "Two Hands", "Head",
		"Shoulders", "Chest", "Hands", "Legs", "Feet"
	};
	private DiceSet diceSet;
	private int defense;
	private int magicMod;
	private int hitMod;
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
		diceSet = new DiceSet();
		setDefense(0);
		setMagicMod(0);
		setHitMod(0);
		setStatMod("none", 0);
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
		diceSet = new DiceSet(dice, sides, plus);
		setDefense(defense);
		setMagicMod(magicBonus);
		setHitMod(hitBonus);
		setStatMod(statToMod, statMod);
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
	private void setMagicMod(int magicBonus)
	{
		if(magicBonus > 0){
			this.magicMod = magicBonus;
		}
		else{
			this.magicMod = 0;
		}
	}
	
	/**
	 * sets the hit bonus without adding a damage bonus
	 */
	private void setHitMod(int hitBonus)
	{
		if(hitBonus > 0){
			this.hitMod = hitBonus;
		}
		else{
			this.hitMod = 0;
		}
	}
	
	/**
	 * sets the stat bonus
	 * @param String - stat to mod valid (str, dex, con, intel, wis, chr)
	 * @param int - amount the stat is changed, CAN BE NEGATIVE!
	 */
	private void setStatMod(String statToMod, int statMod)
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
	protected void setEquipSpots(String spot)
	{
		if(spot != null){
			for(int i = 0; i < EQUIP_SPOTS.length; i++){
				if(EQUIP_SPOTS[i].equalsIgnoreCase(spot)){
					equipSpots.add(spot);
				}
			}
		}
		if(equipSpots.isEmpty()){
			throw new IllegalArgumentException("EquipSpot of " + getName() + 
					" was invalid");
		}
	}
	
	/**
	 * rolls the damage for the weapon and returns it modified with the damage mod
	 * returns just the bonus if no damage range
	 * @return int
	 */
	public int getDamage()
	{
		return (diceSet.getDamage() + diceSet.getPlus());
	}
	
	/**
	 * returns the damage as a string for display
	 * @return String
	 */
	public String showDamage()
	{
		if(diceSet != null){
			return "" + diceSet.getNumber() + "d" + diceSet.getSides() + "+" + (diceSet.getPlus() + magicMod);
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
		return magicMod;
	}
	
	/**
	 * returns the hit bonus of the weapon (magicBonus + hitBonus)
	 * @return int
	 */
	public int getHitBonus()
	{
		return hitMod + magicMod;
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
    
    /**
     * prints the details about the item
     */
    public void printDetails()
    {
    	System.out.println("Item Name: " + getName());
        System.out.println("Item Type: " + getType());
        System.out.println("Item Weight: " + getWeight() + "lbs");
        if(diceSet != null){
        	System.out.println("Damage: " + showDamage());
        }
        if(hitMod > 0){
        	System.out.println("+" + hitMod + " to hit roll");
        }
        if(defense > 0){
        	System.out.println("Defense: " + defense);
        }
        if(statToMod != null && !statToMod.isEmpty()){
        	System.out.println("Gives " + statMod + " to " + statToMod);
        }
        System.out.println("Value: " + printValue());
    }
}
