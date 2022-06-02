package con4.consoleGame;

/**
 * This class contains string objects and String arrays containing the sentences 
 * to be displayed in the Connect4 terminal program (See Connect4ConsoleManager). 
 * @author Simone Gentili
 *
 */
public class TextContainer 
{
	public static String TITLE = "C0NN3CT 4";
	
	public static String RESUME =  "-------0) RESUME";
	public static String[] MENU =    {"---------1) NEW GAME",
									        "----------2) OPTIONS",
				                            "-----------3) EXIT"};
	public static String[] NEW_GAME = {"-------1)SINGLE PLAYER", 
				                             "---------2) TWO PLAYERS", 
				                             "----------3) BACK"};	
	
	public static String[] OPTIONS_WITH_COLOR = {"--------1) GRID SETTINGS",
									                   "---------2) COLOR SETTINGS",
									                   "----------3) RESET SETTINGS",
	                                                   "-----------4) BACK"};
	public static String[] OPTIONS = {"--------1) GRID SETTINGS",
			                                "----------2) RESET SETTINGS",
                                            "-----------3) BACK"};
	public static String[] GRID_DIMENSIONS = {"(1)---> 5 X 5   | (4)---> 9 X 6",
		                                            "(2)---> 6 X 6   | (5)---> 7 X 7",  		 
		                                            "(3)---> 4 X 10  | (6)---> 8 X 10"};
			
	public static String usage = "Usage: java Main [OPTION] [ARGUMENT]\n"
										+ "OPTIONS: --load or -l { filePath.txt} \n"
										+ "         --color or -c (to enable the colors)";
	
	public static String aiDifficulty = "(1)->Easy | (2)->Medium | (3)->Hard";
	
	public static String askForTheFirstName = "Enter the name of the first player: ";
	
	public static String askForTheSecondName = "Enter the name of the second player: ";
	
	public static String askForTheSave = "Do you want to save the game? (0)-> No | (1)-> Si ";
		
	public static String askForAColumn = "Enter a column number, Q to quit: ";
	
	public static String askForNewGame = "Do you want start a new game? (0)-> No | (1)-> Si ";
	
	public static String parity = "Parity";
	
	public static String askForBackgroundColor = "Choise a background color: ";
	
	public static String askForTextColor = "Choise a text color: ";
	
	public static String askForSavePath = "Enter a path: ";
	
	public static String theWinnerIs = "The winner is: ";
	
	public static String saveDone = "Save done";
	
	//Some error messages
	
	public static String errorSave = "The save file is corrupt.";
	
	public static String errorNames = "The names cannot be the same.";
	
	public static String errorFolder = "The entered path must be a folder.";
	
	public static String errorPath = "The entered path does not exist.";
	
	public static String errorValues = "The values cannot be the same.";
	
	public static String errorAiNames = "The chosen name cannot be used.";

	public static String errorMessage = "The inset value is not valid.";
	
}
