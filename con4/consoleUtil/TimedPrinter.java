package con4.consoleUtil;
/**
 * This class has static methods for printing messages on the screen.
 * The messages are printed in a timed manner by using the static 
 * Thread.sleep() method
 * @author Simone Gentili
 *
 */
public class TimedPrinter 
{			
	/**
	 * Prints the specified String array. The speed of printing depends 
	 * on the specified number.
	 * @param menu An array of String object.
	 * @param sleepValue long milliseconds.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	public static void printMenu(String[] menu, int sleepValue) throws InterruptedException
	{
		for(int i = 0; i < menu.length; i++)
		{
			printlnMessage(menu[i], sleepValue);
			Thread.sleep(sleepValue);
		}
	}
	
	/**
	 * Prints the specified String object. The speed of printing depends 
	 * on the specified number.
	 * @param menu The message to print.
	 * @param sleepValue long milliseconds.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	public static void printMessage(String message, int sleepValue) throws InterruptedException
	{
		for(int i = 0; i < message.length(); i++)
		{
			System.out.print(message.substring(i, i+1));
			Thread.sleep(sleepValue);
		}
	}
	
	/**
	 * Prints the specified String object
	 * @param menu The message to print.
	 */
	public static void printMessage(String message) 
	{
			System.out.print(message);
	}
	
	/**
	 * Prints the specified String object with the escape sequence "\n" at the end. 
	 * The speed of printing depends on the specified number.
	 * @param menu The message to print.
	 * @param sleepValue long milliseconds.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	public static void printlnMessage(String message, int sleepValue) throws InterruptedException
	{
		for(int i = 0; i < message.length(); i++)
		{
			System.out.print(message.substring(i, i+1));
			Thread.sleep(sleepValue);
		}
		System.out.println();
	}
	
	/**
	 * Prints the specified String object with the escape sequence "\n" at the end. 
	 * @param menu The message to print.
	*/
	public static void printlnMessage(String message) 
	{
			System.out.print(message + "\n");
	}
	
}
