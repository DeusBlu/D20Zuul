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
    private Stack<Integer> attacks;
    private Entity currentTurn;
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
        attacks = new Stack<Integer>();
        currentTurn = null;
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
        attacks = new Stack<Integer>();
        currentTurn = null;
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
    
    /**
	 * sets the initiative on all of the player objects
	 */
	public void setInitiative()
	{
		turnOrder = initiative.pcInit(players, monsters);
	}
	
	public void setAttacks(Stack<Integer> attacks)
	{
		if(attacks != null){
			this.attacks = attacks;
		}
		else{
			throw new IllegalArgumentException("attacks stack was null");
		}
	}
	
	public void setCurrentTurn()
	{
		currentTurn = getNextTurn();
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
     * returns the current combat roll as an int
     * @return combat roll as int
     */
    public int getRoll()
    {
    	return combat.getRoll();
    }
    
    /**
	 * returns the next Entity in the stack of turn orders returns null
	 * if there is no next or the next is dead
	 * @return Entity for next turn
	 */
	private Entity getNextTurn()
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
	
	public Stack<Integer> getAttacks()
	{
		return attacks;
	}
	
	public int getAttack()
	{
		return attacks.peek();
	}
	
	public Entity getCurrentTurn()
	{
		return currentTurn;
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
	
	public boolean attacksEmpty()
	{
		if(attacks.empty()){
			return true;
		}
		else{
			return false;
		}
	}
}