package con4.essential;
import java.util.ArrayList;

import con4.tools.Chip;

/**
 * This class has the responsibility of establishing and 
 * knowing the positions of the Chip objects in the grid 
 * that have formed a consecutive sequence 4 long.
 * @author Simone Gentili
 */
public class WinnerGrid
{
	public static int[][] winnerPositions;
	
	/**
	 * Sets the grid of the specified Force4Grid object so that the
	 * Chip objects that make up the consecutive sequence of 4 Chips
	 * are most visible.
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 */
	private static void setWinnerGrid(Chip aChip, Connect4Grid aGrid)
	{		
		String value = aChip.getValue();
		String newValue = "(" + value + ")";
		Chip newChip = new Chip(newValue);
		
		for(int[] pos : winnerPositions)
		{
			int y = pos[0]; 
			int x = pos[1];
			
			aGrid.setValueAt(y, x, newChip);
		}
	}

	/**
	 * Invokes the static public getPositions() method of the GridExaminer
	 * class and if the return value of this method (it is a vector) has a
	 * length equal to 4, it saves the positions contained in the vector 
	 * and returns true.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 * @return true if the specified Chip resulted in a consecutive 
	 *         sequence of 4 Chip objects in the grid, false otherwise.
	 */
	public static boolean isWinner(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		ArrayList<int[]> result = GridExaminer.getPositions(y, x, aChip, aGrid);
		if( result.size() == 4 )
		{
			winnerPositions = new int[4][2];
			winnerPositions[0][0] = result.get(0)[0]; 
			winnerPositions[0][1] = result.get(0)[1];
			
			winnerPositions[1][0] = result.get(1)[0];
			winnerPositions[1][1] = result.get(1)[1];
			
			winnerPositions[2][0] = result.get(2)[0];
			winnerPositions[2][1] = result.get(2)[1]; 
			
			winnerPositions[3][0] = result.get(3)[0];
			winnerPositions[3][1] = result.get(3)[1];
		
			setWinnerGrid(aChip, aGrid);
			return true;
		}
		else
		{
			return false;
		}
	}

}
