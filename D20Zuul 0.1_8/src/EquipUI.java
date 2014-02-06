/**
 * the interface a user will use to equip an item
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class EquipUI 
{
	private static final String[] MAINHAND_OPTIONS = {
        "mh", "main hand", "mainhand", "m h", "main", "m", "ma"
    };
    private static final String[] OFFHAND_OPTIONS = {
        "oh", "off hand", "offhand", "o h", "off", "o", "of"
    };
    private static final String[] YES = {
    	"yes", "y"
    };
    private static final String[] NO = {
    	"no", "n"
    };
	private InputReader reader;
	private Equipment equipment;
	private Inventory backpack;
	
	/**
	 * default constructor for EquipUI
	 */
	public EquipUI()
	{
		reader = new InputReader();
		equipment = new Equipment();
		backpack = new Inventory();
	}
	
	/**
	 * constructor specifying the equipment and inventory to use
	 * @param equipment
	 * @param inventory
	 */
	public EquipUI(Equipment equipment, 
				   Inventory inventory)
	{
		reader = new InputReader();
		setEquip(equipment);
		setInventory(inventory);
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
		Gear toEquip;
		Inventory wasEquipped = new Inventory();
		boolean finished = false;
		while(!finished){
			equipment.showEquip();
			System.out.println("What item # would you like to equip?");
			System.out.print("> ");
			int input = reader.readInt();
			while(input == 0){
				if(backpack.getGear(input-1) != null){
					toEquip = backpack.getGear(input-1);
					if(toEquip.getType().equals("2hweapon") && equipment.isDualWield()){
						if(backpack.bagSpace() <= 1){
							System.out.println("Your backpack is full");
						}
						else{
							wasEquipped.lootItem(equipment.unequip("Main Hand"));
							wasEquipped.lootItem(equipment.unequip("Off Hand"));
							equipment.equip(toEquip.getEquipString(), toEquip);
						}
					}
					else if(toEquip.getType().equals("1hweapon")){
						String hand = weaponEquip();
						wasEquipped.lootItem(equipment.unequip(hand));
						equipment.equip(hand, toEquip);
					}
					else{
						wasEquipped.lootItem(equipment.unequip(toEquip.getEquipString()));
						equipment.equip(toEquip.getEquipString(), toEquip);
					}
				}
			}
			finished = equipAgain();
		}
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
            for(int i=0; i < MAINHAND_OPTIONS.length; i++){
                if(input.equalsIgnoreCase(MAINHAND_OPTIONS[i])){
                	answer =  "Main Hand";
                	ask = false;
                }
            }
            for(int i=0; i < OFFHAND_OPTIONS.length; i++){
                if(input.equalsIgnoreCase(OFFHAND_OPTIONS[i])){
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
            System.out.println("Equip another item?");
            System.out.print("> ");
            String input = reader.readString();
            for(int i=0; i < YES.length; i++){
                if(input.equalsIgnoreCase(YES[i])){
                	answer = true;
                	ask = false;
                }
            }
            for(int i=0; i < NO.length; i++){
                if(input.equalsIgnoreCase(NO[i])){
                     answer = false;
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
