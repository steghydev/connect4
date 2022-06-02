package con4.consoleUtil;
import java.util.ArrayList;

/**
 * This class contains a static method for organizing the arguments 
 * entered at the command prompt.
 * @author Simone Gentili
 *
 */
public class ArgumentsSorter 
{
	/**
	 * Arranges arguments within vectors so that in each vector there 
	 * is the option and its arguments. Vectors are added inside a vector 
	 * that is returned.
	 * @param args The array of arguments.
	 * @return	A vector (ArrayList) that contains vectors.
	 */
	public static ArrayList<ArrayList<String>> getArguments(String[] args) 
	{
		ArrayList<ArrayList<String>> optionArguments = new ArrayList<>();
		
		int length = args.length;
		
		for(int i = 0; i < length; i++)
		{
			String arg = args[i];
			if(arg.startsWith("--") ||
					arg.startsWith("-"))
			{
				//it's an option, search arguments
				ArrayList<String> arguments = new ArrayList<>();
				arguments.add(arg);
				
				for(int j = i+1; j < length; j++)
				{
					String newArg = args[j];

					if(!newArg.startsWith("--") ||
							!newArg.startsWith("-"))
					{
						//it's an argument
						arguments.add(newArg);
					}
					else 
					{
						//it's an option.
						break;
					}
				}
				//add the sub vector
				optionArguments.add(arguments);
			}
		}
		return optionArguments;
	}
}
