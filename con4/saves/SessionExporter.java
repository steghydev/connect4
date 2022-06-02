package con4.saves;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class allows classes that implement the Exportable interface 
 * to write the fetched data to a file.
 * @author Simone Gentili
 */
public class SessionExporter 
{
	/**
	 * Takes data from the specified object (invoking the getStatus method 
	 * defined within the specified object class) and writes it to the file 
	 * located from the specified path.
	 * @param var An object that implements the Exportable interface.
	 * @param aPath The destination path.
	 * @throws FileNotFoundException if the destination path doesn't exists.
	 */
	public static void exportSession(Exportable var, String aPath) throws FileNotFoundException
	{
		String data = var.getStatus();
		PrintWriter file = new PrintWriter(new File(aPath));
		file.print(data);
		file.close();
	}
}
