package con4.tools;
/**
 * This class represents grids for containing any type of data.
 * @author Simone Gentili
 *
 */
public class Grid 
{
	//the fantastic grid.
	private Object[][] grid;

	/**
	 * Constructs an object that contains a grid of the specified size.
	 * @param y The number of grid lines.
	 * @param x The number of grid columns.
	 */
	public Grid(int y, int x)
	{
		grid = new Chip[y][x];
	}
	
	/**
	 * sets the specified object within the grid to the specified position.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param value The object to insert.
	 * @throws IndexOutOfBoundsException If y > this.getHeight() || y < 0
	 * 			|| x > this.getWidth() || x < 0. 
	 */
	public void setValueAt(int y, int x, Object value) throws IndexOutOfBoundsException
	{
		grid[y][x] = value;
	}

	/**
	 * Returns the instance of the Object class at the specified location.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @return An instance of Object.
	 * @throws IndexOutOfBoundsException If y > this.getHeight() || y < 0
	 * 			|| x > this.getWidth() || x < 0. 
	 */
	public Object getValueAt(int y, int x) throws IndexOutOfBoundsException
	{
		return grid[y][x];
	}
	
	/**
	 * Sets the grid dimensions of this object. Sets the size (length and width) 
	 * of this object's grid. After that the grid will be "empty" ( each value 
	 * contained in the grid is equal to null ).
	 * @param y The new height.
	 * @param x The new width.
	 */
	public void setDimensions(int y, int x)
	{
		grid = new Object[y][x];
	}
	
	/**
	 * Modifies the grid of this object so that each value contained 
	 * in it is equal to null.
	 */
	public void clearGrid()
	{
		int height = getHeight();
		int width = getWidth();
		grid = new Object[height][width];
	}
	
	/**
	 * Returns the grid width of this object.
	 * @return A non-negative integer.
	 */
	public int getWidth()
	{
		return grid[0].length;
	}

	/**
	 * Returns the grid height of this object.
	 * @return A non-negative integer.
	 */
	public int getHeight()
	{
		return grid.length;
	}

	/**
	 * returns the String object that represents the grid of this object.
     * The representation is of the type: Grid[ height,  width ].
     * @return A String object.
	 */
	public String toString()
	{
		return "Grid[" + "h:" + getHeight() + "," + "w:" + getWidth() + "]";
	}
	


	
}
