public class TestInitiative {

	public static void main(String[] args) 
	{
		Player deus = new Player("Deus", 18, 18, 18, 18, 18, 18, 19, "fighter");
		MhSword longSword = new MhSword(5.0, 2000, "Long Sword", 1, 10, 0, 2, 0, 0, 0, "none", 0);
		deus.equip("Main Hand", longSword);
		deus.learnAbility(new HeavySwing());
		Player rush = new Player("Rush", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Player muru = new Player("Murutang", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Player sure = new Player("Surewould", 18, 11, 11, 11, 11, 11, 19, "fighter");
		Party pcParty = new Party(4);
		Potion potion = new Potion(0.1, 10, "Potion of Healing", 1, "Heals for 1d6+4", 1, 6, 4, false);
		deus.addXP(20000);
		deus.loot(potion);
		pcParty.join(deus);
		pcParty.join(rush);
		pcParty.join(muru);
		pcParty.join(sure);
		Creature gob1 = new Goblin("Goblin1");
		Creature gob2 = new Goblin("Goblin2");
		Creature gob3 = new Goblin("Goblin3");
		Creature gob4 = new Goblin("Goblin4");
		Party monParty = new Party();
		monParty.join(gob1);
		monParty.join(gob2);
		monParty.join(gob3);
		monParty.join(gob4);
		CombatUI testFight = new CombatUI(pcParty, monParty);
		testFight.fight();
		System.out.println();
	}
}
