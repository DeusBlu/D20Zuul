/**
 * @author desmond.jenkins
 *
 */
public class Goblin extends Creature {
	
	static int[] attacks = {0};
	/**
	 * 
	 */
	public Goblin() {
		super();
	}

	/**
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
	 * @param attacks
	 * @param xpValue
	 * @param mpMod
	 */
	public Goblin(String name) {
		super(name, 11, 13, 12, 10, 9, 6, Constant.BASE_AC, 1, 8, 3, 0, 0, 1, 2, 0, attacks, 0.25, 0);
		createLoot();
		super.equipItems();
		
	}
	
	private void createLoot()
	{
		Dice dice = new Dice();
		int roll = dice.roll(1, 20);
		if(roll >= 1){
			super.loot(new MhSword(2, 1000, "Short Sword", 1, 6, 0, 2, 0, 0, 0, "none", 0));
		}
		if(roll >= 5){
			super.loot(new Chest(15, 1000, "Leather Chest", 0, 0, 0, 1, 0, 0, "none", 0));
		}
		if(roll >= 10){
			super.loot(new Shield(5, 1500, "Buckler", 0, 0, 0, 1, 0, 0, "none", 0));
		}
		if(roll >= 15){
			super.loot(new Potion(1, 500, "Potion of Healing", 1, "Heals for 1d6+4", 1, 6, 4, false));
		}
		if(roll >= 20){
			super.loot(new Potion(1, 500, "Potion of Healing", 1, "Heals for 1d6+4", 1, 6, 4, false));
			super.loot(new Potion(1, 500, "Potion of Healing", 1, "Heals for 1d6+4", 1, 6, 4, false));
		}
	}

}
