/**
 * determines who the monster should attack
 * @author DeusBlu
 * @version 0.1_8
 *
 */

public class CombatAI 
{
	private Party players;
	
	/**
	 * constructor for the CombatUI
	 */
	public CombatAI()
	{
		players = new Party();
	}
	
	/**
	 * useable constructor that accepts the array of players to choose from
	 * @param players
	 */
	public CombatAI(Party players)
	{
		this.players = players;
	}
	
	/**
	 * decides who to attack based on current HP, attacks lowest
	 * @return the entity to attack
	 */
	public Entity attackWho()
	{
		Entity toAttack = null;
		int lowestHP = Integer.MAX_VALUE;
		for(int i = 0; i < players.getPlayers().length; i++){
			if(players.getPlayers()[i].getCurrentHP() < lowestHP 
					&& !players.getPlayers()[i].isDead()){
				toAttack = players.getPlayers()[i];
				lowestHP = players.getPlayers()[i].getCurrentHP();
			}
		}
		return toAttack;
	}
}
