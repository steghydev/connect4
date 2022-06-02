package con4.essential;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents the identity of the AI ​​for a game.
 * Contains basic AI information such as difficulty and name.
 * In particular, it was designed for Connect 4 play.
 * See the AIMover and AIExaminer classes.
 * it is possible to set the difficulty of the ai by calling 
 * the relative method setDifficulty(int a).
 * @author Simone Gentili
 */
public class Ai 
{
	public static final String AI_NAME = "ai";
	
	//the values of the difficulties.
	public static final String EASY_DIFF = "Easy";
	public static final String MEDIUM_DIFF = "Medium";
	public static final String HARD_DIFF  = "Hard";
	
	//the keys to use to set the difficulties
	public static final int KEY_EASY = 1;
	public static final int KEY_MEDIUM = 2;
	public static final int KEY_HARD = 3;
	
	private Map<Integer, String> mapDifficulty = new TreeMap<>(); 
	
	private String difficulty;

	/**
	 * When the ai is built the map that associates positive 
	 * integer values ​​to the difficulties is initialized.
	 */
	public Ai()
	{		
		setDifficultyMap();
	}

	/**
	 * Initialize the difficulty map of this item.
	 * This method is invoked when an ai object is constructed.
	 */
	private void setDifficultyMap()
	{
		mapDifficulty.put(KEY_EASY, EASY_DIFF);
		mapDifficulty.put(KEY_MEDIUM, MEDIUM_DIFF);
		mapDifficulty.put(KEY_HARD, HARD_DIFF);
	}
	
	/**
	 * sets the difficulty of the ai with the value 
	 * associated with the specified key (i.e the specified argument)
	 * within the map.
	 * The keys to use are defined in this class. These keys 
	 * are defined inside public static variables of this class.
	 * For example if you want to set the difficulty of ai a 
	 * difficult use the key Ai.KEY_HARD such as argument.
	 * If the value of the key (i.e the specified argument)
	 * is not valid, the method throws an IllegalArgumnetException.
	 * @param aDifficulty An integer >= 0 || <= 2
	 */
	public void setDifficulty(int aDifficulty)
	{
		if(mapDifficulty.containsKey(aDifficulty))
		{
			difficulty = mapDifficulty.get(aDifficulty);
		}
		else
		{

			throw new IllegalArgumentException("Can only accept 0, 1 or 2."
					+ " 0 for Easy, 1 for Medium, 2 for Hard");
		}
	}

	/**
	 * Returns the current difficulty of this object.
	 * @return
	 */
	public String getCurrentDifficulty()
	{
		return difficulty;
	}
}
