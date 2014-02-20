import java.util.Stack;
/**
 * Used for the battle, loads 2 parties (player and monster) and uses a combat object to simulate
 * the actual battle
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Encounter
{
    private Party players;
    private Party monsters;
    private Combat combat;
    private Stack<Entity> turnOrder;
    private Initiative initiative;
    /**
     * default constructor for class Encounter
     */
    public Encounter()
    {
        players = new Party();
        monsters = new Party();
        combat = new Combat();
        turnOrder = new Stack<Entity>();
        initiative = new Initiative();
    }
    
    /**
     * Created the encounter and loads the parties for the encounter/initializes the Combat object
     * @param Party - the players party
     * @param Party - the part of monsters to fight
     */
    public Encounter(Party players, Party monsters)
    {
        setPlayers(players);
        setMonsters(monsters);
        combat = new Combat();
        turnOrder = new Stack<Entity>();
        initiative = new Initiative();
    }
    
    /**
     * loads the players party making sure that it is not a null object or an empty party
     * @param Party - the party of players
     */
    private void setPlayers(Party party)
    {
        if(party != null && !party.isEmpty()){
            players = party;
        }
    }
    
    /**
     * loads the monsters party making sure that it is not a null object or an empty party
     * @param Party - the monsters party
     */
    private void setMonsters(Party party)
    {
        if(party != null && !party.isEmpty()){
            monsters = party;
        }
    }
    
    public Party getPlayers()
    {
    	return players;
    }
    
    public Party getMonsters()
    {
    	return monsters;
    }
    
    /**
     * sets the initiative on all of the player objects
     */
    public void setInitiative()
    {
    	turnOrder = initiative.pcInit(players, monsters);
    }
    
    /**
     * returns the current combat roll as an int
     * @return combat roll as int
     */
    public int getRoll()
    {
    	return combat.getRoll();
    }
    
    /**
     * returns true if the initiative stack is empty
     * @return true if stack empty
     */
    public boolean initEmpty()
    {
    	if(turnOrder.empty()){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    /**
     * returns the next Entity in the stack of turn orders returns null
     * if there is no next or the next is dead
     * @return Entity for next turn
     */
    public Entity getNextTurn()
    {
    	Entity nextTurn = null;
    	if(!turnOrder.empty()){
    		if(!turnOrder.peek().isDead()){
    			nextTurn = turnOrder.pop();
    		}
    		else{
    			turnOrder.pop();
    		}
    	}
    	return nextTurn;
    }
    
    /**
     * checks to see if the entity successfully hit the target and returns
     * true if it hit
     * @param player
     * @param target
     * @return true if hit
     */
    public boolean successHit(int hitMod, Entity target)
    {
    	return combat.successHit(hitMod, target);
    }
    
    public int attack(int hitMod, Entity player, Entity target)
    {
    	return combat.attack(hitMod, player, target);
    }
    
    public boolean critHit()
    {
    	if(combat.getRoll() == 20){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
	
    /**
     * calculates the EXP for the party and assigns it to the players
     * @return
     */
	public int finishCombat()
	{
		CalculateXP calculate = new CalculateXP();
		int exp = calculate.calculateXP(players, monsters);
		for(int i = 0; i < players.getPlayers().length; i++){
			if(!players.getPlayers()[i].isDead()){
				((Player)players.getPlayers()[i]).addXP(exp);
			}
		}
		return exp;
	}
}