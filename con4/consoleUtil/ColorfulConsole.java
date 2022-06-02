package con4.consoleUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * This class allows you to manage and apply colors on a command line program.
 * ANSI color escape sequences are contained within the maps. They can be consulted 
 * through the use of maps ( using the getBackgroundColor(arg) and getTextColor(arg) ). 
 * The names of the colors to use as arguments for the getBackgroundColor(arg) and 
 * getTextColor(arg) methods are contained in the public static variables of this class.
 * For example, to know the ANSI escape sequence for the red color of the text, just 
 * call the getTextColor(ColorConsole.RED) method.
 * Also you can print the color palette ( using the printPalette() method )
 * to allow a user to choose the color to use by entering a number. The number 
 * entered by the user can be used as an argument for the method getColorArNumber(int i),
 * This last method returns the name of the color associated at the specified number.
 * 
 * @author Simone Gentili
 *
 */
public class ColorfulConsole
{
	private final boolean DISABLE_COLOR = false; //disable console colors.
	private final boolean ENABLE_COLOR = true;   //enable  console colors.

	/**
	 * The color names of the ANSI color 
	 * escape sequences (text and background)
	 */
	public final String BLACK =   "black";
	public final String RED =     "red";
	public final String GREEN =   "green";
	public final String YELLOW =  "yellow";
	public final String BLUE =    "blue";
	public final String PURPLE =  "purple";
	public final String CYAN =    "cyan";
	public final String WHITE =   "white";

	
	//Default colors for background and text.
	public final String DEFAULT_BG = "\u001B[40m"; //black bg color.
	public final String DEFAULT_TX = "\u001B[37m"; //white tx color.
	
	//These arrays are used to fill maps.
	private final String[] COLOR_NAMES = {
			"black","red","green","yellow","blue","purple",
			"cyan","white"};
	private final String[] TEXT_COLORS = {
			"\u001B[30m","\u001B[31m","\u001B[32m","\u001B[33m",
			"\u001B[34m","\u001B[35m","\u001B[36m","\u001B[37m"};
	//background binary colors.
	private final String[] BACKGROUND_COLORS = {
			"\u001B[40m","\u001B[41m","\u001B[42m","\u001B[43m",
			"\u001B[44m","\u001B[45m","\u001B[46m","\u001B[47m"};
	
	//reset the colors.
	public final String RESET = "\u001B[0m";
	
	//palette colors.
	private final String PALETTE = "\u001B[40m" + "  1  " + 
	"\u001B[41m" + "  2  " + "\u001B[42m" + "  3  " + "\u001B[43m" + 
    "  4  " + "\u001B[44m" + "  5  " + "\u001B[45m" + "  6  " + 
	"\u001B[46m" + "  7  " + "\u001B[47m" + "  8  " + "\u001B[0m";
		
	//color map (key: name of the color, value: color escape sequence)
	private final Map<String, String>  TX_COLOR_MAP = new TreeMap<>();
	private final Map<String, String>  BG_COLOR_MAP = new TreeMap<>();
	
	/*
	 * Color map (key: number of the color, value: name of the color).
	 * This map is mostly used in conjunction with the color palette.
	 */
	private final Map<Integer, String> NUM_COLORS_MAP = new TreeMap<>();
	
	//Current text and background colors.
	private String textxColor;
	private String backgroundColor;
	
	//Signals if colors are enabled.
	private boolean colorConsole;
	
	/**
	 * Constructs a ColorConsole object by setting colors 
	 * to default values (black for the background and white 
	 * for the text). Also by default, the variable that signals 
	 * the enabling of colors is false.
	 * the maps are set and made usable through the public interface.
	 */
	public ColorfulConsole()
	{
		textxColor = DEFAULT_TX;
		backgroundColor = DEFAULT_BG;
		disableColor();
		setColorMaps(); 
	}
	
	/**
	 * Sets the variable that signals the enabling of colors 
	 * to the value true.
	 */
	public void enableColor()
	{
		colorConsole = ENABLE_COLOR;
	}
	
	/**
	 * Sets the variable that signals the enabling of colors 
	 * to the value false.
	 */
	public void disableColor()
	{
		colorConsole = DISABLE_COLOR;
	}
	
	/**
	 * Returns the value of the variable that signals the 
	 * enabling of colors
	 * @return true if colors are enabled, otherwise false.
	 */
	public boolean isActiveColorConsole()
	{
		return colorConsole;
	}
	
	/**
	 * Sets the color maps. Maps are for methods that set colors 
	 * and for methods that return ANSI color escape sequences 
	 * (such as get methods).
	 */
	public void setColorMaps()
	{
		int length = COLOR_NAMES.length;
		for(int i = 0; i < length; i++)
		{
			String colorName = COLOR_NAMES[i];
			TX_COLOR_MAP.put(colorName,TEXT_COLORS[i]);
			BG_COLOR_MAP.put(colorName, BACKGROUND_COLORS[i]);
			NUM_COLORS_MAP.put(i+1, colorName);
		}
	}
		
	/**
	 * Sets the current text color with the specified color name.
	 * @param aColorName A String object that represents the color name.
	 */
	public void setTextColor(String aColorName)
	{
		if(checkColor(aColorName))
		{
			textxColor = TX_COLOR_MAP.get(aColorName);
		}
		else { throw new IllegalArgumentException("Invalid name color: " + aColorName); } 
	}
	
	/**
	 * Sets the current background color with the specified color name.
	 * @param aColorName A String object that represents the color name.
	 */
	public void setBackgroundColor(String aColorName)
	{
		if(checkColor(aColorName))
		{
			backgroundColor = BG_COLOR_MAP.get(aColorName);
		}
		else { throw new IllegalArgumentException("Invalid name color: " + aColorName); }
	}
		
	/**
	 * Returns the name of the color associated with the number passed 
	 * as an argumnet. This method is useful when used in conjuction 
	 * with the color palette.
	 * @param aNumber A positive integer.
	 * @return String object.
	 */
	public String getColorAtNumber(int aNumber)
	{
		if(checkNumberColor(aNumber))
		{
			return NUM_COLORS_MAP.get(aNumber);
		}
		else { throw new IllegalArgumentException("the value needs to be > 0 || < 9"); }
	}
	
	/**
	 * Returns the ANSI escape sequence of the text color associated with
	 * the specified argument.
	 * @param aColorName A color name.
	 * @return An ANSI escape sequence.
	 */
	public String getTextColorAt(String aColorName)
	{
		if(checkColor(aColorName)) 
		{
			return TX_COLOR_MAP.get(aColorName);
		}
		else { throw new IllegalArgumentException("Invalid name color: " + aColorName); }
	}
	
	/**
	 * Returns the ANSI escape sequence of the background color associated with
	 * the specified argument.
	 * @param aColorName A color name.
	 * @return An ANSI escape sequence.
	 */
	public String getBackgroundColorAt(String aColorName)
	{
		if(checkColor(aColorName)) 
		{
			return BG_COLOR_MAP.get(aColorName);
		}
		else { throw new IllegalArgumentException("Invalid name color: " + aColorName); }
	}
	
	/**
	 * Returns the color currently set for the text.
	 * @return An ANSI escape sequence.
	 */
	public String getCurrentTextColor()
	{
		return textxColor;
	}
	
	/**
	 * Returns the color currently set for the background.
	 * @return An ANSI escape sequence.
	 */
	public String getCurrentBackgroundColor()
	{
		return backgroundColor;
	}
	
	/**
	 * Returns the ANSI escape sequence which resets the color 
	 * to the default values of the terminal in use.
	 * @return An ANSI escape sequence.
	 */
	public String resetColorConsole()
	{
		return RESET;
	}
	
	/**
	 * Checks if the specified color name is present 
	 * within the maps of this object.
	 * @param aColorName A color name
	 * @return true if it exists, otherwise false.
	 */
	public boolean checkColor(String aColorName)
	{
		if(TX_COLOR_MAP.containsKey(aColorName)) 
		{ 
			return true; 
		}
		else { return false; }
	}

	/**
	 * Checks if the specified number is a key within
	 * the map that associated integers with color names.
	 * It is invoked in getColorAtNumber(int aNumber)
	 * to verify that the number entered is valid.
	 * @param aNumber A number
	 * @return true if it exists, otherwise false.
	 */
	private boolean checkNumberColor(int aNumber)
	{
		if(NUM_COLORS_MAP.containsKey(aNumber))
		{
			return true;
		}
		else { return false; } 
	}

	/**
	 * Prints on screen the ANSI escape sequences
	 * currently in used for the text and background.
	 * As a result of this invocation the text and the
	 * background in the terminal will be changed.
	 */
	public void printColor()
	{
		System.out.print(backgroundColor);
		System.out.println(textxColor);
	}
	
	/**
	 * Prints on screen the color palette.
	 * It contains all eight colors of the ANSI escape sequences.
	 * Each color in the printed sequence contains an integer
	 * different from all the others. The number range from 1 to 8.
	 * It is useful in conjuction with getColorArNumber(int aNumber).
	 * For example: the user looks at the palette, chooses a color
	 * by specifying a number. The  name of the color from the map is 
	 * taken from the user's choise.
	 */
	public void printPalette()
	{
		System.out.println(PALETTE);
	}
}
