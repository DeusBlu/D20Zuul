/**
 * the UI for using items
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class ItemUI 
{
	private InputReader reader;
	private Inventory backpack;
	private Party players;
	private Party monsters;
	private Party targets;
	private MagicDevice item;
	private Entity target;
	
	/**
	 * default constructor for ItemUI
	 */
	public ItemUI()
	{
		reader = new InputReader();
	}
	
	public ItemUI(Inventory backpack, Party players, Party monsters)
	{
		reader = new InputReader();
		setBackpack(backpack);
		setPlayers(players);
		setMonsters(monsters);
		item = null;
		target = null;
	}
	
	private void setBackpack(Inventory backpack)
	{
		if(backpack != null){
			this.backpack = backpack;
		}
		else{
			throw new IllegalArgumentException("Inventory object was null");
		}
	}
	
	private void setPlayers(Party players)
	{
		if(players != null){
			this.players = players;
		}
		else{
			throw new IllegalArgumentException("Party players object was null");
		}
	}
	
	private void setMonsters(Party monsters)
	{
		if(monsters != null){
			this.monsters = monsters;
		}
		else{
			throw new IllegalArgumentException("Party monsters object was null");
		}
	}
	
	public boolean useItem()
	{
		String selectItem = null;
		while(selectItem == null){
			backpack.showBag();
			System.out.println("What item would you like to use?");
			System.out.print("#/Name> ");
			selectItem = selectItem();
			if(selectItem != null && selectItem.equalsIgnoreCase("end")){
				return false;
			}
		}
		item = getItem(selectItem);
		String selectTarget = null;
		while(selectTarget == null){
			if(item.isOffensive()){
				targets = monsters;
			}
			else{
				targets = players;
			}
			selectTarget = selectTarget(targets);
			if(selectTarget != null && selectItem.equalsIgnoreCase("end")){
				backpack.lootItem(item);
				return false;
			}
		}
		target = getTarget(selectTarget, targets);
		boolean confirm = confirm();
		if(confirm){
			boolean spent = item.use(target);
			if(!spent){
				backpack.lootItem(item);
			}
			use();
			return true;
		}
		else{
			backpack.lootItem(item);
		}
		if(backpack.isEmpty()){
			System.out.println("Your inventory is empty");
			reader.readString();
		}
		return false;
	}
	
	private String selectItem()
	{
		String input = reader.readString();
		if(input.equalsIgnoreCase("end")){
			return input;
		}
		for(int i = 1; i <= backpack.length(); i++){
			if(input.equals(((Integer)i).toString()) && 
			backpack.getItem(i-1) != null){
				if(backpack.getItem(i-1) instanceof MagicDevice){
					return input;
				}
			}
		}
		for(int i = 0; i < backpack.length(); i++){
			if(backpack.getItem(i) != null &&
					backpack.getItem(i).getName().equalsIgnoreCase(input)){
				if(backpack.getItem(i) instanceof MagicDevice){
					return input;
				}
			}
		}
		System.out.println("That is not valid gear please enter the name");
		System.out.println("of the gear or the location # or type end");
		reader.readString();
		return null;
	}
	
	private MagicDevice getItem(String selectItem)
	{
		for(int i = 1; i <= backpack.length(); i++){
			if(selectItem.equals(((Integer)i).toString()) && 
					backpack.getItem(i-1) != null){
				if(backpack.getItem(i-1) instanceof MagicDevice){
					return (MagicDevice)backpack.removeItem(i-1);
				}
			}
		}
		for(int i = 0; i < Constant.BACKPACK_SIZE; i++){
			if(backpack.getItem(i) != null &&
					backpack.getItem(i).getName().equalsIgnoreCase(selectItem)){
				if(backpack.getItem(i) instanceof MagicDevice){
					return (MagicDevice)backpack.removeItem(i);
				}
			}
		}
		return null;
	}
	
	private String selectTarget(Party targets)
	{
		targets.shortStatus();
		System.out.println("Use on who?");
		System.out.print("#/Name> ");
		String selectTarget = reader.readString();
		for(int i = 1; i <= targets.getPlayers().length; i++){
			if(selectTarget.equals(((Integer)i).toString()) && 
			targets.getPlayers()[i-1] != null){
				return selectTarget;
			}
		}
		for(int i = 0; i < targets.getPlayers().length; i++){
			if(targets.getPlayers()[i] != null &&
					targets.getPlayers()[i].getName().equalsIgnoreCase(selectTarget)){
				return selectTarget;
			}
		}
		System.out.println("That was not a valid target, please enter the name");
		System.out.println("or the # of the target or type end to exit");
		reader.readString();
		return null;
	}
	
	private Entity getTarget(String target, Party targets)
	{
		for(int i = 1; i <= targets.getPlayers().length; i++){
			if(target.equals(((Integer)i).toString()) && 
			targets.getPlayers()[i-1] != null){
				return targets.getPlayers()[i-1];
			}
		}
		for(int i = 0; i < targets.getPlayers().length; i++){
			if(targets.getPlayers()[i] != null &&
					targets.getPlayers()[i].getName().equalsIgnoreCase(target)){
				return targets.getPlayers()[i];
			}
		}
		return null;
	}
	
	private boolean confirm()
	{
		boolean confirm = false;
		boolean ask = true;
		while(ask){
			System.out.println("Use "+item.getName()+" on "+target.getName()+"?");
			System.out.print("Y/N> ");
			String input = reader.readString();
			for(int i=0; i < Constant.YES.length; i++){
                if(input.equalsIgnoreCase(Constant.YES[i])){
                	ask = false;
                	confirm = true;
                }
            }
            for(int i=0; i < Constant.NO.length; i++){
            	if(input.equalsIgnoreCase(Constant.NO[i])){
            		ask = false;
                }
            }
            if(ask){
                System.out.println("use 'yes' or 'no'");
            }
		}
		return confirm;
	}
	
	private void use()
	{
		if(item.isOffensive()){
			int damage = item.getEffectValue();
			System.out.println(target.getName()+" took "+damage+
					" damage from "+item.getName());
			target.takeDamage(damage);
		}
		else{
			int healing = item.getEffectValue();
			System.out.println(target.getName()+" was healed for "+healing+
					" by "+item.getName());
			target.healDamage(healing);
		}
	}
}
