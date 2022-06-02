package con4.tools;
/**
 * This class realizes a shift counter for applications 
 * that need a counter starting from a fixed value. 
 * The fixed value can be changed. The fixed value can be changed 
 * using the method setValueFirstTurn(int aNumber) or it is 
 * possible to set it directly during the invocation of the 
 * constructor method Turns(int aNumber). 
 * By default the constructor method with no arguments sets the 
 * first turn to the value 1.
 * Both constructor methods initialize the turn counter with the 
 * value of the first turn (fixed value).
 * @author Simone Gentili
 *
 */
public class ShiftsCounter 
{
	private final int DEFAULT_START = 0;
	private int turnCounter; //it contains the counter value.
	
	/**
	 * Build a turn counter. Initialize the counter 
	 * to a default value (1)
	 */
	public ShiftsCounter()
	{
		turnCounter = DEFAULT_START;
	}
			
	/**
	 * increases the turn counter
	 */
	public void increase()
	{
		turnCounter ++;
	}
		
	/**
	 * Sets the turn counter at the specified value.
	 * @param aNumber An integer.
	 */
	public void setShiftsCounter(int aNumber)
	{
		turnCounter = aNumber;
	}
	
	/**
	 * Sets the turn counter to 0.
	 */
	public void resetShiftsCounter()
	{
		turnCounter = DEFAULT_START;
	}
	
	/**
	 * Returns the currently value of the counter.
	 * @return An integer.
	 */
	public int getCurrentValue()
	{
		return turnCounter;
	}

}
