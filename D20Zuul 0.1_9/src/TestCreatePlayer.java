
public class TestCreatePlayer {

	public static void main(String[] args) 
	{
		CreatePlayer newPlayer = new CreatePlayer();
		Player thePlayer = newPlayer.start();
		thePlayer.status();
	}
}
