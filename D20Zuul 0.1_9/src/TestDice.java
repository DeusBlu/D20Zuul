
public class TestDice {

	public static void main(String[] args) 
	{
		for(int i = 0; i < 400; i++){
			Dice dice = new Dice();
			dice.rollPrint(3, 20);
			System.out.println();
		}
	}

}
