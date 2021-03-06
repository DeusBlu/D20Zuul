
/**
 * Write a description of class MonParty here used for groups of monsters in combat
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class MonParty
{
    private static final int MAX_MONSTERS = 10;
    private Monster[] monsters;
    /**
     * Constructor for objects of class MonParty
     */
    public MonParty()
    {
        monsters = new Monster[MAX_MONSTERS];
    }
    
    /**
     * adds a monster to a monster party for combat
     * @param Monster
     */
    public void join(Monster monster)
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
        if(monsters.length == 0){
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
        for(int i = 0; i < monsters.length; i++){
            if(!monsters[i].isDead()){
                wiped = false;
            }
        }
        return wiped;
    }
    
    /**
     * prints a list of all monsters left and numbers them for combat selection
     */
    public void list()
    {
        for(int i = 0; i < monsters.length; i++){
            if(monsters[i] != null){
                System.out.println("#" + (1+i) + ": " + monsters[i].getName());
            }
        }
    }
}
