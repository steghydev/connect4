package con4.tools;
/**
 * This class represents individual players.
 * A player has a name. The name can be specified in 
 * the constructor method and optionally changed with 
 * the setName(String aName) method.
 * it is possible to know the name of a player by 
 * invoking the getName() method.
 * @author Simone Gentili
 */
public class Player 
{
	private String name;
	
	/**
	 * Builds a player with the specified name.
	 * @param aName The name of this player.
	 */
	public Player(String aName)
	{
		name = aName;
	}

	/**
	 * Sets the name of this player with the specified
	 * name.
	 * @param aName The new name of this player.
	 */
	public void setName(String aName)
	{
		name = aName;
	}
	
	/**
	 * Return the name of this player.
	 * @return The name of this player.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns true if this player's name is the same 
	 * as the specified player's name.
	 * @return true if they have the same name, 
	 * 			otherwise returns false.
	 */
	public boolean equals(Object aPlayer)
	{
		Player otherPlayer = (Player) aPlayer;
		String otherPlayerName = otherPlayer.getName();
		return name.equals(otherPlayerName);	
	}

	/**
	 * Returns the representation of this player in String
	 * format.The representation is of the type:
	 * Player[name of this Player object].
	 */
	public String toString()
	{
		return name;
	}
}
