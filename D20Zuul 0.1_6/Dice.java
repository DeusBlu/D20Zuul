import java.util.Random;
/**
 * Write a description of class Dice here.
 * 
 * @author (DeusBlu) 
 * @version (0.1_3)
 */
public class Dice
{
    // instance variables - replace the example below with your own
    private Random random;
    /**
     * Constructor for objects of class Dice
     */
    public Dice()
    {
        random = new Random();
    }

    /**
     * rolls dice-D-sides (3d6, 5d12, 2d8) and outputs the results in console
     * @param int - # of dice
     * @param int - sides of dice
     */
    public void rollDebug(int dice, int sides)
    {
        int roll = 0;
        System.out.print("Your Rolls: ");
        for(int i = 0; i < dice; i++){
            int aRoll = random.nextInt(sides-1)+1;
            roll += aRoll;
            System.out.print(" " + aRoll);
        }
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
            roll += random.nextInt(sides-1)+1;
        }
        return roll;
    }
}
