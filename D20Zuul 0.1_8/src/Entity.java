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
	private AbilityList abilityList;
	private String name;
	private Stats stats;
	private Resist resist;
	private int armor;
	private int init;
	private Inventory backpack;
	private Equipment gear;
	private DiceSet damage;
	
	/**
	 * default constructor for Entity
	 */
	public Entity()
	{
		hp = new int[2];
		mp = new int[2];
		abilityList = new AbilityList();
		damage = new DiceSet();
		backpack = new Inventory();
		gear = new Equipment();
		stats = new Stats();
		resist = new Resist();
		init = 0;
		setName("");
		setArmor(0);
		setMP(0);
	}
	

	public Entity(String name, 
				  int str, 
				  int dex, 
				  int con, 
				  int intel, 
				  int wis, 
				  int chr,  
				  int armor, 
				  int fort, 
				  int reflex, 
				  int will, 
				  int dDice, 
				  int dDie, 
				  int dBonus,
				  int mpMod)
	{
		hp = new int[2];
		mp = new int[2];
		abilityList = new AbilityList();
		damage = new DiceSet();
		stats = new Stats(str, dex, con, intel, wis, chr);
		resist = new Resist(fort, reflex, will);
		backpack = new Inventory();
		gear = new Equipment();
		init = 0;
		setDamage(dDice, dDie, dBonus);
		setName(name);
		setArmor(armor);
		setMP(mpMod);
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
	 * sets the base armor for the class
	 * @param armor
	 */
	private void setArmor(int armor) 
	{
		this.armor = armor;
	}
	
	/**
	 * sets the BASE unarmed damage, do not apply str mods here they are applied at the return
	 * Tiny creature - (0, 0, 1), Small (1, 2, 0), Medium (1, 3, 0), Large (1, 4, 0), Huge, (1, 6, 0)
	 * @param dDice
	 * @param dDie
	 * @param dBonus
	 */
	private void setDamage(int dDice, int dDie, int dBonus)
	{
		if(dDice > 0 && dDie > 0){
			damage = new DiceSet(dDice, dDie, dBonus);
		}
		else{
			damage = new DiceSet(1, 3, 0);
		}
	}
	
	/**
	 * sets the beginning HP
	 * @param starting HP of the creature
	 */
	protected void setHP(int hp)
	{
		if(hp > 0){
			this.hp[0] = hp;
			this.hp[1] = hp;
		}
		else{
			this.hp[0] = 1;
			this.hp[1] = 1;
		}
	}


	protected void addHP(int hp)
	{
		if(hp > 0){
			this.hp[0] += hp;
			this.hp[1] += hp;
		}
		else{
			this.hp[0] += 1;
			this.hp[1] += 1;
		}
	}


	/**
	 * sets the starting MP based on Intel and Wis
	 */
	protected void setMP(int level)
	{
		int startMP = getStatMod("wis") + 
				getStatMod("intel");
		if(startMP + level >= 0){
			mp[0] = startMP + level;
			mp[1] = startMP + level;
		}
		else{
			mp[0] = 0;
			mp[1] = 0;
		}
	}
	
	protected void addAbility(Ability ability)
	{
		if(ability != null){
			abilityList.addAbility(ability);
		}
	}


	/**
	 * returns the players current HP
	 * @return current HP as int
	 */
	public int getCurrentHP()
	{
		return hp[0];
	}
	
	/**
	 * returns the current MP
	 * @return MP as an int
	 */
	public int getCurrentMP()
	{
		return mp[0];
	}
	
	/**
	 * returns the HP as an int
	 * @return max HP as an int
	 */
	public int getMaxHP()
	{
		return hp[1];
	}
	
	/**
	 * returns the max MP as an int
	 * @return max MP as an int
	 */
	public int getMaxMP()
	{
		return mp[1];
	}
	
	public AbilityList getAbilitys()
	{
		return abilityList;
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
	 * returns the unmodified base stat
	 * @param stat
	 * @return int
	 */
	public int getBaseStat(String stat)
	{
		return stats.getStat(stat);
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
	 * returns the +/- mod of the stat
	 * @param stat
	 * @return int
	 */
	public int getStatMod(String stat)
	{
		return ((getStat(stat)/2)-5);
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


	/**
	 * returns the armor mod for combat
	 * @return int
	 */
	public int getArmor()
	{
		return armor + getStatMod("dex");
	}


	/**
	 * returns the initiative with the dex mod included for combat
	 * @return int
	 */
	public int getInit()
	{
		return init;
	}


	/**
	 * returns the Inventory item for managing
	 * @return Inventory
	 */
	public Inventory getBackpack()
	{
		return backpack;
	}


	/**
	 * returns the Gear item for managing
	 * @return Equipment
	 */
	public Equipment getGear()
	{
		return gear;
	}


	/**
	 * returns the randomized and modified damage for the entity
	 * @return int
	 */
	public int getDamage()
	{
		if(!gear.hasGear("Both Hands") || !gear.hasGear("Main Hand") ||
		   !gear.getGear("Off Hand").getType().equals("1hweapon")){
			return (damage.getRoll() + getStatMod("Str"));
		}
		else{
			return gear.getDamage() + getStatMod("Str");
		}
	}


	/**
	 * deal damage to the life total returns true if dead
	 * @param damage
	 * @return boolean
	 */
	public boolean takeDamage(int damage)
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
	public int healDamage(int heal)
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
	
	public void setInit(int init)
	{
		this.init = init + getStatMod("dex");
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
	
	/**
	 * adds a ability to the list of abilitys of the entity
	 * @param ability
	 */
	public void learnAbility(Ability ability)
	{
		if(ability != null){
			abilityList.addAbility(ability);
		}
		else{
			throw new IllegalArgumentException(getName()+" ability was null");
		}
	}


	/**
	 * returns the modifier tohit for melee weapons
	 * @return int
	 */
	public int getMeleeAttackMod()
	{
		return gear.getAttackMod() + getStatMod("str");
	}


	/**
	 * returns the toHit mod for ranged weapons
	 * @return int
	 */
	public int getRangedAttackMod()
	{
		return gear.getAttackMod() + getStatMod("dex");
	}
}
