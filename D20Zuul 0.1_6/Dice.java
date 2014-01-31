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
     * @param int - # of dice
     * @param int - sides of dice
     * rolls diceDsides (3d6, 5d12, 2d8)
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
     * @param int - # of dice
     * @param int - sides of dice
     * rolls diceDsides (3d6, 5d12, 2d8)
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
