package con4.essential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import con4.saves.PathGenerator;
import con4.saves.SaveManager;
import con4.saves.SessionExporter;
import con4.saves.SessionImporter;

/**
 * This class menages the import and export of save files for
 * the Connect4 class.
 * @author Simone Gentili
 */
public class Connect4Saves 
{
	public static final String ESTENSION = ".txt";
	
	/**
	 * This method exports a save file to the specified path.
	 * @param aPath A String object that represents the save path.
	 * @param obj A Connect4 object.
	 * @throws FileNotFoundException
	 */
	public static void exportStatus(String aPath, Connect4 obj) throws FileNotFoundException
	{
		String path = PathGenerator.generatePath(aPath, DataCollector.SIGNATURE_FILE) + ESTENSION;
		SessionExporter.exportSession( new DataCollector(obj), path);
		SaveManager.setLastSave(path);
	}
	
	/**
	 * Import the file specified by the path into the game.
	 * if the file does not contain the ffsave string in the first line,
	 * the method throws an IllegalArgumentException.
	 * @param aPath The save path.
	 * @param obj A Connect4 object.
	 * @throws FileNotFoundException if and only if the specified path
	 * 			doesn't exist.
	 */
	public static void importStatus(String aPath, Connect4 obj) throws FileNotFoundException
	{
		if(CheckFileSignature(aPath)) 
		{
			SessionImporter.importSession( new DataSetter(obj), aPath);
			SaveManager.setLastSave(aPath);
		}
		else
		{
			throw new IllegalArgumentException("The file does not contain the signature.");
		}
	}
	
	/**
	 * checks that the file specified by the path contains the signature.
	 * @param aPath A String object representing a save file.
	 * @return true if the file contains the signature, false otherwise.
	 * @throws FileNotFoundException if the file specified by the path
	 * 			doesn't exist
	 */
	private static boolean CheckFileSignature(String aPath) throws FileNotFoundException
	{
		
		Scanner input = new Scanner(new File(aPath));
		
		if(input.hasNextLine())
		{
			String signature = input.nextLine();
			
			if(signature.equals(DataCollector.SIGNATURE_FILE))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			throw new IllegalArgumentException("The file is empty");
		}
	}
	
	/**
	 * Checks the file by the specified path e returns true if it exists,
	 * false otherwise.
	 * @param aPath A String object representing a file.
	 * @return true if the file exists, false otherwise.
	 */
	public static boolean exists(String aPath)
	{
		File file = new File(aPath);
		return file.exists();
	}

}
