package con4.saves;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class defines a method to take data within a file and 
 * use it to set the instance of a class that implements the 
 * Importable interface.
 * @author Simone Gentili
 */
public class SessionImporter
{
	/**
	 * Reads data into the localized file from the specified 
	 * path and sets the specified object by invoking the 
	 * setStatus () method defined in that object's class.
	 * @param var An object whose class implements the Importable interface.
	 * @param aPath The file.
	 * @throws FileNotFoundException if the file doesn't exists.
	 */
	public static void importSession(Importable var, String aPath) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(aPath));
		var.setStatus(in);
	}
}