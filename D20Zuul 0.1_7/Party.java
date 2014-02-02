
/**
 * This class holds objects of type PlayerCharacter used for loading
 * into combat or as an object for the players to store their party
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Party
{
    public static final int PLAYERS = 4;
    private PlayerCharacter[] players;
    /**
     * Constructor for empty party object
     */
    public Party()
    {
        players = new PlayerCharacter[PLAYERS];
    }
    
    /**
     * constructor for parties of pre-build arrays
     */
    public Party(PlayerCharacter[] players)
    {
        this.players = players;
    }

    /**
     * adds the player to the party
     * @param PlayerCharacter
     */
    public void join(PlayerCharacter player)
    {
        boolean placed = false;
        if(player != null){
            for(int i = 0; i < PLAYERS; i++){
                if(players[i] == null && !placed){
                    players[i] = player;
                    placed = true;
                }
            }
            if(!placed){
                System.out.println("Your party is full");
            }
        }
        else{
            System.out.println("Player object was null!!!");
        }
    }
    
    /**
     * checks to see if the Party is empty and returns false if not empty
     */
    public boolean isEmpty()
    {
        if(players.length == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * returns the player object at the requested index
     */
    public PlayerCharacter[] getPlayers()
    {
        return players;
    }
    
    /**
     * returns true if either party is dead
     */
    public boolean isDefeated()
    {
        boolean wiped = true;
        for(int i = 0; i < players.length; i++){
            if(!players[i].isDead()){
                wiped = false;
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
                System.out.println("#" + (i+1) + ":  Name: " + players[i].getName() +
                "  HP: " + players[i].showHP());
            }
        }
    }
    
    /**
     * prints the full status of the Player Character
     */
    public void status(int player)
    {
        if(players[player] != null){
            players[player].status();
        }
        else{
            System.out.println("There is no player there");
        }
    }
    
    /**
     * removes a character from the party setting the pointer back to null
     */
    public void remove(int spot)
    {
        players[spot] = null;
    }
}
