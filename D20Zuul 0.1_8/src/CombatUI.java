/**
 * determines who the monster should attack
 * @author DeusBlu
 * @version 0.1_8
 *
 */

public class CombatUI 
{
	private InputReader reader;
	private Party options;
	
	/**
	 * default constructor for the CombatUI
	 */
	public CombatUI()
	{
		reader = new InputReader();
		options = new Party();
	}
	
	/**
	 * constructor for useable combatUI
	 * @param monsters
	 */
	public CombatUI(Party monsters)
	{
		reader = new InputReader();
		options = monsters;
		
	}
	
	/**
	 * returns the target the user has selected to attack
	 * @return entity to attack
	 */
	public Entity getTarget()
	{
		Entity target = null;
		int input = 0;
		while(target == null){
			options.shortStatus();
			System.out.println("Monster to attack?");
			System.out.print(">");
			input = reader.readInt();
			if(options.getPlayers()[input-1] != null){
				target = options.getPlayers()[input-1];
			}
			else{
				System.out.println("That is not a valid target");
			}
		}
		return target;
	}
}
