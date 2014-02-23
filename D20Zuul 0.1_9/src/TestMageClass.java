
public class TestMageClass {

	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		Mage mage = new Mage();
		mage.addXP(100000);
		int[] attacks = mage.getAttacks();
		System.out.println();
	}

}
