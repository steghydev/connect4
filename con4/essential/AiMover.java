package con4.essential;

import java.util.ArrayList;
import java.util.Random;

import con4.tools.Chip;

/**
 * This class combines the AI ​​class and the AIExaminer class. 
 * Based on the current difficulty of the Ai (see the class Ai) 
 * and the checks carried out by the class AiExaminer (see the
 * class AiExaminer) it returns a positive integer value using 
 * the method move (String a1, Force4 a2).
 * First of all check if it is possible to achieve a consecutive 
 * sequence of 4 Chip objects that would allow the AI ​​to win.
 * If there is no value then check if the opponent can achieve 
 * this sequence. If it does not return a useful value then it 
 * generates the column number in a psudo-random way among the 
 * indices of the available columns.
 * The controls are adjusted based on the current difficulty of 
 * the ai. Easy difficulty allows for vertical checks. Medium 
 * difficulty allows you to perform horizontal and vertical checks 
 * in order. Difficulty difficult allows to carry out in order 
 * the oblique, horizontal and vertical checks.
 * @author Simone Gentili
 */
public class AiMover 
{
	private static final int ABANDON = -1;	
	
	private static final Random RANDOM_CHOISE = new Random();

	/**
	 * Returns the columns available in the grid.
	 * @param grid A Grid object.
	 * @return A vector that contains all available columns.
	 */
	private static ArrayList<Integer> getColumnsToChoose(Connect4Grid grid)
	{
		ArrayList<Integer> columns = new ArrayList<>();
		for( int column : grid.getFreeColumn() )
		{
			columns.add(column);
		}
		return columns;
	}
	
	/**
	 * Returns a value representing the chosen column.
	 * @param Ai An Ai object.
	 * @param force4 A Force4 object.
	 * @return A positive integer value.
	 */
	public static int move(String difficulty,  Connect4 force4, Ai ai)
	{
		/*
		 * Check all the old moves, starting with the last one
		 *  and working backwards.
		 */
		int size = 0;
		
		//AI chips first
		size = force4.getMovementsOf(force4.players[1]).size();

		for(int i=0; i < size; i++)
		{
			int[] pos = force4.getMovementsOf(force4.players[1]).get(size-i-1);
			
			int yAi = pos[0]; 
			int xAi= pos[1];
			
			int result = moveBasedOn(yAi, xAi, force4.chips[1], force4.grid, ai);
			
			if(result != ABANDON) 
			{
				return result;
			}
		}
		
		//Then the opponent's chips.
		size = force4.getMovementsOf(force4.players[0]).size();
		
		for(int i=0; i < size; i++)
		{
			int[] pos = force4.getMovementsOf(force4.players[0]).get(size-i-1);
			int yOpp = pos[0]; 
			int xOpp = pos[1];
			
			int result = moveBasedOn(yOpp, xOpp, force4.chips[0], force4.grid, ai);
			
			if(result != ABANDON) 
			{
				return result;
			}
		} 
		//no long sequence 3 found. Randomize!
		return randomColumn(force4.grid);
	}
	
	/**
	 * According to the difficulty, it regulates the 
	 * checks to be carried out.
	 * @param y The Y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Grid object.
	 * @param Ai An Ai object.
	 * @return A positive integer number.
	 */
	private static int moveBasedOn(int y, int x, Chip aChip, Connect4Grid aGrid, Ai ai) 
	{
		String difficulty = ai.getCurrentDifficulty();
		if(difficulty.equals(Ai.EASY_DIFF)) 
		{
			return easyMove(y, x, aChip, aGrid);
		}
		else if(difficulty.equals(Ai.MEDIUM_DIFF)) 
		{
			return mediumMove(y, x, aChip, aGrid);
		}
		else if(difficulty.equals(Ai.HARD_DIFF))
		{
			return hardMove(y, x, aChip, aGrid);
		}
		else
		{
			throw new IllegalArgumentException("the value specified for the "
					                           + "difficulty is not valid."
					                           + "Use the keyword Hard, Medium or Easy.");
		}
	}
		
	/**
	 * Returns -1 if nothing is found, otherwise a positive integer.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A chip object.
	 * @param aGrid A Grid object.
	 * @return -1 or a positive integer number.
	 */
	private static int easyMove(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		int result = AiExaminer.verticalControls(y, x, aChip, aGrid);
		return result;
	}

	/**
	 * Returns -1 if not nothing is found, otherwise a positive integer.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Grid object.
	 * @return -1 or a positive integer number.
	 */
	private static int mediumMove(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		int result = AiExaminer.horizontalControls(y, x, aChip, aGrid);
		
		if(result == ABANDON) 
		{
			result = AiExaminer.verticalControls(y, x, aChip, aGrid);
		} 
		return result;
	}
	
	/**
	 * Returns -1 if not nothing is found, otherwise a positive integer.
	 * @param y The y coordinate.
	 * @param x The x coordinate.
	 * @param aChip A Chip object.
	 * @param aGrid A Grid object.
	 * @return -1 or a positive integer number.
	 */
	private static int hardMove(int y, int x, Chip aChip, Connect4Grid aGrid)
	{
		int result = AiExaminer.obliqueControls(y, x, aChip, aGrid);
		
		if(result == ABANDON) 
		{
			result = AiExaminer.horizontalControls(y, x, aChip, aGrid);
		}
		
		if(result == ABANDON) 
		{
			result = AiExaminer.verticalControls(y, x, aChip, aGrid);
		} 
		return result;
	}

	/**
	 * returns a pseudo-random number chosen from the available 
	 * columns of the grid.
	 * @param grid A Grid object.
	 * @return A positive integer number.
	 */
	private static int randomColumn(Connect4Grid grid)
	{
		ArrayList<Integer> result = getColumnsToChoose(grid);
		int size = result.size();
		int choise = RANDOM_CHOISE.nextInt(size);
		return result.get(choise);
	}
	
}
