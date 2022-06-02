package con4.consoleGame;
import java.io.FileNotFoundException;

import con4.consoleUtil.*;
import con4.essential.*;
import con4.saves.*;

/**
 * This class manages the invocations of the methods 
 * that allow the user to move within the force 4 game (Combining 
 * the inOutController class with the Force4 class mainly).
 * There are methods to set up the game against an artificial 
 * intelligence (in different difficulty) and to set the 
 * two-player mode. The printing and reception of the data 
 * entered by the user occurs by invoking the static methods 
 * of the inOutController class.
 * The TimedPrinter class is instead used to print messages 
 * when the user does not need to enter data.
 * Method invocations start from the only public method of 
 * this class  ( i.e. execution () ).
 * Have fun playing it!
 * @author Simone Gentili
 *
 */
public class Connect4ConsoleManager 
{
	//Variables to inform the mode in use
	private static final String SINGLE_MODE = "S_MODE";
	private static final String TWO_MODE = "T_MODE";
	private static final String RESUME_MODE = "R_MODE";
	
	//Manages the colors of the console.
	public  static ColorfulConsole colors = new ColorfulConsole();
	
	private static Connect4 connect4 = new Connect4(); //the game.
	
	private static String mode = ""; //contains the mode in use.
	
	/**
	 * Contains the main menu.
	 * @throws InterruptedException
	 */
	public static void execution() throws InterruptedException 
	{
		//To activate the colors you have to insert the --load 
		//option when launching the command.
		if(colors.isActiveColorConsole()) { colors.printColor(); }
		while(true) 
		{
			TimedPrinter.printlnMessage(TextContainer.TITLE);
	
			// check for save file.
			int choiseStart = checkSaveGame();	
			
			int choise = InOutController.getIntAnswer(choiseStart, 3, TextContainer.MENU, TextContainer.errorMessage);
			
			if( choise == 0) 
			{
				//I set up the game
				restoreGame();
				mode = RESUME_MODE;
				playGame();
			}
			
			else if( choise == 1) 
			{
				newGame(); 
			}
			
			else if( choise == 2 )
			{
				options();
			}
			
			else { break; }	
		} 
		System.exit(1);
	}
	
	/**
	 * Contains the menu to play.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	private static void newGame() throws InterruptedException 
	{
		while(true)
		{	
			int choise = InOutController.getIntAnswer(1, 3, TextContainer.NEW_GAME, TextContainer.errorMessage);
			
			if( choise == 1)
			{
				setSinglePlayer();
				mode = SINGLE_MODE;
				playGame();
			}
			
			else if( choise == 2)
			{
				setTwoPlayers();
				mode = TWO_MODE;
				playGame();
			}
			
			else { break; }	
		}
	}
	/**
	 * It sets the single-player mode.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	private static void setSinglePlayer() throws InterruptedException
	{
		int choise = InOutController.getIntAnswer(1, 3, TextContainer.aiDifficulty, TextContainer.errorMessage);
		
		//setting the ai difficulty
		if( choise == 0)      { connect4.ai.setDifficulty(Ai.KEY_EASY);}
		else if( choise == 1) { connect4.ai.setDifficulty(Ai.KEY_MEDIUM);}
		else                  { connect4.ai.setDifficulty(Ai.KEY_HARD);}
		
		String playerName1;
		while(true)
		{
			playerName1 = InOutController.getStringAnswer(TextContainer.askForTheFirstName);
			
			//not accepted.
			if( playerName1.equals(Ai.AI_NAME) ) { TimedPrinter.printlnMessage(TextContainer.errorAiNames, 0);}
			else { break; }
		}
		connect4.players[0].setName(playerName1);
		connect4.players[1].setName(Ai.AI_NAME);
		connect4.resetGame();
	}
	
	/**
	 * It sets the two-players mode.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	private static void setTwoPlayers() throws InterruptedException
	{		
		String playerName1 = InOutController.getStringAnswer(TextContainer.askForTheFirstName);
		String playerName2 = "";
		while(true)
		{
			playerName2 = InOutController.getStringAnswer(TextContainer.askForTheSecondName);
			
			//not accepted
			if(playerName1.equals(playerName2)) { TimedPrinter.printlnMessage(TextContainer.errorNames, 0);}
			else { break; }
		}
		connect4.players[0].setName(playerName1);
		connect4.players[1].setName(playerName2);
		connect4.resetGame();
	}
	
	/**
	 * Manages a single game.
	 * @throws InterruptedException  if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
 	private static void playGame() throws InterruptedException
	{
 		/**
 		 * It is used to understand whether to ask the user to save the game.
 		 * For example, if he has not made any move then he can't save the game.
 		 */
 		int counter = 0;
 		
		while(true)
		{
			//I take the values ​​of the current player.
			String playerTurnName = connect4.shiftManager.getPlayerTurn().getName();	
			String playerTurnChip = connect4.getChipPlayerTurn().getValue();	

			//it is not necessary if the current player is the AI.
			if( !playerTurnName.equals(Ai.AI_NAME) )
			{
				TimedPrinter.printlnMessage(connect4.grid.toString(), 0);
				TimedPrinter.printlnMessage(playerTurnName + " (" + playerTurnChip + ")" + " | Round: " + connect4.counter.getCurrentValue(), 0);
				TimedPrinter.printlnMessage(connect4.grid.getFreeColumn().toString(), 0);
			}
			
			int choise;	
			
			//asks ai to move
			if(playerTurnName.equals(Ai.AI_NAME))
			{	
				choise = AiMover.move(connect4.ai.getCurrentDifficulty(), connect4, connect4.ai);		
			}
			
			//asks the human being to move
			else
			{ 
				choise = InOutController.getChoiseValueIn(connect4.grid.getFreeColumn(), TextContainer.askForAColumn, TextContainer.errorMessage);
			}
		
			//When the choice is a negative value it means 
			//that the user intends to exit.
			if( choise < 0 && counter > 0 ) //with the ability to save.
			{
				int saveChoise = InOutController.getIntAnswer(0, 1, TextContainer.askForTheSave, TextContainer.errorMessage);
				
				if( saveChoise == 1 ) { makeSaveGame(); } 
				break;
			}
			
			else if( choise < 0 && counter == 0 ) { break; } //without the ability to save.

			//Inserts the Chip whitin the grid..
			int result = Connect4Inserter.insertChipAt(choise, connect4); 
			
			//the current player has won
			if(result == 1)
			{
				TimedPrinter.printlnMessage(connect4.grid.toString(), 0);
				TimedPrinter.printlnMessage(TextContainer.theWinnerIs + playerTurnName, 0);
				
				//If the game you just finished came from a save.
				if( mode.equals(RESUME_MODE) ) { SaveManager.resetLastSave(); }
				
				int restartChoise = InOutController.getIntAnswer(0, 1, TextContainer.askForNewGame, TextContainer.errorMessage);
				if( restartChoise == 1)
				{
					//Another game.
					connect4.resetGame();
				}
				else { break; }
			}
			
			//the grid is full
			else if(result == 0)
			{
				//So you can see the full grid.
				if(playerTurnName.equals(Ai.AI_NAME))
				{
					TimedPrinter.printlnMessage(connect4.grid.toString(), 5);
				}
				TimedPrinter.printlnMessage(TextContainer.parity, 0);
				
				//If the game just ended was a save
				if( mode.equals(RESUME_MODE) ) { SaveManager.resetLastSave(); }
				
				int restartChoise = InOutController.getIntAnswer(0, 1, TextContainer.askForNewGame, TextContainer.errorMessage);
				if( restartChoise == 1)
				{
					connect4.resetGame();
				}
				else { break; }	
			}
			//Go to the next round!
			else if(result == -1)
			{
				connect4.shiftManager.nextRound();
				counter ++;
			}
		}
	}
 	 
 	/**
 	 * Just the options menu.
 	 * If colors are enabled it also prints the color menu.
 	 * However, the grid and reset menus are always printed.
 	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
 	 */
	private static void options() throws InterruptedException
	{
		while(true)
		{
			if(colors.isActiveColorConsole())
			{
				int choise = InOutController.getIntAnswer(1, 4, TextContainer.OPTIONS_WITH_COLOR, TextContainer.errorMessage);
				
				if( choise == 1)
				{
					gridOptions();
				}
				else if( choise == 2)
				{
					colorOptions();
				}
				else if( choise== 3)
				{
					resetOptions();
				}
				else if( choise == 4 )
				{ 
					break; 
				}
			}
			else 
			{ 
				int choise = InOutController.getIntAnswer(1, 3, TextContainer.OPTIONS, TextContainer.errorMessage);
				
				if( choise == 1)
				{
					gridOptions();
				}
				else if( choise== 2)
				{
					resetOptions();
				}
				else if( choise == 3)
				{ 
					break; 
				}
			}
		}
	}
	
	/**
	 * It allows the user to change the colors of the interface.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown
	 */
	private static  void colorOptions() throws InterruptedException
	{
		colors.printPalette();
		while(true)
		{
			int bgColorChoise = InOutController.getIntAnswer(1, 8, TextContainer.askForBackgroundColor, TextContainer.errorMessage);
			int txColorChoise;
			while(true)
			{
				txColorChoise =  InOutController.getIntAnswer(1, 8, TextContainer.askForTextColor, TextContainer.errorMessage);
				
				if( bgColorChoise == txColorChoise) 
				{ 
					TimedPrinter.printlnMessage(TextContainer.errorValues, 0);
				}
				else 
				{ 
					break;
				}
			}
			
			colors.setTextColor      (colors.getColorAtNumber(txColorChoise));
			colors.setBackgroundColor(colors.getColorAtNumber(bgColorChoise));
			colors.printColor();
			
			break;
		}
	}
	
	/**
	 * Allows the user to change the size of the grid. (dimensions are quantized).
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown.
	 */
	private static void gridOptions() throws InterruptedException
	{
		TimedPrinter.printMenu(TextContainer.GRID_DIMENSIONS, 0);
		while(true)
		{
			int dimensionChoise = InOutController.getIntAnswer(1, 6, "", TextContainer.errorValues);
			
			if     ( dimensionChoise == 1){ connect4.grid.setDimensions(5 ,5); }
			else if( dimensionChoise == 2){ connect4.grid.setDimensions(6 ,6); }
			else if( dimensionChoise == 3){ connect4.grid.setDimensions(4 ,10);}
			else if( dimensionChoise == 4){	connect4.grid.setDimensions(9 ,6); }
			else if( dimensionChoise == 5){ connect4.grid.setDimensions(7 ,7); }
			else if( dimensionChoise == 6){ connect4.grid.setDimensions(8 ,10);}
			break;
		}
	}
	
	/**
	 * Sets the default values ​​for the settings.
	 * If the colors are enabled they return to the default values ​​
	 * (i.e. black for the background and white for the text).
	 * The dimensions of the grid go back to being 6 x 7.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown.
	 */
	private static void resetOptions() throws InterruptedException
	{
		if(colors.isActiveColorConsole())
		{
			colors.setTextColor      (colors.WHITE);
			colors.setBackgroundColor(colors.BLACK);
			colors.printColor();
		}
		connect4.grid.setDimensions(Connect4Grid.DEFAULT_GRID_HEIGHT, Connect4Grid.DEFAULT_GRID_WIDTH); 
	}

	/**
	 * Asks the user where to save the game, then performs the save.
	 * @throws InterruptedException  if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown.
	 */
	private static void makeSaveGame() throws InterruptedException
	{
		while(true)
		{
			String choise = InOutController.getStringAnswer(TextContainer.askForSavePath);
			try
			{
				Connect4Saves.exportStatus(choise, connect4);
				//Everything is alright.
				TimedPrinter.printlnMessage(TextContainer.saveDone, 0);
				break;
			}
			catch (FileNotFoundException e)
			{
				//The user entered an invalid path.
				TimedPrinter.printlnMessage(TextContainer.errorPath, 0);
			}
			catch (IllegalArgumentException i)
			{
				//the path is not a directory.
				TimedPrinter.printlnMessage(TextContainer.errorFolder, 0);
			}
		}
	}
		
	/**
	 * Check for a save file.
	 * @return 0 if exists, otherwise 1.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown.
	 */
	private static int checkSaveGame() throws InterruptedException
	{
		int value = 1;
		if(SaveManager.checkFilePath())
		{
			if(Connect4Saves.exists(SaveManager.getFilePath()))
			{
				//it exists.
				TimedPrinter.printlnMessage(TextContainer.RESUME, 0);
				value = 0;
			}
			else
			{
				//File does not exist
				SaveManager.resetLastSave();
				TimedPrinter.printlnMessage(TextContainer.errorPath);
			}
		}
		return value;
	}
	
	/**
	 * It restores a game session.
	 * If the save is corrupt, it restores settings by invoking the 
	 * abortOperation() method.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown.
	 */
	private static void restoreGame() throws InterruptedException 
	{
		try 
		{
			Connect4Saves.importStatus(SaveManager.getFilePath(), connect4);
		}
		catch (FileNotFoundException e) //If the file does not exist
		{
			SaveManager.resetLastSave();
			TimedPrinter.printlnMessage(TextContainer.errorPath, 0);
		}
		//if the file is corrupt.
		catch(java.lang.StringIndexOutOfBoundsException e) { abortOperation(); }
		catch(java.lang.NumberFormatException e)           { abortOperation(); }
		catch(IllegalArgumentException e)                  { abortOperation(); }
	}
	
	/**
	 * It resets the grid size and then deletes the path of the last loaded save.
	 * @throws InterruptedException if any thread has interrupted 
	 * 			the current thread. The interrupted status of the current 
	 * 				thread iscleared when this exception is thrown.
	 */
	private static void abortOperation() throws InterruptedException 
	{
		TimedPrinter.printlnMessage(TextContainer.errorSave, 0);
		connect4.grid.setDimensions(Connect4Grid.DEFAULT_GRID_HEIGHT, Connect4Grid.DEFAULT_GRID_WIDTH); 
		SaveManager.resetLastSave();
		execution();
	}
	
}

 