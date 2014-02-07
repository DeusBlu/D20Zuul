/**
 * a base class for anything living in the game
 * @author Deus
 *
 */
public class Entity 
{
	private String name;
	private HP hp;
	private int armor;
	private Inventory backpack;
	private Equipment gear;
	private int init;
	
	/**
	 * default constructor for Entity
	 */
	public Entity()
	{
		hp = new HP();
		backpack = new Inventory();
		gear = new Equipment();
		setName("");
		setHP(0, 0, 0);
		setArmor(0);
		setInit(0);
	}
	
	/**
	 * proper constructor for Entity
	 * @param name
	 * @param startingHP
	 * @param armor
	 * @param init
	 */
	public Entity(String name, int armor, int init,
				  int numberDice, int hpDie, int hpMod)
	{
		hp = new HP();
		backpack = new Inventory();
		gear = new Equipment();
		setName(name);
		setArmor(armor);
		setInit(init);
		setHP(numberDice, hpDie, hpMod);
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

	private void setHp(int numberDice, int hpDie, int hpMod) {
		hp.
	}

	private void setArmor(int armor) {
		this.armor = armor;
	}

	private void setInit(int init) {
		this.init = init;
	}
}
