/**
 * the UI for using items
 * @author Deus
 *
 */
public class ItemUI 
{
	private InputReader reader;
	private Inventory backpack;
	private Party players;
	private Party monsters;
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
		useItem();
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
	
	public void useItem()
	{
		while(item == null){
			item = getItem();
		}
		Entity target = null;
		while(target == null){
			if(item.isOffensive())
				target = getMonster();
			else{
				target = getPlayer();
			}
		}
		boolean confirm = confirm();
		if(confirm){
			boolean spent = item.use(target);
			if(!spent){
				backpack.lootItem(item);
			}
			use();
		}
	}
	
	private MagicDevice getItem()
	{
		int input = 0;
		item = null;
		while(input == 0){
			backpack.showBag();
			System.out.println("Use which item?");
			System.out.print("#> ");
			input = reader.readInt();
			if(backpack.getItem(input-1) != null && backpack.getItem(input-1) instanceof MagicDevice){
				return item = (MagicDevice)backpack.removeItem(input-1);
			}
		}
		return null;
	}
	
	private Entity getPlayer()
	{
		int input = 0;
		while(input == 0){
			players.shortStatus();
			System.out.println("Use on who?");
			System.out.print("#> ");
			input = reader.readInt();
			if(players.getPlayers()[input-1] != null){
				target = players.getPlayers()[input-1];
			}
		}
		return target;
	}
	
	private Entity getMonster()
	{
		int input = 0;
		while(input == 0){
			monsters.shortStatus();
			System.out.println("Use on who?");
			System.out.print("#> ");
			input = reader.readInt();
			if(monsters.getPlayers()[input-1] != null){
				target = monsters.getPlayers()[input-1];
			}
		}
		return target;
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
