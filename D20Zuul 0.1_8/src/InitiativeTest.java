
/**
 * Write a description of class InitiativeTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InitiativeTest
{
    public InitiativeTest()
    {
        
    }

    public void testInitiative(Party party)
    {
        Initiative init = new Initiative();
        for(int i = 0; i < 50000; i++){
            init.pcInit(party);
        }
    }
}
