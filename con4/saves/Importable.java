package con4.saves;
import java.util.Scanner;

/**
 * Interface for importing data into a given class.
 * @author Simone Gentili
 */
public interface Importable 
{
	/**
	 * It should set the class that implements this 
	 * interface with the specified Scanner object 
	 * (see the auxiliary class SessionImporter).
	 * @param A Scanner object containing the data
	 */
	public abstract void setStatus(Scanner input);

}
