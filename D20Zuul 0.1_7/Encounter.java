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
    
    /**
     * default constructor for class Encounter
     */
    public Encounter()
    {
        Party players = new Party();
        Party monsters = new Party();
        Combat combat = new Combat();
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
        Combat combat = new Combat();
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
}
