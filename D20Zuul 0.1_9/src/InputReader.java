import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * InputReader - accepts keyboard input for various data types.
 * 
 * @author Colleen
 * @version 2013.10.15
 */
public class InputReader
{
    private Scanner reader;

    /**
     * Constructor for objects of class InputReader
     */
    public InputReader()
    {
        reader = new Scanner(System.in); // default input
    }
    
    /**
     * Reads string input from keyboard.
     * @return input string
     */
    public String readString()
    {
    	return reader.nextLine();
    }
    
    /**
     * Reads integer input from keyboard.
     * @return input or zero if int not typed
     */
    public int readInt()
    {
        int theInt = 0;
        try {
            theInt = reader.nextInt();
        }
        catch(InputMismatchException e) {} // won't crash if not an int
        reader.nextLine(); // clear buffer ready for next input
        return theInt; // still 0 if exception caught
    }
    
    /**
     * Reads double input from keyboard.
     * @return input or zero if double not typed
     */
    public double readDouble()
    {
        double theDouble = 0.0;
        try {
            theDouble = reader.nextDouble();
        }
        catch(InputMismatchException e) {} // won't crash if not double
        reader.nextLine(); // clear buffer ready for next input
        return theDouble; // still 0.0 if exception caught
    }

    /**
     * Reads boolean input from keyboard.
     * @return input or false if boolean not typed
     */
    public boolean readBoolean()
    {
        boolean theBoolean = false;
        try {
            theBoolean = reader.nextBoolean();
        }
        catch(InputMismatchException e) {} // won't crash if not boolean
        reader.nextLine(); // clear buffer ready for next input
        return theBoolean; // still false if exception caught
    }

}
