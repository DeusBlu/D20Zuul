/**
 * a base class for anything living in the game
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Entity 
{
	private int hp[];
	private int mp[];
	private String name;
	private Stats stats;
	private Resist resist;
	private int armor;
	private Inventory backpack;
	private Equipment gear;
	
	/**
	 * default constructor for Entity
	 */
	public Entity()
	{
		hp = new int[2];
		mp = new int[2];
		backpack = new Inventory();
		gear = new Equipment();
		stats = new Stats();
		resist = new Resist();
		setName("");
		setArmor(0);
		setHP(0, 0);
		setMP();
	}
	

	public Entity(String name, int str, int dex, int con, int intel, int wis, int chr, 
				  int armor, int numberDice,  int hpDie, int fort, int reflex, int will)
	{
		hp = new int[2];
		mp = new int[2];
		stats = new Stats(str, dex, con, intel, wis, chr);
		resist = new Resist(fort, reflex, will);
		backpack = new Inventory();
		gear = new Equipment();
		setName(name);
		setArmor(armor);
		setHP(numberDice, hpDie);
		setMP();
	}

	/**
	 * sets the name to unknown if null or empty
	 * @param name
	 */
	private void setName(String name) {
		if(name != null && !name.isEmpty()){
			this.name = name;
		}
		else{
			this.name = "Unknown";
		}
	}
	
	/**
	 * sets the beginning HP
	 * @param numberDice
	 * @param hpDie
	 */
	private void setHP(int numberDice, int hpDie)
	{
		DiceSet hpDice = new DiceSet(numberDice, hpDie, getStatMod(stats.getStat("con")));
		int startHP = hpDice.getRoll();
		if(startHP > 0){
			hp[0] = startHP;
			hp[1] = startHP;
		}
		else{
			hp[0] = 1;
			hp[1] = 1;
		}
	}
	
	/**
	 * sets the base armor for the class
	 * @param armor
	 */
	private void setArmor(int armor) 
	{
		this.armor = armor;
	}
	
	/**
	 * returns the players current HP
	 * @return
	 */
	public int getCurrentHP()
	{
		return hp[0];
	}
	
	/**
	 * prints the HP as a string
	 */
	public int getMaxHP()
	{
		return hp[1];
	}
	
	/**
	 * deal damage to the life total returns true if dead
	 * @param damage
	 * @return boolean
	 */
	public boolean damage(int damage)
	{
		if(damage <= hp[0]){
			hp[0] -= damage;
			return true;
		}
		else{
			hp[0] = 0;
			return false;
		}
	}
	
	/**
	 * heals the player for the amount and returns the overheal
	 * @param healing
	 * @return int
	 */
	public int heal(int heal)
	{
		int overheal = 0;
		if(hp[0] + heal > hp[1]){
			overheal = heal - (hp[1] - hp[0]);
			hp[0] = hp[1];
		}
		else{
			hp[0] += heal;
		}
		return overheal;
	}
	
	/**
	 * sets the starting MP based on Intel and Wis
	 */
	public void setMP()
	{
		int startMP = getStatMod(stats.getStat("wis") + getStatMod(stats.getStat("intel")));
		mp[0] = startMP;
		mp[1] = startMP;
	}
	
	/**
	 * spends the mp returns true if the spell was cast
	 * @param damage
	 * @return boolean
	 */
	public boolean spendMP(int spent)
	{
		if(spent <= mp[0]){
			mp[0] -= spent;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * restores the mp
	 * @param healing
	 */
	public void restoreMP(int restore)
	{
		if(mp[0] + restore > mp[1]){
			mp[0] = mp[1];
		}
		else{
			mp[0] += restore;
		}
	}
	
	/**
	 * returns the amount of free mp
	 * @return int
	 */
	public int freeMP()
	{
		return mp[1] - mp[0];
	}
	
	/**
	 * returns the name
	 * @return String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * returns the value of a stat
	 * @param stat
	 * @return int
	 */
	public int getStat(String stat)
	{
		return gear.getStatMod(stat) + stats.getStat(stat);
	}
	
	/**
	 * returns the unmodified base stat
	 * @param stat
	 * @return int
	 */
	public int getBaseStat(String stat)
	{
		return stats.getStat(stat);
	}
	
	/**
	 * returns the +/- mod of the stat
	 * @param stat
	 * @return int
	 */
	public int getStatMod(int stat)
	{
		return ((stat/2)-5);
	}
	
	/**
	 * returns the value of the resist
	 * @param resist
	 * @return int
	 */
	public int getResist(String resist)
	{
		return this.resist.getResist(resist);
	}
	
	public int getInit()
	{
		return getStatMod(stats.getStat("dex"));
	}
	
	/**
	 * returns true the entity is dead
	 * @return boolean
	 */
	public boolean isDead()
	{
		if(hp[0] <= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * returns the armor mod for combat
	 * @return int
	 */
	public int getArmor()
	{
		return armor + getStatMod(stats.getStat("dex"));
	}
	
	/**
	 * returns the modifier tohit for melee weapons
	 * @return int
	 */
	public int getMeleeAttackMod()
	{
		return gear.getAttackMod() + getStatMod(stats.getStat("str"));
	}
	
	/**
	 * returns the toHit mod for ranged weapons
	 * @return int
	 */
	public int getRangedAttackMod()
	{
		return gear.getAttackMod() + getStatMod(stats.getStat("dex"));
	}
	
	/**
	 * equips an item to the player
	 * @param spot
	 * @param gear
	 */
	public void equip(String spot, Gear gear)
	{
		this.gear.equip(spot, gear);
	}
	
	/**
	 * loots an item to the inventory
	 * @param item
	 */
	public void loot(Item item)
	{
		backpack.lootItem(item);
	}
}
