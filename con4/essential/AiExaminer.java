package con4.essential;
import con4.tools.Chip;

/**
 * This class defines the methods necessary for AI to control 
 * the grid and make decisions. The checks are iterative.
 * The controls are basically 3 (oblique, horizontal and vertical) 
 * and to work they use the methods isFreeThatPos () and isTheSameObj ().
 * Respectively these last two methods check if a position is free
 * and if the considered position is occupied by a specific Chip object.
 * @author Simone Gentili
 */
public class AiExaminer 
{
	//The value to return if no valid solution has been found.
	private static final int ABANDON = -1;
	
	/**
	 * Checks that the position at specified coordinates of the
	 * specified grid is free. If it's free, returns true, otherwise
	 * it returns false.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aGrid A Connect4Grid object to check.
	 * @return true if the specified position is free, otherwise
	 * 			returns false.
	 */ 
	public static boolean isFreeThatPos(int y, int x, Connect4Grid aGrid)
	{
		if( y >= aGrid.getHeight() || y < 0 || x >= aGrid.getWidth() || x < 0) 
		{ 
			return false; 
		}
		
		Chip chipAt = (Chip) aGrid.getValueAt(y, x);
		
		if ( chipAt.equals(new Chip(Chip.EMPTY)) )
		{
			return true;
		}
		
		return false; 
	}
	
	/**
	 * Checks the specified position from the coordinates entered 
	 * in the specified grid and returns true if it contains a Chip 
	 * object that has the same value as the specified Chip object.
	 * @param y The x coordinate.
	 * @param x The y coordinate.
	 * @param aChip A Chip object
	 * @param aGrid A Connect4Grid object
	 * @return true if the specified position contains a Chip object that 
	 * 			has the same value as the specified Chip object, otherwise
	 * 			it returns false.
	 */
	public static boolean isTheSameChip(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		if( y >= aGrid.getHeight() || y < 0 || x >= aGrid.getWidth() || x < 0) 
		{ 
			return false; 
		}
		
		Chip chipAt = (Chip) aGrid.getValueAt(y, x);
		
		if( chipAt.equals(aChip) ) 
		{ 
			return true; 
		}
		
		return false; 	
	}
	
	/**
	 * Controls the grid following oblique directions from the position
	 * specified in the method argument. Returns the value of the column 
	 * that would allow to obtain a consecutive sequence of 4 Chip objects
	 * having the same value of the specified Chip object within the grid.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 * @return The result of the check. it can be -1 if it found nothing,
	 *         otherwise an integer greater than 0
	 */
	public static int obliqueControls(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		if(isTheSameChip(y-1, x-1, aChip, aGrid) && isTheSameChip(y+1,  x+1, aChip, aGrid)) 
		{
			if(isFreeThatPos(y-2, x-2, aGrid) || isFreeThatPos(y+2,x+2, aGrid)) 
			{
				if(!isFreeThatPos(y-1, x-2, aGrid) || !isFreeThatPos(y+3, x+2, aGrid))
				{
					return x-2;
				}
			} 
		}
		
		if(isTheSameChip(y-1, x+1, aChip, aGrid) && isTheSameChip(y+1,  x-1, aChip, aGrid)) 
		{
			if(isFreeThatPos(y+2, x-2, aGrid) || isFreeThatPos(y-2,x+2, aGrid)) 
			{
				if(!isFreeThatPos(y+3, x-2, aGrid) || !isFreeThatPos(y-1, x+2, aGrid))
				{
					return x-2;
				}
			}
		}
		
		if(isTheSameChip(y+2, x-2, aChip, aGrid)) 
		{
			if(isFreeThatPos(y+1, x-1, aGrid) && !isFreeThatPos(y+2, x-1, aGrid)) 
			{
				if(isTheSameChip(y-1, x+1, aChip, aGrid) || isTheSameChip(y+3, x-3, aChip, aGrid))
				{
					return x-1;
				}
			}	
		}
		
		if(isTheSameChip(y+1, x-1, aChip, aGrid))
		{
			if(isFreeThatPos(y-1, x+1, aGrid) && !isFreeThatPos(y,x+1, aGrid)) 
			{
				if(isTheSameChip(y-2, x+2, aChip, aGrid) || isTheSameChip(y+2, x-2, aChip, aGrid))
				{
					return x+1;
				}
			}
		} 
		
		return ABANDON;
	}
	
	/**
	 * Controls the grid following horizontal directions from the position
	 * specified in the method argument. Returns the value of the column 
	 * that would allow to obtain a consecutive sequence of 4 Chip objects
	 * having the same value of the specified Chip object within the grid.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 * @return The result of the check. it can be -1 if it found nothing,
	 *         otherwise an integer greater than 0
	 */
	public static int horizontalControls(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		if(isTheSameChip(y, x-1, aChip, aGrid) && isTheSameChip(y, x+1, aChip, aGrid))
		{
			if(isFreeThatPos(y, x-2, aGrid)) 
			{
				if(!isFreeThatPos(y+1, x-2, aGrid))
				{
					return x-2;	
				} 
			}
			
			if(isFreeThatPos(y, x+2, aGrid)) 
			{
				if(!isFreeThatPos(y+1, x+2, aGrid))
				{
					return x+2;	
				} 
			} 
		}
		
		if(isTheSameChip(y, x-1, aChip, aGrid)) 
		{
			if(isTheSameChip(y, x+2, aChip, aGrid) && isFreeThatPos(y, x+1, aGrid) 
					&& !isFreeThatPos(y+1, x+1, aGrid))
			{
				return x+1;
			}
			
			if(isFreeThatPos(y, x-2, aGrid) && isTheSameChip(y, x-3, aChip, aGrid)
					&& !isFreeThatPos(y+1, x-2, aGrid))
			{
				return x-2; 
			}
			if(isTheSameChip(y, x-2, aChip, aGrid))
			{
				if(isFreeThatPos(y, x-3, aGrid) && !isFreeThatPos(y+1, x-3, aGrid))
				{
					return x-3;
				}
				
				if(isFreeThatPos(y, x+1, aGrid) && !isFreeThatPos(y+1, x+1, aGrid))
				{
					return x+1;
				}
			}
		}
		
		if(isTheSameChip(y, x-2, aChip, aGrid) && isFreeThatPos(y, x-1, aGrid) &&
			isTheSameChip(y, x-3, aChip, aGrid) && !isFreeThatPos(y+1, x-1, aGrid))
		{
			return x-1;
		}
		
		if(isTheSameChip(y, x+1, aChip, aGrid))
		{
			if(isTheSameChip(y, x-2, aChip, aGrid) && isFreeThatPos(y, x-1, aGrid) 
					&& !isFreeThatPos(y+1, x-1, aGrid))
			{
				return x-1;
			}
			
			if(isFreeThatPos(y, x+2, aGrid) && isTheSameChip(y, x+3, aChip, aGrid)
					&& !isFreeThatPos(y+1, x+2, aGrid))
			{
				return x+2; 
			}
			
			if(isTheSameChip(y, x+2, aChip, aGrid)) 
			{
				if(isFreeThatPos(y, x+3, aGrid) && !isFreeThatPos(y+1, x+3, aGrid))
				{
					return x+3;
				}
				
				if(isFreeThatPos(y, x-1, aGrid) && !isFreeThatPos(y+1, x-1, aGrid))
				{
					return x-1; 
				}
			}
		}
		
		if(isTheSameChip(y, x+2, aChip, aGrid) && isFreeThatPos(y, x+1, aGrid) &&
			isTheSameChip(y, x+3, aChip, aGrid) && !isFreeThatPos(y+1, x+1, aGrid))
		{
			return x+1;
		}
		
		return ABANDON;
	}
	
	/**
	 * Controls the grid following the unique vertical direction from the position
	 * specified in the method argument. Returns the value of the column 
	 * that would allow to obtain a consecutive sequence of 4 Chip objects
	 * having the same value of the specified Chip object within the grid.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 * @return The result of the check. it can be -1 if it found nothing,
	 *         otherwise an integer greater than 0
	 */
	public static int verticalControls(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		if(isTheSameChip(y+1, x, aChip, aGrid) && isTheSameChip(y+2, x, aChip, aGrid)
				&& isFreeThatPos(y-1, x, aGrid))
		{
			return x;
		}	
		
		return ABANDON;	
	}

}