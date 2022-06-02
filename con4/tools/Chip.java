package con4.tools;
/**
 *The Chip class represents the making of tokens. 
 * Each token has a value defined by a String object.
 * @author Simone Gentili
 */
public class Chip implements Cloneable
{
	public static String EMPTY = " ";
	
	//the value of the token.
	private String value;
	
	/**
	 * Constructs a Chip object having the 
	 * specified String object as its value.
	 * @param aValue The String object.
	 */
	public Chip(String aValue)
	{
		value = aValue;
	}
	
	/**
	 * Sets the value of this object with 
	 * the specified String object.
	 * @param aValue The String object.
	 */
	public void setValue(String aValue)
	{
		value = aValue;
	}

	/**
	 * Returns true if the specified object 
	 * has the same value as this object.
	 * @return true if they are the same
	 */
	public int compareTo(Object obj)
	{
		if(obj instanceof Chip)
		{
			Chip otherChip = (Chip) obj;
			return compareTo(otherChip.getValue());
		}
		else
		{
			throw new IllegalArgumentException("The specified object is not valid."
					+ "Only Chip object accepted");
		}
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Chip)
		{
			Chip otherChip = (Chip) obj;
			String otherChipValue = otherChip.getValue();
			return this.value.equals(otherChipValue);
		}
		
		else
		{
			throw new IllegalArgumentException("The specified object is not valid."
					+ "Only Chip object accepted");
		}
	}

	/**
	 * Returns a Chip object having the 
	 * same value as this object.
	 * @return The clone object.
	 */
	public Chip clone() throws CloneNotSupportedException
	{
		return (Chip)super.clone();
	}	

	/**
	 * Returns this object in String format.
	 * The returned string is of the type:
	 * Chip[value of the Chip object]
	 * @return The String object of this object.
	 */
	public String toString()
	{
		return "Chip[" + value + "]";
	}
	
	public String getValue()
	{
		return value;
	}

}
