/**
 * Write a description of class Dice here.
 * 
 * @author (DeusBlu) 
 * @version (0.1_3)
 */
public class Dice
{
	
    /**
     * Constructor for objects of class Dice
     */
    public Dice()
    {
    }

    /**
     * rolls dice-D-sides (3d6, 5d12, 2d8) and outputs the results in console
     * @param int - # of dice
     * @param int - sides of dice
     */
    public int rollPrint(int dice, int sides)
    {
        int roll = 0;
        System.out.print("Your Rolls: ");
        for(int i = 0; i < dice; i++){
            int aRoll = BetterRandom.random(sides)+1;
            roll += aRoll;
            System.out.print(" " + aRoll);
        }
        return roll;
    }
    
    /**
     * rolls dice-D-sides (3d6, 5d12, 2d8)
     * @param int - # of dice
     * @param int - sides of dice
     */
    public int roll(int dice, int sides)
    {
        int roll = 0;
        for(int i = 0; i < dice; i++){
            roll += BetterRandom.random(sides-1)+1;
        }
        return roll;
    }
}
