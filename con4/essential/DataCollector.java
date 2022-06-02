package con4.essential;
import con4.saves.Exportable;
/**
 * This class takes care of fetching data from the Connect4 class 
 * to allow you to restore the session later using a save file.
 * @author Simone Gentili
 *
 */
public class DataCollector implements Exportable
{
	//The string in line one of the data.
	public static final String SIGNATURE_FILE = "force4Saves";
	
	private Connect4 connect4; //the game.
	private String data; //contains the game data.
	
	/**
	 * Builds an object for takes data from the game.
	 * Inizializes the data with the signature file.
	 * @param obj A Connect4 object.
	 */
	public DataCollector(Connect4 obj)
	{
		connect4 = obj;
		data = SIGNATURE_FILE;
	}
		
	/**
	 * It takes data from the Force4 class by placing it 
	 * inside the data variable of this object.
	 * The data is arranged using a convention in accordance
	 * with the DataSetter class.
	 * @return The String object represents the data of the game.
	 */
	public String getStatus()
	{		
		data += "\n" + getStatusGrid() + getStatusPlayers()
			   +  getStatusTurn() + getStatusMovements();
		if( connect4.ai.getCurrentDifficulty().equals( null ) )
		{
			return data;
		}
		else 
		{ 
			return data += getStatusAi();
		}
	}
	
	/**
	 * Returns the grid data.
	 * @return A String object.
	 */
	public String getStatusGrid()
	{
		String gridStatus = "";
		int gridHeight = connect4.grid.getHeight();
		int gridWidth = connect4.grid.getWidth();
		gridStatus += gridHeight + "\n" + gridWidth + "\n" + connect4.grid.toString();
		return  gridStatus;
	}
	
	/**
	 * Returns the players data.
	 * @return A String object.
	 */
	public String getStatusPlayers()
	{
		String statusPlayers = "";
		String playerName1 = connect4.players[0].getName();
		String playerName2 = connect4.players[1].getName();
		String playerTurnName = connect4.shiftManager.getPlayerTurn().getName();
		statusPlayers += playerName1 + "\n" + playerName2 + "\n" + playerTurnName + "\n";
		return statusPlayers;	
	}
	
	/**
	 * Returns the turns data.
	 * @return A String object.
	 */
	public String getStatusTurn()
	{
		String statusTurn = "";
		int numberShifts = connect4.counter.getCurrentValue();
		statusTurn += numberShifts + "\n";
		return statusTurn;
		
	}
	
	/**
	 * Returns the moves data.
	 * @return A String object.
	 */ 
	public String getStatusMovements()
	{
		//sets the first player movements.
		String movementsPlayer1 = "";
		int size = connect4.getMovementsOf(connect4.players[0]).size();
		for(int i = 0; i < size; i++)
		{
			int[] pos = connect4.getMovementsOf(connect4.players[0]).get(i);
			movementsPlayer1 += pos[0];
			movementsPlayer1 += pos[1];
		}
		
		//sets the second player movements.
		String movementsPlayer2 = "";
		size = connect4.getMovementsOf(connect4.players[1]).size();	
		for(int i = 0; i < size; i++)
		{
			int[] pos = connect4.getMovementsOf(connect4.players[1]).get(i);
			movementsPlayer2 += pos[0]; movementsPlayer2 += pos[1];
		}
		
		return movementsPlayer1 + "\n" + movementsPlayer2 + "\n";
	}
	
	/**
	 * Returns the ai data (difficulty).
	 * @return A String object.
	 */
	public String getStatusAi()
	{
		return "" + connect4.ai.getCurrentDifficulty();
	}
}
