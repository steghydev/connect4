package con4.saves;
import java.io.File;
/**
 * This class provides a method to generate a String object representing a
 * file path using a signature file.
 * @author Simone Gentili
 */
public class PathGenerator 
{	
	/**
	 * It generates a path using the specified path and the specified file signature.
	 * If the specified file is not a directory, the method throws an IllegalArgumentException.
	 * @param aPath A String object represents the directory.
	 * @param aFileSignature A String object represents the file signature to use.
	 * @return A String object represents the generated path.
	 */
	public static String generatePath(String aPath, String aFileSignature)
	{
		if(!isDirectory(aPath))
		{
			throw new IllegalArgumentException("The specified file is not valid");
		}
		String pathGenerated = aPath;
		String time = java.time.Clock.systemUTC().instant().toString().replaceAll(":", "-");
		if(OsController.isWindows())
		{
			pathGenerated += "\\";
		}
		else if(OsController.isLinux())
		{
			pathGenerated += "/";
		}
		pathGenerated += aFileSignature + time;

		return pathGenerated;
	}
	
	/**
	 * Checks if the specified path represents a dirctory and
	 * returns true if it is, false otherwise.
	 * @param aPath A String object.
	 * @return true if the specified path is a directory, false
	 * 			otherwise.
	 */
	private static boolean isDirectory(String aPath)
	{
		File file = new File(aPath);
		if(file.isDirectory())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	

}
