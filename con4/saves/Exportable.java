package con4.saves;

/**
 * Interface for exporting data from a class.
 * @author Simone Gentili
 */
public interface Exportable 
{
	/**
	 * It must take the data from the class that implements 
	 * this interface and return it as a string.
	 * @return A String object containing the data.
	 */
	public abstract String getStatus();

}
