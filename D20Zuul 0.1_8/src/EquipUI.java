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
	public EquipUI(Equipment equipment, 
				   Inventory inventory)
	{
		toEquip = null;
		reader = new InputReader();
		setEquip(equipment);
		setInventory(inventory);
		wasEquipped = new Inventory();
		equipUI();
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
	private void equipUI()
	{
		boolean finished = false;
		int input = 0;
		while(!finished){
			input = 0;
			while(input == 0){
				backpack.showBag();
				System.out.println("What item # would you like to equip?");
				System.out.print("> ");
				input = reader.readInt();
				if(input > 0 && backpack.getGear(input-1) != null){
					toEquip = backpack.removeGear(input-1);
					if(toEquip.getType().equals("2hweapon")){
						equip2H();
					}
					else if(toEquip.getType().equals("1hweapon")){
						equipWeapon(weaponEquip());
					}
					else if(toEquip.getType().equals("mhweapon")){
						equipWeapon("Main Hand");
					}
					else{
						equipGear();
					}
				}
			}
			backpack.transfer(0, wasEquipped);
			backpack.transfer(1, wasEquipped);
			backpack.sort();
			finished = equipAgain();
		}
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
				System.out.print(">");
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
				System.out.print(">");
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
            System.out.print("> ");
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
            System.out.print("> ");
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
