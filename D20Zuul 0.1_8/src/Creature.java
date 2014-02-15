/**
 * constructor for base object Creature
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Creature extends Entity 
{
	private int xpValue;
	
	/**
	 * default constructor for type Creture
	 */
	public Creature() 
	{
		super();
		setXpValue(1);
	}

	/**
	 * Constructor to create a useable creature
	 * @param name
	 * @param str
	 * @param dex
	 * @param con
	 * @param intel
	 * @param wis
	 * @param chr
	 * @param armor
	 * @param numberDice
	 * @param hpDie
	 * @param fort
	 * @param reflex
	 * @param will
	 * @param dDice
	 * @param dDie
	 * @param dBonus
	 * @param xpValue
	 */
	public Creature(String name, 
					int str, 
					int dex, 
					int con, 
					int intel, 
					int wis, 
					int chr, 
					int armor, 
					int numberDice, 
					int hpDie, 
					int fort, 
					int reflex, 
					int will, 
					int dDice, 
					int dDie, 
					int dBonus, 
					int xpValue,
					int mpMod) {
		super(name, str, dex, con, intel, wis, chr, armor, 
				numberDice, hpDie, fort, reflex, will, dBonus, mpMod);
		setHP(numberDice, hpDie);
		setXpValue(xpValue);
	}
	
	/**
	 * sets the xp value of the monster for when it is killed
	 * @param xpValue
	 */
	private void setXpValue(int xpValue)
	{
		if(xpValue > 0){
			this.xpValue = xpValue;
		}
		else{
			this.xpValue = 1;
		}
	}
	
	/**
	 * sets the HP for the creature
	 */
	public void setHP(int numDice, int hpDie)
	{
		DiceSet hpDice = new DiceSet(numDice, hpDie, getStatMod("con"));
		int startHP = hpDice.getRoll();
		if(startHP > 0){
			super.setHP(startHP);
		}
		else{
			super.setHP(1);
		}
	}
	
	/**
	 * returns the xp value for when creature is killed
	 * @return xpValue
	 */
	public int getXpValue()
	{
		return xpValue;
	}

	/**
	 * systematically equips the best items for the creature from their 
	 * backpack - this will be any items that were looted to the monster
	 */
	public void equipItems()
	{
		Inventory backpack = getBackpack();
		for(int i = 0; i < backpack.length(); i++){
			if(backpack.getGear(i) != null){
				Gear newGear = backpack.removeGear(i);
				if(newGear instanceof Weapon){
					weaponEquip(newGear);
				}
				else{
					gearEquip(newGear);
				}
			}
		}
	}

	/**
	 * determines the best place for this piece of gear based on what is
	 * already equipped
	 * @param newGear
	 */
	private void weaponEquip(Gear newGear)
	{
		Equipment gear = getGear();
		Inventory backpack = getBackpack();
		if(newGear.getType().equals("2hweapon")){
			if(gear.getGear("Both Hands") != null){
				if(newGear.getValue() > gear.getGear("Both Hands").getValue()){
					backpack.lootItem(gear.equip("Both Hands", newGear));
				}
			}
			else{
				backpack.lootItem(gear.equip("Main Hand", newGear));
			}
		}
		if(newGear.getType().equals("mhweapon")){
			if(gear.getGear("Main Hand") != null){
				if(newGear.getValue() > gear.getGear("Main Hand").getValue()){
					backpack.lootItem(gear.equip("Main Hand", newGear));
				}
			}
			else{
				backpack.lootItem(gear.equip("Main Hand", newGear));
			}
		}
		if(newGear.getType().equals("1hweapon")){
			if(gear.getGear("Both Hand") == null){
				if(gear.getGear("Main Hand") != null){
					if(!gear.getGear("Main Hand").getType().equals("mhweapon")){
						if(newGear.getValue() > gear.getGear("Main hand").getValue()){
							backpack.lootItem(gear.equip("Main Hand", newGear));
						}
					}
				}
				else if(gear.getGear("Main Hand") == null){
					backpack.lootItem(gear.equip("Main Hand", newGear));
				}
				else if(!gear.getGear("Off Hand").getType().equals("Shield")){
					if(newGear.getValue() > gear.getGear("Off Hand").getValue()){
						backpack.lootItem(gear.equip("Off Hand", newGear));
					}
				}
			}
		}
	}

	/**
	 * equips a piece of gear to the only spot it is able to be equipped to
	 * @param newGear
	 */
	private void gearEquip(Gear newGear)
	{
		if(newGear != null){
			Equipment gear = getGear();
			Inventory backpack = getBackpack();
			if(newGear.getType().equals("Shield")){
				if(gear.getGear("Both Hands") == null){
					if(gear.getGear("Off Hand") != null){
						if(newGear.getValue() > gear.getGear("Off Hand").getValue()){
							backpack.lootItem(gear.equip("Main Hand", newGear));
						}
					}
				}
			}
			else{
				if(gear.getGear(newGear.getEquipString()) != null){
					if(newGear.getValue() > gear.getGear(newGear.getEquipString()).getValue()){
						backpack.lootItem(gear.equip(newGear.getEquipString(), newGear));
					}
				}
				else{
					backpack.lootItem(gear.equip(newGear.getEquipString(), newGear));
				}
				
			}
		}
	}
}
