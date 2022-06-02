
package con4.essential;
import java.util.Scanner;

import con4.saves.Importable;
import con4.tools.Chip;

/**
 * This class sets the Connect4 class using the Scanner object
 * which is passed to the setStatus(Scanner in) method.
 * @author Simone Gentili
 */
public class DataSetter implements Importable
{
	Connect4 connect4;
	
	/**
	 * Builds an object for setting the data within the 
	 * Connect4 class.
	 * @param obj
	 */
	public DataSetter(Connect4 obj)
	{
		connect4 = obj;
	}
	
	/**
	 * Sets the data contained within the specified Scanner 
	 * object within the Connect4 class.
	 * The data is set according to the Data Collector class.
	 */
	public void setStatus(Scanner input) 
	{
		input.nextLine();
		setGridBy       (input);
		setPlayersBy    (input);
		setShiftsCounter(input);
		setMapPMby      (input);
		if(input.hasNext())
		{
			setAiBy     (input);
		}
	}

	/**
	 * Sets the player-movements map of the Connect4 class 
	 * using the specified Scanner object.
	 * @param input A Scanner object.
	 */
	public void setMapPMby(Scanner input)
	{		
		//the positions
		String posPlayer1 = input.nextLine();
		String posPlayer2 = input.nextLine();

		//if true, it means that the passed file has been corrupted.
		if( (posPlayer1.length()%2) != 0 
				|| (posPlayer2.length()%2) != 0 )
		{
			throw new IllegalArgumentException("Invalid player "
					                 + "position values ​​in save");
		}
		connect4.resetMovements();
		
		for(int i = 0; i < posPlayer1.length(); i+=2)
		{
			String posAt = posPlayer1.substring(i, i+2);
			
			int y = Integer.parseInt(posAt.substring(0, 1));
			int x = Integer.parseInt(posAt.substring(1));
			
			int[] pos = {y, x};
			connect4.addMovement(connect4.players[0], pos[0], pos[1]);
		}
		for(int i = 0; i < posPlayer2.length(); i+=2)
		{
			String posAt = posPlayer2.substring(i, i+2);
			
			int y = Integer.parseInt(posAt.substring(0, 1));
			int x = Integer.parseInt(posAt.substring(1));
			
			int[] pos = {y, x};
			connect4.addMovement(connect4.players[1], pos[0], pos[1]);
		}
	}
	
	/**
	 * Sets the Grid object of the Connect4 class using the data
	 * contained within the specified Scanner Object.
	 * @param input A Scanner Object.
	 */
	public void setGridBy(Scanner input)
	{
		int gridHeight = Integer.parseInt(input.nextLine());
		int gridWidth =  Integer.parseInt(input.nextLine());

		connect4.grid.setDimensions(gridHeight, gridWidth);
		
		input.nextLine();
		
		for(int y = 0; y < gridHeight; y++)  
		{
			int increaser = 0;
			String line = input.nextLine().substring(2, gridWidth*4);
			for(int x = 0; x < gridWidth; x++)
			{
				String value = line.substring( increaser, increaser + 1);
				
				//if true, it means that the passed file has been corrupted
				if(        !value.equals(Connect4.VALUE_CHIP1) 
						&& !value.equals(Connect4.VALUE_CHIP2)
						&& !value.equals(Chip.EMPTY))
				{
					throw new IllegalArgumentException("I don't recognize the "
							+ "values ​​of the save chips");
				}
				increaser += 4;
				connect4.grid.setValueAt(y, x, new Chip(value));
			}
		}
		connect4.grid.setCurrentFreeColumns();
		
		input.nextLine();
	}
	
	/**
	 * Sets the Ai difficulty using the specified Scanner object.
	 * @param input A Scanner object.
	 */
	public void setAiBy(Scanner input)
	{
		String difficulty = input.nextLine();
		int keyDifficulty;
		if(difficulty.equals(Ai.EASY_DIFF))        { keyDifficulty = Ai.KEY_EASY;   }
		else if(difficulty.equals(Ai.MEDIUM_DIFF)) { keyDifficulty = Ai.KEY_MEDIUM; }
		else if(difficulty.equals(Ai.HARD_DIFF))   { keyDifficulty = Ai.KEY_HARD;   }
		
		//it means that the passed file has been corrupted
		else { throw new IllegalArgumentException("Can't find the specified"
				                                + " difficulty");}
		connect4.ai.setDifficulty(keyDifficulty);
	}
	
	/**
	 * Sets the players of the Connect4 class using the data within the specified
	 * Scanner object.
	 * @param input A Scanner object.
	 */
	public void setPlayersBy(Scanner input)
	{
		String playerName1 = input.nextLine();
		String playerName2 = input.nextLine();
		
		//if true, it means that the passed file has been corrupted.
		if(playerName1.equals(playerName2)) 
		{
			throw new IllegalArgumentException("the names cannot be the same.");
		}
		String playerTurnName = input.nextLine();
		
		//if true, it means that the passed file has been corrupted.
		if(!playerTurnName.equals(playerName1) && !playerTurnName.equals(playerName2))
		{
			throw new IllegalArgumentException("The player of the turn is"
												+ " not valid");
		}
					
		connect4.players[0].setName(playerName1);
		connect4.players[1].setName(playerName2);
		
		if(playerName1.equals(playerTurnName))
		{
			connect4.shiftManager.nextRoundBy(connect4.players[0]);
		}
		else 
		{ 
			connect4.shiftManager.nextRoundBy(connect4.players[1]); 
		}
	}
	
	/**
	 * Sets the shifts-counter of the Connect4 Class using the data
	 * contained within the specified Scanner object.
	 * @param input A Scanner object.
	 */
	public void setShiftsCounter(Scanner input)
	{
		int turnsNumber = Integer.parseInt(input.nextLine());	
		
		connect4.counter.setShiftsCounter(turnsNumber);	
	}
	
}
