package con4.consoleUtil;
import java.util.Set;

/**
 * This class has static methods to print messages and receive values entered by the user
 * from the command line. If the user enters invalid values, the method continues waiting 
 * for the user to enter a valid value (possibly printing an error message to warn him).
 * The TimedPrinter class is used to print messages, so the messages are printed in a timed 
 * manner.
 * @author Simone Gentili
 *
 */
public class InOutController
{
	//They contain the print speeds of the strings.
	private static final int LOW_SPEED = 15;  
	private static final int MID_SPEED = 10; 
	
	/**
	 * It prints the specified message and takes a value entered by 
	 * the user from the terminal and checks that it is greater than 
	 * or equal to the first argument or less than or equal to the 
	 * second argument and if it isn'it the method print the specified 
	 * error message. The user must enters a value until it is valid.
	 * @param min The minimun value.
	 * @param max The maximun value.
	 * @param message The message to print.
	 * @param error  The error message to print if the user enters an invalid value.
	 * @return The entered value by the user from the terminal.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	public static int getIntAnswer(int min, int max, String message, String error) throws InterruptedException
	{
		int result;
		
		TimedPrinter.printlnMessage(message, LOW_SPEED);
		while(true)
		{
			try
			{
				result = ConsoleReceiver.nextInt(min, max);
				break;
			}
			catch( IllegalArgumentException e )
			{
				TimedPrinter.printlnMessage(error, LOW_SPEED);
			}
		}
		return result;
	}
	
    /**
     * It prints the specified message and takes a value entered by the user 
     * from the terminal and checks that it is contained within the specified 
     * set and, if the user enters a character that is not a number, 
     * then the method checks whether this last entered value is equal to Q or q and, 
     * if it is, returns the value -1. Otherwise if the user does not enter a valid value, 
     * the error message is printed and the user must enter a value until it is valid.
     * @param aSet A set of integer values.
     * @param message The massege to print
     * @param error   The error message to print if the user enters an invalid value.
     * @return An integer corresponding to the value entered by the user
	 * 			from the terminal.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
     */
	public static int getChoiseValueIn(Set<Integer> aSet, String message, String error) throws InterruptedException
	{
		int result;
		
		TimedPrinter.printlnMessage(message, LOW_SPEED);
		while(true)
		{
			try
			{
				result = ConsoleReceiver.nextIntIf(aSet);
				break;
			}
			catch( IllegalArgumentException e )
			{
				TimedPrinter.printlnMessage(error, LOW_SPEED);
			}
		}
		return result;
	}
	
	/**It prints the specified message and receives a value entered by the user from the command line 
	 * and returns that value.
     * @return The value entered by the user.
     * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
     */
	public static String getStringAnswer(String message) throws InterruptedException
	{
		String result;
		
		TimedPrinter.printlnMessage(message, LOW_SPEED);
		result = ConsoleReceiver.nextString();
		return result;
	}
		
	/**
	 * It prints the specified menu (array of String object) and takes a 
	 * value entered by the user from the terminal and checks that it is 
	 * greater than or equal to the first argument or less than or equal 
	 * to the second argument and if it isn'it the method print the specified 
	 * error message. The user must enters a value until it is valid.
	 * @param min The minimun value.
	 * @param max The maximun value.
	 * @param message The message to print.
	 * @param error  The error message to print if the user enters an invalid value.
	 * @return The entered value by the user from the terminal.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	public static int getIntAnswer(int min, int max, String[] menu, String error) throws InterruptedException
	{
		int result;
		
		TimedPrinter.printMenu(menu, MID_SPEED);
		while(true)
		{
			try
			{
				result = ConsoleReceiver.nextInt(min, max);
				break;
			}
			catch( IllegalArgumentException e )
			{
				TimedPrinter.printlnMessage(error, LOW_SPEED);
			}
		}
		return result;
	}
	

}
