
public class TestPlayer {

	public static void main(String[] args) {
		Player deus = new Player("Deus", 16, 12, 17, 5, 9, 14, 1, 10, 1, 0, 0, 19);
		deus.status();
		deus.takeDamage(2);
		deus.healDamage(1);
		System.out.println(deus.getCurrentHP() + "/" + deus.getMaxHP());
		deus.addXP(7000);
		deus.showXP();
	}

}