
public class TestPlayer {

	public static void main(String[] args) {
		Player deus = new Player("Deus", 16, 12, 17, 5, 9, 14, 19, "fighter");
		deus.status();
		deus.takeDamage(2);
		deus.healDamage(1);
		System.out.println(deus.getCurrentHP() + "/" + deus.getMaxHP());
		deus.addXP(7000);
		deus.showXP();
		deus.status();
	}

}
