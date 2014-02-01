
/**
 * This class holds objects of type Monster or PlayerCharacter depending on the constructor used for loading
 * into combat or as an object for the players to store their party
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Party
{
    private static final int PLAYERS = 4;
    private PlayerCharacter[] players;
    /**
     * Constructor for PlayerCharacters
     */
    public Party()
    {
        players = new PlayerCharacter[PLAYERS];
    }
    
    /**
     * constructor for Monsters, number of the monsters in the group set by int
     * @param int
     */
    public Party(int monsters)
    {
        this.monsters = new Monster[monsters];
    }

    /**
     * adds the player to the party
     * @param PlayerCharacter
     */
    public void joinParty(PlayerCharacter player)
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
     * adds a monster to a monster party for combat
     * @param Monster
     */
    public void monsterGroup(Monster monster)
    {
        boolean placed = false;
        if(monster != null){
            for(int i = 0; i < monsters.length; i++){
                if(monsters[i] == null && !placed){
                    monsters[i] = monster;
                    placed = true;
                }
            }
        }
    }
    
    /**
     * checks to see if the Party is empty and returns false if not empty
     */
    public boolean isEmpty()
    {
        if(players.length == 0 && monsters.length == 0){
            return true;
        }
        else{
            return false;
        }
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
        for(int i = 0; i < monsters.length; i++){
            if(!monsters[i].isDead()){
                wiped = false;
            }
        }
        return wiped;
    }
}
