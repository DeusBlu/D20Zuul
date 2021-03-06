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
     * loops through the player party and seeds them into a stack in order of initiative lowest to highest
     * @param Party - the party to have initiative done for them
     * @return Stack<PlayerCharacter> - the initiative order in action from first to last, just pop
     */
    public Stack<PlayerCharacter> pcInit(Party playerParty)
    {
        Party party = cloneParty(playerParty);
        Stack<PlayerCharacter> initiative = new Stack<PlayerCharacter>();
        setInitiative(party);
        while(!party.isEmpty()){
            while(!isTied(party) && !party.isEmpty()){
                moveLowest(party, initiative);
            }
            Party tied = new Party();
            if(!party.isEmpty() && isTied(party)){
                tied = removeTied(party);
            }
            breakTie(tied, initiative);
        }
        return initiative;
    }
    
    /**
     * takes the tied party members and loops within itself to break down ties
     * @param Party - a party of tied party members
     * @param Stack<PlayerCharacter> - the stack to put the playrs into
     */
    private void breakTie(Party tied, Stack<PlayerCharacter> initiative)
    {
        while(!tied.isEmpty()){
            setInitiative(tied);
            while(!isTied(tied) && !tied.isEmpty()){
                moveLowest(tied, initiative);
            }
            breakTie(tied, initiative);
        }
    }
    
    /**
     * moves the current highest initiative from the party object to the stack
     * @param Party - the part to move from
     * @param Stack<PlayerCharacter> - the initiative stack to be loaded
     */
    private void moveLowest(Party party, Stack<PlayerCharacter> pcInit)
    {
        if(getLowest(party) >= 0 && party.getPlayers()[getLowest(party)] != null){
            pcInit.push(party.remove(getLowest(party)));
        }
    }
    
    /**
     * clones the party object so it can be emptied without effecting the original group of the players
     * @param Party - the party to clone
     * @return Party - the cloned party
     */
    private Party cloneParty(Party party)
    {
        Party clone = new Party();
        for(int i = 0; i < party.getPlayers().length; i++){
            if(party.getPlayers()[i] != null){
                clone.getPlayers()[i] = party.getPlayers()[i];
            }
        }
        return clone;
    }
    
    /**
     * randomizes the initiatize of all the party members
     * working with
     * @param Party playerParty
     */
    private void setInitiative(Party party)
    {
        for(int i = 0; i < party.getPlayers().length; i++){
            if(party.getPlayers()[i] != null){
                party.getPlayers()[i].setInit(dice.roll(1, 20));
                //this sets the initiative
            }
        }
    }
    
    /**
     * accepts array object and checks to see if players are tied for initiative then returns boolean
     * true if there is a tie for the current initiative spot
     * @param Party - the party object players are removed from
     * @return boolean - true if tied
     */
    private boolean isTied(Party party)
    {
        for(int i = 0; i < party.getPlayers().length; i++){
            if(party.getPlayers()[i] != null && i != getLowest(party)){
                if(party.getPlayers()[getLowest(party)].getInit() == 
                party.getPlayers()[i].getInit() && i != getLowest(party)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * takes objects that are tied for highest out of the party and transfers them into a party object for returning
     * initiative
     * @param Party - the party to take players out of
     * @return Party - a party made up of the tied party members
     */
    private Party removeTied(Party party)
    {
        Party tied = new Party();
        int highest = party.getPlayers()[getLowest(party)].getInit();
        for(int i = 0; i < party.getPlayers().length; i++){
            if(party.getPlayers()[i] != null && party.getPlayers()[i].getInit() == highest){
                tied.join(party.remove(i));
            }
        }
        return tied;
    }
    
    /**
     * method to find which player currently has the lowest or which one is tied for the lowest
     * and returns the index location of the array the player is found at.
     * @param Party - the party to check
     * @return int - the index of the highest party member
     */
    private int getLowest(Party tempParty)
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
