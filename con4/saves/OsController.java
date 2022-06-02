package con4.saves;

/**
 * This class provides methods for check the os type system.
 * @author Simone Gentili
 */
public class OsController 
{
	private static final String OS_WINDOWS_NAME = "Windows";
	private static final String OS_LINUX_NAME = "Linux";
	
	/**
	 * Return a String object representing the os in use.
	 * @return A String object.
	 */
	private static String getOsName()
	{
		String osName = System.getProperty("os.name");
		return osName;
	}
	
	/**
	 * Returns true if the os is Windows, false otherwise.
	 * @return a boolean value.
	 */
	public static boolean isWindows()
	{
		return getOsName().startsWith(OS_WINDOWS_NAME);
	}
	
	/**
	 * Returns true if the os is Linux, false otherwise.
	 * @return a boolean value.
	 */
	public static boolean isLinux()
	{
		return getOsName().startsWith(OS_LINUX_NAME);
	}

}
