import java.util.Stack;
public class TestInitiative {

	public static void main(String[] args) 
	{
		Player deus = new Player("Deus", 11, 11, 11, 11, 11, 11, 1, 10, 1, 1, 1, 19);
		Player rush = new Player("Rush", 11, 11, 11, 11, 11, 11, 1, 10, 1, 1, 1, 19);
		Player muru = new Player("Murutang", 11, 11, 11, 11, 11, 11, 1, 10, 1, 1, 1, 19);
		Player sure = new Player("Surewould", 11, 11, 11, 11, 11, 11, 1, 10, 1, 1, 1, 19);
		Party pcParty = new Party(true);
		pcParty.join(deus);
		pcParty.join(rush);
		pcParty.join(muru);
		pcParty.join(sure);
		Creature gob1 = new Creature("Goblin A", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Creature gob2 = new Creature("Goblin B", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Creature gob3 = new Creature("Goblin C", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Creature gob4 = new Creature("Goblin D", 8, 8, 8, 8, 8, 8, 3, 1, 6, 0, 0, 0, 1, 2, 0, 35);
		Party monParty = new Party();
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
