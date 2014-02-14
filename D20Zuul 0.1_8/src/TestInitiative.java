public class TestInitiative {

	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		Player deus = new Player("Deus", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Player rush = new Player("Rush", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Player muru = new Player("Murutang", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Player sure = new Player("Surewould", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Party pcParty = new Party(true);
		Potion potion = new Potion(0.1, 10, "Potion of Healing", 1, "Heals for 1d6", new DiceSet(1, 6, 0), false);
		deus.loot(potion);
		pcParty.join(deus);
		pcParty.join(rush);
		pcParty.join(muru);
		pcParty.join(sure);
		Creature gob1 = new Creature("Goblin A", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Creature gob2 = new Creature("Goblin B", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Creature gob3 = new Creature("Goblin C", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Creature gob4 = new Creature("Goblin D", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Party monParty = new Party();
		TwoHanded greataxe5 = new TwoHanded(6.0, 5002000, "Greataxe +5", 1, 12, 0, 0, 5, 5, "none", 0);
	    MainHand longSword = new MainHand(5.0, 2000, "Long Sword", 1, 10, 0, 0, 0, 0, "none", 0);
	    OneHand shortSword = new OneHand(1.5, 600, "Short Sword", 1, 6, 0, 0, 0, 0, "none", 0);
	    Helm skullCap = new Helm(1.0, 500, "Skullcap", 0, 0, 0, 1, 0, 0, "none", 0);
	    Shoulders spikedShoulder = new Shoulders(3.8, 700, "Spiked Shoulders", 0, 0, 1, 3, 0, 0, "none", 0);
	    Chest chainmail = new Chest(10.0, 8000, "Chainmail", 0, 0, 0, 5, 0, 0, "none", 0);
	    Gloves chainGloves = new Gloves(2.5, 300, "Chain Gloves of Strength", 0, 0, 0, 1, 0, 0, "str", 2);
	    Pants chainPants = new Pants(6.1, 900, "Chain Pants", 0, 0, 0, 3, 0, 0, "none", 0);
	    Boots chainBoots = new Boots(3.4, 430, "Chain Boots of Striking", 0, 0, 0, 2, 0, 1, "none", 0);
	    Boots moreBoots = new Boots(2.1, 8000, "Leather Sandles of Giant Strength", 0, 0, 0, 0, 0, 0, "str", 6);
	    gob1.loot(greataxe5);
	    gob1.loot(skullCap);
	    gob1.loot(spikedShoulder);
	    gob1.loot(longSword);
	    gob1.loot(chainmail);
	    gob1.loot(chainGloves);
	    gob1.loot(chainPants);
	    gob1.loot(chainBoots);
	    gob1.loot(moreBoots);
	    gob1.equipItems();
		monParty.join(gob1);
		monParty.join(gob2);
		monParty.join(gob3);
		monParty.join(gob4);
		//Initiative init = new Initiative();
		//Stack<Entity> initiative = init.pcInit(pcParty, monParty);
		Encounter testFight = new Encounter(pcParty, monParty);
		testFight.fight();
	}

}
