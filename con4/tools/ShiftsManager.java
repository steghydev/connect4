package con4.tools;
import java.util.Random;
/**
 * This class represents the management of two-player games.
 * There is a method ( setFirstRound() ) to select (pseudo-randomly) 
 * the player who starts the game. The reference to the player who starts 
 * the game can be consulted using the get method getPlayerTurn().
 * To continue the game, passing the turn to the other player, simply 
 * invoke the nextRound() method, which increases the turn counter 
 * and changes the reference to the player whose turn it is.
 * The resetMatch() method resets the turn counter and invokes 
 * the setFirstRound() method, allowing you to cleverly start a new game.
 * By default, the names of the players is "Player1" and "Player2" but
 * you can change the names of both players by invoking the 
 * setPlayersName(String aName1, String aName2) method.
 * @author Simone Gentili
 *
 */
public class ShiftsManager 
{
	private Player[] players;
	private Player playerTurn;

	/**
	 * Constructs a PlayerTurn object using default values ​​for player names. 
	 * The players are always 2. The default shift counter starts from 1.
	 * The start value of the counter can be modified by invoking the 
	 * respective method of its class.
	 */
	public ShiftsManager(Player[] arrayPlayers)
	{
		players = arrayPlayers;
	}

	/**
	 * Based on the generation of a pseudo-random number, it sets the 
	 * starting player. The generated number can be 1 or 0.
	 */
	public void setFirstRound()
	{
		Random random = new Random();
		int index = random.nextInt(2);
		playerTurn = players[index];	
	}
	
	/**
	 * If the specified player is contained in the players array, it
	 * sets the starting player with the specified player.
	 * @param aPlayer A Player object.
	 */
	public void nextRoundBy(Player aPlayer)
	{
		if(contains(aPlayer))
		{
			playerTurn = aPlayer;
		}
		else
		{
			throw new IllegalArgumentException("The specified player"
					+ "is not present among the players");
		}
	}
	
	
	/**
	 * This method changes the player who has to play and increase
	 * the turn counter.
	 * @throws Exception 
	 */
	public void nextRound()
	{
		if(players.length == 2)
		{
			if(players[0] == playerTurn)
			{
				playerTurn = players[1];
			}
			else 
			{
				playerTurn = players[0];
			}
		}
		else
		{
			throw new IllegalArgumentException("Only two players are accepted");
		}
	}
	
	/**
	 * Resets the turn counter ( it drops to value 1 ) and sets
	 * the starting player calling the setFirstRound() method.
	 */
	public void resetShifts()
	{
		setFirstRound();
	}
	
	/**
	 * Checks the array of players and returns true if the Player 
	 * object is contained.
	 * @param aPlayer The Player object
	 * @return true if the specified Player exists in the players array
	 * 			otherwise return false.
	 */
	private boolean contains(Player aPlayer)
	{
		for(Player player : players)
		{
			if(player.getName().equals(aPlayer.getName()))
			{
				return true;
			}
		} 
		return false;
	}
			
	/**
	 * Returns the player who has to play.
	 * @return A Player object.
	 */
	public Player getPlayerTurn()
	{
		return playerTurn;
	}
}