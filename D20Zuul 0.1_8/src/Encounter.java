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
    public boolean successHit(Entity player, Entity target)
    {
    	int roll = combat.setRoll();
    	if(roll == 20){
    		return true;
    	}
    	else if(roll == 1){
    		return false;
    	}
    	else if(player.getMeleeAttackMod() + roll > target.getArmor()){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public int attack(Entity player, Entity target)
    {
    	return combat.attack(player, target);
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
	
	public int finishCombat()
	{
		int exp = 0;
		if(!players.isDefeated()){
			Entity[] defeated = monsters.getPlayers();
			for(int i = 0; i < defeated.length; i++){
				if(defeated[i] != null){
					exp += ((Creature)defeated[i]).getXpValue();
				}
			}
			Entity[] victor = players.getPlayers();
			for(int i = 0; i < victor.length; i++){
				if(!victor[i].isDead()){
					if(victor[i] != null){
						((Player)victor[i]).addXP(exp);
					}
				}
			}
		}
		return exp;
	}
}