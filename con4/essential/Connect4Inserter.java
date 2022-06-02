package con4.essential;
import con4.tools.Chip;

/**
 * This class defines the methods necessary for inserting a Chip 
 * object into the grid (Force4Grid object). Each time a Chip object
 * is inserted into the grid, the insertChipAt () method informs
 * the Force4 class of the position of the inserted Chip object 
 * and increments the turn counter. Every time a Chip object is 
 * inserted into the grid, it is checked whether this insertion 
 * resulted in a win for the player who inserted it.
 * @author Simone Gentili
 */
public class Connect4Inserter 
{
	public static final int END = 1;
	public static final int CONTINUE = -1;
	public static final int PARITY = 0;
	
	/**
	 * Inserts the chip into the specified column within the grid 
	 * of the Force4Grid object contained in the specified Force4 
	 * object.vEach time the Chip object is inserted, the position 
	 * is communicated to the Force4 class and the winnings are 
	 * checked.
	 * The method after the check can return 3 possible values:
	 * 1 if the player who entered the chip object has won, 
	 * 0 if, as a result of the entry, the grid is full and -1 
	 * if there has been no winner.
	 * @param aColumn A positive integer number.
	 * @param force4 A Force4 object.
	 * @return 1, 0 or -1.
	 */
	public static int insertChipAt(int aColumn, Connect4 force4) 
	{
		Chip chip = force4.getChipPlayerTurn();
		
		int[] pos = putChip(aColumn, chip, force4.grid);
		
		int y = pos[0];
		int x = pos[1];
		
		force4.addMovement(force4.shiftManager.getPlayerTurn(), y, x);
		
		force4.counter.increase();
		
		if(WinnerGrid.isWinner(y, x, chip, force4.grid))
		{
			return END;
		}
		
		else if( force4.grid.isFull() )
		{
			return PARITY;
		}
		
		else 
		{  
			return CONTINUE; 
		}
		
	}
	
	/**
	 * Inserts the specified Chip object into the specified column 
	 * within the specified Force4Grid object. The chip runs along 
	 * the entire column and moves to the first available position 
	 * in that column. The method finally returns the position of 
	 * the inserted Chip object.
	 * @param x The column.
	 * @param aChip A Chip object.
	 * @param grid A Force4Grid object
	 * @return The position of the inserted chip.
	 */
	private static int[] putChip(int x, Chip aChip, Connect4Grid grid) 
	{
		if(!grid.getFreeColumn().contains(x))
		{
			throw new IllegalArgumentException("Column number not valid");
		}
		
		int[] pos = new int[2];
		int height = grid.getHeight();
		
		for(int y = 0; y < height; y++)
		{
			if( y == height-1 ) 
			{ 
				//it's the last line
				grid.setValueAt(y, x, aChip); 
				pos[0] = y; pos[1] = x;
				break;
			}	
			
			Chip chipAt = (Chip) grid.getValueAt(y, x); 
			if( chipAt.equals(new Chip(Chip.EMPTY)) )
			{
				Chip chipAtNext = (Chip) grid.getValueAt(y+1, x);
				if( !(chipAtNext.equals(new Chip(Chip.EMPTY)) ) )
				{
					if( y == 0 ) 
					{ 
						//the newly inserted chip filled the column.
						grid.getFreeColumn().remove(x); 
					}
					grid.setValueAt(y, x, aChip); 									
					pos[0] = y; pos[1] = x;
					break;
				}
			}
		} 
		return pos;
	}	

}
