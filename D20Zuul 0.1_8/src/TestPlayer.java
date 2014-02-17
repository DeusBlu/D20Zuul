import java.util.Stack;
public class TestPlayer {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Player deus = new Player("Deus", 16, 12, 17, 5, 9, 14, 19, "fighter");
		deus.status();
		deus.takeDamage(2);
		deus.healDamage(1);
		System.out.println(deus.getCurrentHP() + "/" + deus.getMaxHP());
		deus.learnWeapProf("2H Axe");
		deus.addXP(100000);
		Stack<Integer> attacks = deus.getAttacks();
		deus.learnAbility(new HeavySwing());
		deus.showXP();
		deus.status();
	}
}
