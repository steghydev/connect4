package con4.consoleUtil;
import java.util.Scanner;
import java.util.Set;

/**
 * This class contains static methods that receive the data entered 
 * by the user into the terminal after first checking it against 
 * specific criteria.
 * @author Simone Gentili
 *
 */
public class ConsoleReceiver
{
	private static final String QUIT_OPTION = "Q"; //Option to exit
	
	private static Scanner input = new Scanner(System.in);
	
	/**
	 * It takes a value entered by the user from the terminal and 
	 * checks that it is greater than or equal to the first argument 
	 * or less than or equal to the second argument and if it isn'it
	 * the method throws an IllegalArgumentException.
	 * @param min The minimum value.
	 * @param max The maximum value.
	 * @return An integer corresponding to the value entered by the user
	 * 			from the terminal.
	 */
    public static int nextInt(int min, int max)
    {
    	int value;
    	if(input.hasNextInt())
    	{
    		value = input.nextInt();
    		if( value < min || value > max)
    		{
    			throw new IllegalArgumentException("The insert value is not valid");
    		}
    		else
    		{
    			return value;
    		}
    	}
    	else 
    	{
    		input.next();
    		throw new IllegalArgumentException("The insert value is not valid");
    	}
    }   
	    
    /**
     * It takes a value entered by the user from the terminal and checks that 
     * it is contained within the specified set and if it isn'it the method
     * throws an IllegalArgumentException.
     * If the user enters a character that is not a number, then the method checks 
     * whether this last entered value is equal to Q or q and, if it is, returns 
     * the value -1. Otherwise throw an exception.
     * @param aSet A set of integer values.
     * @return An integer corresponding to the value entered by the user
	 * 			from the terminal.
     */
    public static int nextIntIf(Set<Integer> aSet) 
    {
      	int value = 0;
    	if(input.hasNextInt())
    	{
    		value = input.nextInt();
    		
    		if( !aSet.contains(value) )
    		{
    			throw new IllegalArgumentException("Only the numbers contained in the set "
    											+ "are accepted");
    		}
    	}
    	else if(input.hasNext())
    	{
    		if ( input.next().toUpperCase().equals(QUIT_OPTION) )
    		{
    			value = -1;
    		}
    		else
    		{
    			throw new IllegalArgumentException("Only the numbers contained in the "
    					                 + "set or the character Q (or q) are accepted");
    		}
    	}
    	return value;
    }
	    
    /**
     * Receives a value entered by the user from the command line and returns that value.
     * @return The value entered by the user.
     */
	public static String nextString() 
	{
	    String value = "";
	    if(input.hasNext())
	   	{
    		value = input.next();
    		return value;
    	} 
	    return value;
	}
}
