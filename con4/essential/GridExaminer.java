package con4.essential;
import java.util.ArrayList;

import con4.tools.Chip;
/**
 * This class defines a getPositions() method invoked to check (at each insertion 
 * of a new Chip object in the grid) the presence of a consecutive sequence of 4 
 * Chip objects within the grid. If it finds this sequence, the method returns a 
 * vector containing the coordinates of the Chips that produced this sequence in 
 * the grid.
 * @author Simone Gentili
 */
public class GridExaminer 
{	
	/**
	 * Checks the positions surrounding the location located by the specified 
	 * coordinates and returns an empty vector if it has not found any consecutive
	 * sequence of 4 Chip objects in the grid having the same value as the specified 
	 * Chip object, otherwise a vector along 4 containing the coordinates of the 
	 * points of the sequence .
	 * @param line The line (i.e the x coordinate of the Chip object)
	 * @param column The column (i.e the y coordinate of the Chip object)
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 * @return A vector ( ArrayList<int[]> object in particular ).
	 */
	public static ArrayList<int[]> getPositions(int line, int column, Chip aChip, Connect4Grid aGrid) 
	{		
		ArrayList<int[]> positions = new ArrayList<>();
		
		for(int y = -1; y < 2 ; y++)
		{	
			for(int x = -1; x < 2; x++)
			{
				if( y == 0 && x == 0) {  continue; }	
				
				if ( checkSingleBox(line + y, column + x, aChip, aGrid) && 
					 checkSingleBox(line - y, column - x, aChip, aGrid) ) 
				{	
					if( checkSingleBox(line + y + y, column + x + x, aChip, aGrid) )
					{	
						int[] pos1 = {line, column};
						int[] pos2 = {line + y, column + x};
						int[] pos3 = {line - y, column - x};
						int[] pos4 = {line + y + y, column + x + x};
						positions.add(pos1);
						positions.add(pos2);
						positions.add(pos3);
						positions.add(pos4);
						return positions;
					}
					else if( checkSingleBox(line - y - y, column - x - x, aChip, aGrid) )
					{
						int[] pos1 = {line, column};
						int[] pos2 = {line + y, column + x};
						int[] pos3 = {line - y, column - x};
						int[] pos4 = {line - y - y, column - x - x};
						positions.add(pos1);
						positions.add(pos2);
						positions.add(pos3);
						positions.add(pos4);
						return positions;
					}
				}
				if( checkSingleBox(line + y, column + x, aChip, aGrid) )  
				{
					if( checkSingleBox(line + y + y, column + x + x, aChip, aGrid) )
					{
						if( checkSingleBox(line + y + y + y, column + x + x + x, aChip, aGrid) )
						{
							int[] pos1 = {line, column};
							int[] pos2 = {line + y, column + x};
							int[] pos3 = {line + y + y, column + x + x};
							int[] pos4 = {line + y + y + y, column + x + x + x};
							positions.add(pos1);
							positions.add(pos2);
							positions.add(pos3);
							positions.add(pos4);
							return positions;
						}
					}
				}
			}
		} return positions;
	}
	
	/**
	 * Returns true if the method finds a Chip object in the grid that has a value
	 * equal to the value of the specified Chip object.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Connect4Grid object.
	 * @return true if the method finds a Chip object in the grid that has a value 
	 * equal to the value of the specified Chip object
	 */
	private static boolean checkSingleBox(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		if( y < 0 || y >= aGrid.getHeight() || x >= aGrid.getWidth() || x < 0 )
		{
			return false;
		}
		else
		{
			Chip chipAt = (Chip) aGrid.getValueAt(y, x);
			if( chipAt.getValue().equals(aChip.getValue()) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	

	
}
