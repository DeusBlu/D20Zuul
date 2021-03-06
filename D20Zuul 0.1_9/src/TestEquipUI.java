

/**
 * the primary method that runs the game
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class TestEquipUI 
{
	public static void main(String args[])
	{
		Player deus = new Player();
	    ThAxe battleaxe5 = new ThAxe(10.0, 4000, "Battle Axe +5", 2, 10, 0, 2, 0, 5, 5, "none", 0);
	    MainHand longSword = new MhSword(5.0, 2000, "Long Sword", 1, 10, 0, 2, 0, 0, 0, "none", 0);
	    OhSword shortSword = new OhSword(1.5, 600, "Short Sword", 1, 6, 0, 2, 0, 0, 0, "none", 0);
	    Helm skullCap = new Helm(1.0, 500, "Skullcap", 0, 0, 0, 1, 0, 0, "none", 0);
	    Shoulders spikedShoulder = new Shoulders(3.8, 700, "Spiked Shoulders", 0, 0, 1, 3, 0, 0, "none", 0);
	    Chest chainmail = new Chest(10.0, 8000, "Chainmail", 0, 0, 0, 5, 0, 0, "none", 0);
	    Gloves chainGloves = new Gloves(2.5, 300, "Chain Gloves of Strength", 0, 0, 0, 1, 0, 0, "str", 2);
	    Pants chainPants = new Pants(6.1, 900, "Chain Pants", 0, 0, 0, 3, 0, 0, "none", 0);
	    Boots chainBoots = new Boots(3.4, 430, "Chain Boots of Striking", 0, 0, 0, 2, 0, 1, "none", 0);
	    Boots moreBoots = new Boots(2.1, 8000, "Leather Sandles of Giant Strength", 0, 0, 0, 0, 0, 0, "str", 6);
	    deus.loot(battleaxe5);
	    deus.loot(longSword);
	    deus.loot(shortSword);
	    deus.loot(skullCap);
	    deus.loot(spikedShoulder);
	    deus.loot(chainmail);
	    deus.loot(chainGloves);
	    deus.loot(chainPants);
	    deus.loot(chainBoots);
	    deus.loot(moreBoots);
		EquipUI equipMe = new EquipUI(deus);
	    equipMe.equipUI();
	    equipMe = null;
	} 
}
