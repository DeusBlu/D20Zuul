
/**
 * Write a description of class TEST here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TEST
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class TEST
     */
    public TEST()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
       if(toEquip.getType().equals("2hweapon") && equipment.isDualWield()){
			if(backpack.bagSpace() <= 1){
				System.out.println("Your backpack is full");
				wasEquipped.lootItem(toEquip);
			}
			else{
				if(replace(toEquip.getEquipString())){
					wasEquipped.lootItem(equipment.unequip("Main Hand"));
					wasEquipped.lootItem(equipment.unequip("Off Hand"));
					System.out.println(toEquip.getName() + " was equipped!");
					equipment.equip(toEquip.getEquipString(), toEquip);
				}
				else{
					System.out.println(toEquip.getName() + " was returned to your bag");
					wasEquipped.lootItem(toEquip);
				}
			}
		}
		else if(toEquip.getType().equals("1hweapon")){
			String hand = weaponEquip();
			if(replace(hand)){
				wasEquipped.lootItem(equipment.unequip(hand));
				wasEquipped.lootItem(equipment.unequip("Both Hands"));
				System.out.println(toEquip.getName() + " was equipped!");
				equipment.equip(hand, toEquip);
			}
			else{
				System.out.println(toEquip.getName() + " was returned to your bag");
				wasEquipped.lootItem(toEquip);
			}
		}
		else{
			if(replace(toEquip.getEquipString())){
				Gear lootGear = gearCheck(toEquip);
				if(lootGear != null){
					wasEquipped = equipment.equip();
					
				}
				else{
					wasEquipped.lootItem(equipment.unequip(toEquip.getEquipString()));
				}
				System.out.println(toEquip.getName() + " was equipped!");
				equipment.equip(toEquip.getEquipString(), toEquip);
			}
			else{
				System.out.println(toEquip.getName() + " was returned to your bag");
				wasEquipped.lootItem(toEquip);
			}
		}
		
		
    }
    
    
	
	/**
	 * checks for 2hweapon, mhweapon, ohweapon and makes sure proper equipment
	 * changes happen
	 * @param Gear - item to equip
	 * @return Gear - the removed item
	 */
	private Gear gearCheck(Gear toEquip)
	{
		if(toEquip.getType().equals("2hweapon") ||
		   toEquip.getType().equals("mhweapon")){
			if(equipment.hasGear("Both Hands")){
				return equipment.unequip("Both Hands");
			}
			else if(equipment.hasGear("Main Hand")){
				return equipment.unequip("Main Hand");
			}
			else if(equipment.hasGear("Off Hand")){
				return equipment.unequip("Off Hand");
			}
			else{
				return equipment.unequip(toEquip.getEquipString());
			}
		}
		return null;
	}
}
