package con4.essential;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import con4.tools.Chip;
import con4.tools.Player;
import con4.tools.ShiftsCounter;
import con4.tools.ShiftsManager;
/**
 * This class represents the making of the Connect 4 game.
 * A Force4 class item contains a turn counter, match players,
 * turn player manager, grid, and chips.
 * The class also contains two maps that contain Player-Chip
 * and Player-Movements associations respectively.
 * @author Simone Gentili
 */
public class Connect4
{		
	//Some defaults values.
	public static final String NAME_PLAYER1 = "Player1";
	public static final String NAME_PLAYER2 = "Player2";
	public static final String VALUE_CHIP1 = "X";
	public static final String VALUE_CHIP2 = "O";

	//The elements necessary for the realization of the game.
	public ShiftsManager shiftManager;
	public Ai ai;
	public ShiftsCounter counter;
	
	public Player[] players = new Player[2];
	public Chip[] chips = new Chip[2];
	public Connect4Grid grid;
	

	
	//Contains the association Player-Chip.
	public Map<Player, Chip> mapPlayerChip = new HashMap<>();
	
	//Contains the association Player-Movements.
	public Map<Player, Stack<int[]>> mapPlayerMovements = new HashMap<>();

	/**
	 * Builds a Force4 object with deafults values and sets the maps.
	 */
	public Connect4()
	{
		players[0] = new Player(NAME_PLAYER1);
		players[1] = new Player(NAME_PLAYER2);
		
		chips[0] = new Chip(VALUE_CHIP1);
		chips[1] = new Chip(VALUE_CHIP2);
		
		shiftManager = new ShiftsManager(players);
		
		grid = new Connect4Grid();
		
		ai = new Ai();
		
		counter = new ShiftsCounter();
		
		setMapPC(); setMapPM();
	}
	
	/**
	 * Sets the Player-Chip map of this object.
	 */
	private void setMapPC()
	{
		mapPlayerChip.put(players[0], chips[0]);
		mapPlayerChip.put(players[1], chips[1]);
	}
	
	/**
	 * Sets the Player-Movements map of this object.
	 */
	private void setMapPM()
	{
		mapPlayerMovements.put(players[0], new Stack<>());
		mapPlayerMovements.put(players[1], new Stack<>());
	}
	
	/**
	 * Clear player moves from the map.
	 */
	public void resetMovements()
	{
		mapPlayerMovements.get(players[0]).clear();
		mapPlayerMovements.get(players[1]).clear();
	}

	/**
	 * Adds the specified values ​​into an array of length 2 
	 * and adds that array to the stack associated with the 
	 * specified player. If the specified player is not a key 
	 * within the PM map, the method throws an IllegalArgumentsException.
	 * @param player A player object.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 */
	public void addMovement(Player player, int y, int x)
	{
		if(!mapPlayerMovements.containsKey(player))
		{
			throw new IllegalArgumentException("The specified Player object is not valid");
		}
		mapPlayerMovements.get(player).add(new int[2]);
		mapPlayerMovements.get(player).lastElement()[0] = y;
		mapPlayerMovements.get(player).lastElement()[1] = x;		
	}

	/**
	 * It returns the Chip object associated with the specified 
	 * Player object. If the specified Player object is not valid,
	 * the method throws an IllegalArgumentException.
	 * @param aPlayer A Player object.
	 * @return A Chip object.
	 */
	public Chip getChipPlayer(Player aPlayer)
	{
		if(!mapPlayerChip.containsKey(aPlayer))
		{
			throw new IllegalArgumentException("The specified Player "
									+ "is not valid");
		}
		return mapPlayerChip.get(aPlayer);
	}

	/**
	 * Returns the specified Chip object associated with the 
	 * Player object that is currently playing.
	 * @return A Chip object.
	 */
	public Chip getChipPlayerTurn()
	{
		return mapPlayerChip.get(shiftManager.getPlayerTurn());
	}
				
	/**
	 * Returns the stack (which contains the positions of the 
	 * previous moves) associated with the specified player.
	 * @param aPlayer A Player object
	 * @return A Stack<int[]>
	 */
	public Stack<int[]> getMovementsOf(Player aPlayer)
	{
		return mapPlayerMovements.get(aPlayer);
	}
			
	/**
	 * Resets all elements of this object.
	 * It must be used to set up a new game.
	 */
	public void resetGame()
	{
		shiftManager.resetShifts();
		grid.resetGrid();
		resetMovements();
		shiftManager.setFirstRound();
		counter.resetShiftsCounter();
	}
}
;
