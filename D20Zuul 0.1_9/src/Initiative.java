import java.util.Stack;
/**
 * takes in the party objects and creates a turn order then returns a stack
 * 
 * @author (DeusBlu) 
 * @version (0.1_7)
 */
public class Initiative
{
    private Dice dice;
    
    /**
     * default constructor for object Initiative
     */
    public Initiative()
    {
        dice = new Dice();
    }

    /**
     * loops through the player party and seeds them into a stack in order of initiative lowest to highest
     * @param Party - the party to have initiative done for them
     * @return Stack<Entity> - the initiative order in action from first to last, just pop
     */
    public Stack<Entity> pcInit(Party players, Party opponents)
    {
    	Party combatants = mergeParties(players, opponents);
        Stack<Entity> initiative = new Stack<Entity>();
        setInitiative(combatants);
        while(!combatants.isEmpty()){
            while(!isTied(combatants) && !combatants.isEmpty()){
                moveLowest(combatants, initiative);
            }
            Party tied = new Party();
            if(!combatants.isEmpty() && isTied(combatants)){
                tied = removeTied(combatants);
            }
            breakTie(tied, initiative);
        }
        return initiative;
    }
    
    /**
     * takes the tied party members and loops within itself to break down ties
     * @param Party - a party of tied party members
     * @param Stack<Entity> - the stack to put the playrs into
     */
    private void breakTie(Party tied, Stack<Entity> pcInit)
    {
        while(!tied.isEmpty()){
        	modTieBreak(tied, pcInit);
            setInitiative(tied);
            while(!isTied(tied) && !tied.isEmpty()){
                moveLowest(tied, pcInit);
            }
            breakTie(tied, pcInit);
        }
    }
    
    /**
     * breaks initiative ties based on the dex mod
     * @param tied
     * @param pcInit
     */
    private void modTieBreak(Party tied, Stack<Entity> pcInit)
    {
    	while(!modTied(tied) && !tied.isEmpty()){
    		moveLowestMod(tied, pcInit);
    	}
    }
    
    /**
     * returns true if the dex modifiers of the players are tied
     * @param party
     * @return boolean true if tied
     */
    private boolean modTied(Party party)
    {
    	for(int i = 0; i < party.getPlayers().length; i++){
            if(party.getPlayers()[i] != null && i != getLowestMod(party)){
                if(party.getPlayers()[getLowestMod(party)].getStatMod("dex") == 
                party.getPlayers()[i].getInit() && i != getLowestMod(party)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * moves the current highest initiative from the party object to the stack
     * @param Party - the part to move from
     * @param Stack<Entity> - the initiative stack to be loaded
     */
    private void moveLowest(Party party, Stack<Entity> pcInit)
    {
        if(getLowest(party) >= 0 && party.getPlayers()[getLowest(party)] != null){
            pcInit.push(party.remove(getLowest(party)));
        }
    }
    
    private void moveLowestMod(Party party, Stack<Entity> pcInit)
    {
    	if(getLowestMod(party) >= 0 && party.getPlayers()[getLowestMod(party)] != null){
            pcInit.push(party.remove(getLowest(party)));
        }
    }
    
    /**
     * clones a new party from 2 parties for sorting into the initiative stack
     * @param players
     * @param opponents
     * @return Party made up of all players in the 2 given parties
     */
    private Party mergeParties(Party players, Party opponents)
    {
    	Party combatants = new Party(20);
    	for(int i = 0; i < players.getPlayers().length; i++){
            if(players.getPlayers()[i] != null){
                combatants.getPlayers()[i] = players.getPlayers()[i];
            }
        }
    	for(int i = 0; i < opponents.getPlayers().length; i++){
            if(opponents.getPlayers()[i] != null){
                combatants.getPlayers()[i + players.getNumberPlayers()]
                		= opponents.getPlayers()[i];
            }
        }
    	return combatants;
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
    
    /**
     * returns the index of the lowest init mod
     * @param tempParty
     * @return index of lowest init mod as an int
     */
    private int getLowestMod(Party tempParty)
    {
    	int currentLow = Integer.MAX_VALUE;
        int currentIndex = -1;
        for(int i = 0; i < tempParty.getPlayers().length; i++){
            if(tempParty.getPlayers()[i] != null){
            	int dexMod = tempParty.getPlayers()[i].getStatMod("dex");
                if(dexMod <= currentLow){
                    currentLow = dexMod;
                    currentIndex = i;
                }
            }
        } //at this point we have found the current lowest init value
        return currentIndex;
    }
}
