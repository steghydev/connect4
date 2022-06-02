package con4.essential;
import java.util.Set;
import java.util.TreeSet;

import con4.tools.Chip;
import con4.tools.Grid;

/**
 * This class extends the Grid class. Overrides the setValue()
 * and setDimensions () methods to match the Connect 4 game.
 * A Connect4Grid object has the ability to know the status of 
 * the grid columns at any time using the getFreeColumn() method.
 * @author Simone Gentili
 */
public class Connect4Grid extends Grid 
{
	//Default dimension values.
	public static final int DEFAULT_GRID_WIDTH = 7;
	public static final int DEFAULT_GRID_HEIGHT = 6;
	
	//it contains the free columns.
	private Set<Integer> freeColumns = new TreeSet<>();

	/**
	 * Constructs a Force4Grid object by setting the 
	 * grid first ( see the setGrid() method ) and then 
	 * the free column ( see setFreeColumn() method ).
	 */
	public Connect4Grid()
	{
		super(DEFAULT_GRID_HEIGHT, DEFAULT_GRID_WIDTH);
		setGrid();
		setFreeColumns();
	}
		
	/**
	 * It changes the size of the grid with the specified 
	 * values. After the change has been made the free 
	 * columns are updated.
	 * @param y The height.
	 * @param x The width.
	 */
	public void setDimensions(int y, int x)
	{
		super.setDimensions(y, x);
		setGrid();
		clearFreeColumns();
		setFreeColumns();
	}
	
	/**
	 * Sets the specified Chip object in the grid to the 
	 * specified coordinates.
	 * If the object passed as an argument is not an 
	 * instance of the Chip class, the method throws an 
	 * IllegalArgumentException.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 */
	public void setValueAt(int y, int x, Object aChip)
	{
		if(aChip instanceof Chip)
		{
			Chip chip = (Chip) aChip;
			super.setValueAt(y, x, chip);
		}
		else
		{
			throw new IllegalArgumentException("the specified object "
					+ "must be of type Chip");
		}
	}
	
	/**
	 * Set this object for a new game, set up the grid 
	 * and update the available columns.
	 */
	public void resetGrid()
	{
		setGrid();
		clearFreeColumns();
		setFreeColumns();
	}
	
	/**
	 * Updates the available grid columns of this object.
	 */
	public void setCurrentFreeColumns()
	{
		clearFreeColumns();
		setFreeColumns();
	}
	
	/**
	 * Fill the grid of this object with Chip objects with
	 * default value. The default value of Chip objects is
	 * " " ( see the Chip class for more information ).
	 */
	private void setGrid()
	{
		int w = getWidth();
		int h = getHeight();
		
		for(int i = 0; i < h; i++ )
		{
			for(int j = 0; j < w;  j++)
			{
				setValueAt(i, j, new Chip(Chip.EMPTY));
			}
		}
	}
	
	/**
	 * iterates the grid of this object along the entire 
	 * first line. When it finds a column with a Chip having 
	 * a default value (ie the value "") it inserts the index 
	 * of that column into the set of free columns.
	 */
	private void setFreeColumns()
	{		
		int w = getWidth();
		
		for(int x = 0; x < w; x++)
		{
			Chip chip = (Chip) getValueAt(0, x);
			if( chip.equals( new Chip(Chip.EMPTY)) )
			{
				freeColumns.add(x); 
			} 
		}
	}
	
	/**
	 * it empties the set of free columns of this object.
	 */
	private void clearFreeColumns()
	{
		freeColumns.clear();
	}
	
	/**
	 * Returns the set of free columns of this object.
	 * @return A Set<Integer>
	 */
	public Set<Integer> getFreeColumn()
	{
		return freeColumns;
	}
	
	/**
	 * Checks the set of free columns and returns true if
	 * empty, false otherwise.
	 * @return true if the set of free columns is empty,
	 * 			false otherwise.
	 */
	public boolean isFull()
	{
		if(freeColumns.size() > 0)
		{
			return false;
		}
		else return true;
	}

	/**
	 * Returns the string representation of this object's grid.
	 * @return A String object.
	 */
	public String toString()
	{		
		String gridString = "";
		
		int w = getWidth();
		int h = getHeight();	
		
		for(int i = 0; i <= w; i++)
		{
			gridString += "_";
			if(i != w) { gridString += "   "; }
		}
		
		gridString += "\n";	
		int lineCounter = 0;
		for(int y = 0; y < h; y++)
		{	
			for (int x = 0; x < w; x++)
			{
				String value = ( (Chip) getValueAt(y, x) ).getValue();
				gridString += "|";
				if(value.charAt(0) == '(')
				{
					gridString += value;
				}
				else 
				{
					gridString += " "+value+" ";
				}
			}
			lineCounter ++;
			if(lineCounter <= h)
			{	
				gridString += "|\n";
			}
		}
		gridString += "|";
		for(int x = 0; x < w; x++)
		{
			gridString += "---";
			if( x < w - 1)
			{ 
				gridString += "-";
			}
		}
		gridString += "|\n";
		return gridString;
	}
	

}
