
/**
 * This class holds objects of type PlayerCharacter used for loading
 * into combat or as an object for the players to store their party
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Party
{
    private Entity[] players;
    /**
     * Constructor for empty party object
     */
    public Party()
    {
        players = new Entity[Constant.MAX_PARTY];
    }
    
    /**
     * constructor for parties of players sending boolean sets to player
     * party max size
     * @param boolean true or false will set the max players to MAX_PLAYERS
     */
    public Party(boolean forPlayers)
    {
        this.players = new Entity[Constant.MAX_PLAYERS];
    }

    /**
     * adds the player to the party
     * @param PlayerCharacter
     */
    public void join(Entity player)
    {
        boolean placed = false;
        if(player != null){
            for(int i = 0; i < players.length; i++){
                if(players[i] == null && !placed){
                    players[i] = player;
                    placed = true;
                }
            }
        }
        else{
            throw new IllegalArgumentException("Player object was null");
        }
    }
    
    /**
     * checks to see if the Party is empty and returns false if not empty
     */
    public boolean isEmpty()
    {
        for(int i = 0; i < players.length; i++){
            if(players[i] != null){
                return false;
            }
        }
        return true;
    }
    
    /**
     * returns the player object at the requested index
     */
    public Entity[] getPlayers()
    {
        return players;
    }
    
    /**
     * returns the number of entities in the party as an int
     * @return number of players as an int
     */
    public int getNumberPlayers()
    {
    	int numPlayers = 0;
    	for(int i = 0; i < players.length; i++){
    		if(players[i] != null){
    			numPlayers++;
    		}
    	}
    	return numPlayers;
    }
    
    /**
     * returns true if either party is dead
     */
    public boolean isDefeated()
    {
        boolean wiped = true;
        for(int i = 0; i < players.length; i++){
        	if(players[i] != null){
        		if(!players[i].isDead()){
                	return false;
            	}
        	}
        }
        return wiped;
    }
    
    /**
     * prints the status of the party in short form
     */
    public void shortStatus()
    {
        for(int i = 0; i < players.length; i++){
            if(players[i] != null){
            	if(!players[i].isDead()){
            		printStatus(players[i], i);
            	}
            	else if(players[i] instanceof Player){
            		printStatus(players[i], i);
            	}
            }
        }
    }
    
    private void printStatus(Entity player, int i)
    {
    	System.out.print("#"+(i+1)+":  Name: "+player.getName());
		if(player.getName().length() <= 4){
			System.out.print("\t\t");
		}
		else if(player.getName().length() > 4){
			System.out.print("\t");
		}
		if(player instanceof Player){
			System.out.print("HP: "+player.getCurrentHP()+"/"+player.getMaxHP());
		}
		System.out.println();
    }
    
    /**
     * removes a character from the party setting the pointer back to null and returns the character that was in that
     * location
     * @param int - the character to remove
     * @return PlayerCharacter
     */
    public Entity remove(int spot)
    {
    	Entity removed = players[spot];
        players[spot] = null;
        return removed;
    }
    
    public int getAverageLevel()
    {
    	int totalLevel = 0;
    	for (int i = 0; i < players.length; i++){
    		if(players[i] instanceof Player){
    			totalLevel += ((Player)players[i]).getPlayerClass().getLevel();
    		}
    	}
    	int avgLevel = 0;
    	if(totalLevel > 0){
    		avgLevel = totalLevel/players.length;
    	}
    	return avgLevel;
    }
    
    public int getTotalCR()
    {
    	double cr = 0;
    	for(int i = 0; i < players.length; i++){
    		if(players[i] instanceof Creature){
    			cr += ((Creature)players[i]).getChallengeRating();
    		}
    	}
    	int finalCR = 0;
    	finalCR += cr;
    	return finalCR;
    }
    
    private int getLiving()
    {
    	int living = 0;
    	for(int i = 0; i < players.length; i++){
    		if(!players[i].isDead()){
    			living++;
    		}
    	}
    	return living;
    }
    
    public int gainXP(int totalXPGained)
    {
    	int standingPlayers = getLiving();
    	int playerXP = totalXPGained/standingPlayers;
    	for(int i = 0; i < players.length; i++){
    		if(!players[i].isDead()){
    			((Player)players[i]).addXP(playerXP);
    		}
    	}
    	return playerXP;
    }
}
