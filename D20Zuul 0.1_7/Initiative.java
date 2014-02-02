import java.util.Stack;
/**
 * takes in the party objects and creates a turn order then returns a stack
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Initiative
{
    private MonParty monsters;
    private Party players;
    private Stack<Monster> monInit;
    private Dice dice;
    /**
     * default constructor for object Initiative
     */
    public Initiative()
    {
        monInit = new Stack<Monster>();
        dice = new Dice();
    }
    
    /**
     * constructor that accepts both parties and initializes the stacks
     */
    public Initiative(MonParty monsters, Party players)
    {
        monInit = new Stack<Monster>();
        dice = new Dice();
    }

    /**
     * loops through the player party and seeds them into a stack in order of initiative lowest to highest
     */
    public Stack<PlayerCharacter> pcInit(Party playerParty)
    {
        Stack<PlayerCharacter> pcInit = new Stack<PlayerCharacter>();
        PlayerCharacter[] pcArray = new PlayerCharacter[Party.PLAYERS];
        for(int i = 0; i < Party.PLAYERS; i++){
            pcArray[i] = playerParty.getPlayers()[i];
            if(pcArray[i] != null){
                pcArray[i].setInit(dice.roll(1, 20));
                //sets the initiative of the players
            }
        }
        Party tempParty = new Party(pcArray);
        while(!tempParty.isEmpty()){
            int currentIndex = getHighest(playerParty);
            for(int i = 0; i < tempParty.getPlayers().length; i++){
                PlayerCharacter[] tied = new PlayerCharacter[Party.PLAYERS];
                int addedToTied = 0;
                if(tempParty.getPlayers()[i] != null && i != currentIndex){
                    if(tempParty.getPlayers()[currentIndex].getInit() == 
                    tempParty.getPlayers()[i].getInit()){
                        tied[addedToTied] = tempParty.getPlayers()[i];
                        addedToTied++;
                        tempParty.remove(i);
                    }
                }
                if(addedToTied == 0){
                    pcInit.push(tempParty.getPlayers()[currentIndex]);
                    tempParty.remove(currentIndex);
                }
                else{
                    breakTie(tied);
                }
            }
        }
        return pcInit;
    }
    
    /**
     * this re-rolls the tied party members initiative
     */
    private PlayerCharacter[] breakTie(PlayerCharacter[] tied)
    {
        return null;
    }
    
    /**
     * method to find which player currently has the highest or which one is tied for the highest
     * and returns the index location of the array the player is found at.
     */
    private int getHighest(Party tempParty)
    {
        int currentLow = Integer.MAX_VALUE;
        int currentIndex = -1;
        for(int i = 0; i < tempParty.getPlayers().length; i++){
            if(tempParty.getPlayers()[i] != null){
                if(tempParty.getPlayers()[i].getInit() <= currentLow){
                    currentLow = tempParty.getPlayers()[i].getInit();
                    currentIndex = i;
                }
            }
        } //at this point we have found the current lowest init value
        return currentIndex;
    }
}
