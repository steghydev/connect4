package con4.saves;

/**
 * This class takes care of keeping track of the path 
 * of an exported or imported save file.
 * @author Simone Gentili
 */
public class SaveManager 
{
	//If there is no save path.
	public static final String MISSING_FILE = "";
	
	//Contains the path of the save file.
	private static String filePath = "";
	
	/**
	 * It sets the last path of the save file with the 
	 * specified path.
	 * @param aPath The path of the new file.
	 */
	public static void setLastSave(String aPath)
	{
		filePath = aPath;
	}
	
	/**
	 * Deletes the path of the last saved save.
	 */
	public static void resetLastSave()
	{
		filePath = MISSING_FILE;
	}
	
	/**
	 * Returns the path of the last saves save.
	 * @return The file path.
	 */
	public static String getFilePath()
	{
		return filePath;
	}

	/**
	 * Checks and returns true if there is a save 
	 * path set.
	 * @return true if there is a save path set, false
	 * 			otherwise.
	 */
	public static boolean checkFilePath()
	{
		return !filePath.equals(MISSING_FILE);
	}
}
