/**
 * the interface a user will use to equip an item
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class EquipUI 
{
	private InputReader reader;
	private Equipment equipment;
	private Inventory backpack;
	private Inventory wasEquipped;
	private Gear toEquip;
	
	/**
	 * default constructor for EquipUI
	 */
	public EquipUI()
	{
		toEquip = null;
		reader = new InputReader();
		equipment = new Equipment();
		backpack = new Inventory();
		wasEquipped = new Inventory();
	}
	
	/**
	 * constructor specifying the equipment and inventory to use
	 * @param equipment
	 * @param inventory
	 */
	public EquipUI(Player player)
	{
		toEquip = null;
		reader = new InputReader();
		setEquip(player.getGear());
		setInventory(player.getBackpack());
		wasEquipped = new Inventory();
	}
	
	/**
	 * makes sure the Equipment is a valid object
	 * @param equipment
	 */
	private void setEquip(Equipment equipment)
	{
		if(equipment != null){
			this.equipment = equipment;
		}
		else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * makes sure the Inventory is a valid object
	 * @param inventory
	 */
	private void setInventory(Inventory backpack)
	{
		if(backpack != null){
			this.backpack = backpack;
		}
		else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * the main interface for equiping a character
	 */
	public boolean equipUI()
	{
		boolean itemEquipped = false;
		boolean finished = false;
		String equip = null;
		while(!finished){
			while(equip == null){
				backpack.showBag();
				System.out.println("What item would you like to equip?");
				System.out.print("#/Name> ");
				equip = selectEquip();
				if(equip != null && equip.equalsIgnoreCase("end")){
					break;
				}
			}
			toEquip = getEquip(equip);
			if(toEquip != null){
				if(toEquip.getType().equals("2hweapon")){
					equip2H();
					reader.readString();
					itemEquipped = true;
				}
				else if(toEquip.getType().equals("1hweapon")){
					equipWeapon(weaponEquip());
					reader.readString();
					itemEquipped = true;
				}
				else if(toEquip.getType().equals("mhweapon")){
					equipWeapon("Main Hand");
					reader.readString();
					itemEquipped = true;
				}
				else{
					equipGear();
					reader.readString();
					itemEquipped = true;
				}
			}
			backpack.transfer(0, wasEquipped);
			backpack.transfer(1, wasEquipped);
			backpack.sort();
			finished = equipAgain();
			if(!finished){
				equip = null;
			}
		}
		return itemEquipped;
	}
	
	private String selectEquip()
	{
		String input = reader.readString();
		if(input.equalsIgnoreCase("end")){
			return input;
		}
		for(int i = 1; i <= backpack.length(); i++){
			if(input.equals(((Integer)i).toString()) && 
					backpack.getItem(i-1) != null){
				if(backpack.getItem(i-1) instanceof Gear){
					return input;
				}
			}
		}
		for(int i = 0; i < backpack.length(); i++){
			if(backpack.getItem(i) != null &&
					backpack.getItem(i).getName().equalsIgnoreCase(input)){
				if(backpack.getItem(i) instanceof Gear){
					return input;
				}
			}
		}
		System.out.println("That is not valid gear please enter the name");
		System.out.println("of the gear or the location # or type end");
		return null;
	}
	
	private Gear getEquip(String equip)
	{
		for(int i = 1; i <= backpack.length(); i++){
			if(equip.equals(((Integer)i).toString()) && 
					backpack.getItem(i-1) != null){
				if(backpack.getItem(i-1) instanceof Gear){
					return backpack.removeGear(i-1);
				}
			}
		}
		for(int i = 0; i < backpack.length(); i++){
			if(backpack.getItem(i) != null &&
					backpack.getItem(i).getName().equalsIgnoreCase(equip)){
				if(backpack.getItem(i) instanceof Gear){
					return backpack.removeGear(i);
				}
			}
		}
		return null;
	}
	/**
	 * the equip process for a 2hweapon
	 */
	private void equip2H()
	{
		if(backpack.bagSpace() <= 1 && equipment.isDualWield()){
			System.out.println("Your backpack is full");
			wasEquipped.lootItem(toEquip);
		}
		else if(equipment.hasGear("Main Hand") || equipment.hasGear("Off Hand")){
			if(replace(toEquip.getEquipString())){
				wasEquipped.lootItem(equipment.unequip("Main Hand"));
				wasEquipped.lootItem(equipment.unequip("Off Hand"));
				equipment.equip(toEquip.getEquipString(), toEquip);
				System.out.println(toEquip.getName() + " was equipped!");
			}
			else{
				wasEquipped.lootItem(toEquip);
				System.out.println(toEquip.getName()  + " returned to your backpack");
			}
		}
		else{
			if(replace(toEquip.getEquipString())){
				wasEquipped.lootItem(equipment.unequip(toEquip.getEquipString()));
				equipment.equip(toEquip.getEquipString(), toEquip);
				System.out.println(toEquip.getName() + " was equipped!");
			}
			else{
				wasEquipped.lootItem(toEquip);
				System.out.println(toEquip.getName()  + " returned to your backpack");
			}
		}
	}
	
	/**
	 * the equip process for a 1h or mh weapon
	 * @param hand
	 */
	private void equipWeapon(String hand)
	{
		if(equipment.hasGear("Both Hands")){
			if(replace(toEquip.getEquipString())){
				wasEquipped.lootItem(equipment.unequip("Both Hands"));
				equipment.equip(toEquip.getEquipString(), toEquip);
				System.out.println(toEquip.getName() + " was equipped!");
			}
			else{
				wasEquipped.lootItem(toEquip);
				System.out.println(toEquip.getName()  + " returned to your backpack");
			}
		}
		else if(equipment.hasGear(hand)){
			if(replace(toEquip.getEquipString())){
				wasEquipped.lootItem(equipment.unequip(hand));
				equipment.equip(toEquip.getEquipString(), toEquip);
				System.out.println(toEquip.getName() + " was equipped!");
			}
			else{
				wasEquipped.lootItem(toEquip);
				System.out.println(toEquip.getName()  + " returned to your backpack");
			}
		}
		else{
			wasEquipped.lootItem(equipment.unequip(toEquip.getEquipString()));
			equipment.equip(hand, toEquip);
			System.out.println(toEquip.getName() + " was equipped!");
		}
	}
	
	/**
	 * equips all other kinds of gear
	 */
	private void equipGear()
	{
		if(equipment.hasGear(toEquip.getEquipString())){
			if(replace(toEquip.getEquipString())){
				wasEquipped.lootItem(equipment.unequip(toEquip.getEquipString()));
				equipment.equip(toEquip.getEquipString(), toEquip);
				System.out.println(toEquip.getName() + " was equipped!");
			}
			else{
				wasEquipped.lootItem(toEquip);
				System.out.println(toEquip.getName()  + " returned to your backpack");
			}
		}
		else{
			wasEquipped.lootItem(equipment.unequip(toEquip.getEquipString()));
			equipment.equip(toEquip.getEquipString(), toEquip);
			System.out.println(toEquip.getName() + " was equipped!");
		}
	}
	
	/**
	 * confirms if you really want to replace one item with the other
	 * @param equip location HashMap key
	 * @return boolean
	 */
	private boolean replace(String gear)
	{
		boolean ask = true;
		boolean replace = true;
		if(gear.equals("Both Hands") && equipment.isDualWield()){
			while(ask){
				System.out.println("Replace these items?");
				System.out.println("--------------------");
				equipment.getGear("Main Hand").printDetails();
				System.out.println();
				equipment.getGear("Off Hand").printDetails();
				System.out.println();
				System.out.print("Y/N> ");
				String input = reader.readString();
				for(int i=0; i < Constant.YES.length; i++){
	                if(input.equalsIgnoreCase(Constant.YES[i])){
	                	wasEquipped.lootItem(equipment.unequip("Main Hand"));
	                	wasEquipped.lootItem(equipment.unequip("Off Hand"));
	                	ask = false;
	                }
	            }
	            for(int i=0; i < Constant.NO.length; i++){
	                if(input.equalsIgnoreCase(Constant.NO[i])){
	    				 System.out.println(toEquip.getName() + " was returned to your bag");
	                     ask = false;
	                     replace = false;
	                }
	            }
	            if(ask){
	                System.out.println("use 'yes' or 'no'");
	            }
			}
		}
		else if(equipment.hasGear("Both Hands") && (gear.equals("Main Hand") || 
				gear.equals("Off Hand"))){
			while(ask){
				System.out.println("Replace this item?");
				System.out.println("------------------");
				equipment.getGear("Both Hands").printDetails();
				System.out.println();
				System.out.print("Y/N> ");
				String input = reader.readString();
				for(int i=0; i < Constant.YES.length; i++){
	                if(input.equalsIgnoreCase(Constant.YES[i])){
	                	ask = false;
	                }
	            }
	            for(int i=0; i < Constant.NO.length; i++){
	                if(input.equalsIgnoreCase(Constant.NO[i])){
	    				 System.out.println(toEquip.getName() + " was returned to your bag");
	                     ask = false;
	                     replace = false;
	                }
	            }
	            if(ask){
	                System.out.println("use 'yes' or 'no'");
	            }
			}
		}
		else if(equipment.hasGear(gear)){
			while(ask){
				System.out.println("Replace this item?");
				System.out.println("------------------");
				equipment.getGear(gear).printDetails();
				System.out.println();
				System.out.print("Y/N> ");
				String input = reader.readString();
				for(int i=0; i < Constant.YES.length; i++){
	                if(input.equalsIgnoreCase(Constant.YES[i])){
	                	ask = false;
	                }
	            }
	            for(int i=0; i < Constant.NO.length; i++){
	                if(input.equalsIgnoreCase(Constant.NO[i])){
	    				 System.out.println(toEquip.getName() + " was returned to your bag");
	                     ask = false;
	                     replace = false;
	                }
	            }
	            if(ask){
	                System.out.println("use 'yes' or 'no'");
	            }
			}
		}
		return replace;
	}
	
	/**
	 * this method determines what hand they wish to equip and returns string for the spot
	 * @return equipSpot
	 */
	private String weaponEquip()
	{
		boolean ask = true;
		String answer = "";
        while(ask){
            System.out.println("Which hand to equip to?");
            System.out.print("Main/Off> ");
            String input = reader.readString();
            for(int i=0; i < Constant.MAINHAND_OPTIONS.length; i++){
                if(input.equalsIgnoreCase(Constant.MAINHAND_OPTIONS[i])){
                	answer =  "Main Hand";
                	ask = false;
                }
            }
            for(int i=0; i < Constant.OFFHAND_OPTIONS.length; i++){
                if(input.equalsIgnoreCase(Constant.OFFHAND_OPTIONS[i])){
                     answer = "Off Hand";
                     ask = false;
                }
            }
            if(ask){
                System.out.println("You can equip to main hand or off hand");
            }
        }
        return answer;
	}
	
	/**
	 * asks the user if he would like to equip another item returns boolean
	 * @return boolean
	 */
	private boolean equipAgain()
	{
		boolean answer = false;
		boolean ask = true;
        while(ask){
        	System.out.println("Current Equip:");
        	System.out.println();
        	equipment.showEquip();
            System.out.println("Equip another item?");
            System.out.print("Y/N> ");
            String input = reader.readString();
            for(int i=0; i < Constant.YES.length; i++){
                if(input.equalsIgnoreCase(Constant.YES[i])){
                	answer = false;
                	ask = false;
                }
            }
            for(int i=0; i < Constant.NO.length; i++){
                if(input.equalsIgnoreCase(Constant.NO[i])){
                     answer = true;
                     ask = false;
                }
            }
            if(ask){
                System.out.println("use 'yes' or 'no'");
            }
        }
        return answer;
	}
}
