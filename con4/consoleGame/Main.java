package con4.consoleGame;
import java.util.ArrayList;

import con4.consoleUtil.*;
import con4.saves.SaveManager;

/**
 * This class contains the main method to run the Connect 4 console program.
 * The arguments that are entered on the command line are organized using 
 * the static ArgumentsSorter.getArguments (args []) method and used to set 
 * the program. 
 * The --color \ -c option enables the program colors in the console.
 * The --load \ -l [file] option loads a save file to restore a game.
 * @author Simone Gentili
 *
 */
public class Main 
{	
	//extended and contracted options
	private static final String COLOR_EXT_OPT = "--color";
	private static final String COLOR_OPT = "-c";
	private static final String LOAD_EXT_OPT = "--load";
	private static final String LOAD_OPT = "-l";
	private static final String HELP_EXT_OPT = "--help";
	private static final String HELP_OPT = "-h";
	
	public static void main(String[] args) throws Exception
	{	
		ArrayList<ArrayList<String>> optArg = ArgumentsSorter.getArguments(args);
		
		if(optArg.size() > 0)
		{
			int counter = 0;
			int argumentsSize = optArg.size();

			for(int i = 0; i < optArg.size(); i++)
			{
				for(int j = 0; j < optArg.get(i).size(); j++)
				{
					String value = optArg.get(i).get(j);
					if(value.equals(HELP_EXT_OPT) ||
							value.equals(HELP_OPT))
					{
						TimedPrinter.printMessage(TextContainer.usage);
						System.exit(1);
					}
					if(value.equals(COLOR_EXT_OPT) || 
							value.equals(COLOR_OPT))
					{
						counter ++;
						Connect4ConsoleManager.colors.enableColor();
					}
					else if(value.equals(LOAD_EXT_OPT) ||
							value.equals(LOAD_OPT))
					{
						counter ++;
						
						if(optArg.get(i).size() > 1)
						{
							//there is an argument;
							String savePath = optArg.get(i).get(j+1);
							SaveManager.setLastSave(savePath);
						}
						else
						{
							TimedPrinter.printlnMessage(TextContainer.usage); 
							System.exit(1);
						}
					}
				}
			}
			if( !(counter == argumentsSize) ) 
			{ 
				TimedPrinter.printlnMessage(TextContainer.usage); 
				System.exit(1);
			}
		}
		Connect4ConsoleManager.execution();
	}
}
