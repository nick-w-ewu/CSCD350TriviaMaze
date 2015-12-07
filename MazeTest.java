package triviamaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MazeTest.java
 * Author: Jenia Rousseva
 * Revision: 1, Jenia Rousseva
 * Date: 12/6/2015
 * This file provides unit tests for methods in the Maze class.
 */

public class MazeTest 
{
	MazeCopy maze;
	String [][] array = {{"W", "W", "W", "W", "W", "W", "W"},
						{"W", "W", "W", "W", "W", "W", "W"},
				 		{"W", "O", "O", "W", "1", "G", "W"},
				 		{"W", "W", "O", "O", "2", "W", "W"},
				 		{"W", "W", "3", "1", "2", "W", "W"},
				 		{"W", "W", "O", "W", "W", "W", "W"},
				 		{"W", "W", "W", "W", "W", "W", "W"}};
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception  {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	
	/*
	 * Convert String array into a Maze object.
	 */
	
	@Before
	public void setUp() throws Exception 
	{
		CellType[][] newMaze = new CellType[array.length][array[0].length];
		
		for (int i = 0; i < array.length; i ++)
		{
			for (int j = 0; j < array[0].length; j ++)
			{			
				CellType cell;
				String spot = array[i][j];
				
				if (spot.equals("S"))
					cell = CellType.START;
				else if (spot.equals("G"))
					cell = CellType.END;
				else if (spot.equals("O"))
					cell = CellType.OPEN;
				else if (spot.equals("W"))
					cell = CellType.WALL;
				else if (spot.equals("?"))
					cell = CellType.QUESTION;
				else if (spot.equals("V"))
					cell = CellType.VISITED;
				else if (spot.equals("."))
					cell = CellType.SUCCESS;
				else if (spot.equals("#"))
					cell = CellType.BEEN_HERE;
				else if (spot.equals("P"))
					cell = CellType.PLAYER;
				else if (spot.equals("1"))
					cell = CellType.TFQUESTION;
				else if (spot.equals("2"))
					cell = CellType.MCQUESTION;
				else if (spot.equals("3"))
					cell = CellType.SAQUESTION;
				else
					cell = CellType.BLANK;
				
				newMaze[i][j] = cell;
			}//end for			
		}//end for
		
		maze = new MazeCopy(newMaze);

	}//end setUp

	
	@After
	public void tearDown() throws Exception 
	{
	}

	
	/*
	 * Test finding the start postion in the maze.
	 */
	
	@Test
	public final void testFindEntrance() 
	{
		maze.findEntrance();
		assertEquals(2, maze.getCurRow());
		assertEquals(1, maze.getCurCol());
	}//end testFindEntrance
	
	
	/*
	 * Test finding the goal position in the maze.
	 */
	
	@Test
	public final void testFindExit()
	{
		maze.findExit();
		maze.setPosition(2, 5);
		assertEquals(2, maze.getCurRow());
		assertEquals(5, maze.getCurCol());
	}//end testFindExit
	
	
	/*
	 * Test if at least one valid path exists from the start
	 * position to the goal position in the maze.
	 */
	
	@Test
	public final void testFindPath()
	{
		maze.setCell(2, 1, CellType.START);
		maze.setPosition(2, 1);
		assertEquals(true, maze.pathExists()); //pathExists calls findPath
		
		maze.setCell(2, 1, CellType.START);
		maze.setPosition(2, 1);
		maze.setCell(2, 2, CellType.WALL);
		assertEquals(false, maze.pathExists());
	}//end testFindPath
	
	
	/*
	 * Test landing on one of the three types of questions cells.
	 */
	
	@Test 
	public final void testGetLandOnQuestion()
	{
		maze.setPosition(2, 4);
		assertEquals(true, maze.getLandOnQuestion());
	
		maze.setPosition(3, 4);
		assertEquals(true, maze.getLandOnQuestion());
		
		maze.setPosition(4, 4);
		assertEquals(true, maze.getLandOnQuestion());
		
		maze.setPosition(4, 2);
		assertEquals(true, maze.getLandOnQuestion());
		
		maze.setPosition(5, 4);
		assertEquals(false, maze.getLandOnQuestion());
		
		maze.setPosition(4, 1);
		assertEquals(false, maze.getLandOnQuestion());
		
	}//end testGetLandOnQuestion 
	
	
	/*
	 * Test retrieving the String representation of the cell type.
	 */
	
	@Test
	public final void testGetQuestionType()
	{
		maze.setPosition(2, 4);
		assertEquals("truefalse", maze.getQuestionType());
		
		maze.setPosition(4, 3);
		assertEquals("truefalse", maze.getQuestionType());
		
		maze.setPosition(3, 4);
		assertEquals("multiplechoice", maze.getQuestionType());
		
		maze.setPosition(4, 4);
		assertEquals("multiplechoice", maze.getQuestionType());
		
		maze.setPosition(4, 2);
		assertEquals("shortanswer", maze.getQuestionType());
		
		maze.setPosition(5, 4);
		assertEquals("error", maze.getQuestionType());
		
		maze.setPosition(4, 1);
		assertEquals("error", maze.getQuestionType());
		
	}//end testGetQuestionType
	
	
	/*
	 * Test being at the goal position. 
	 */
	
	@Test 
	public final void testReachEnd()
	{
		assertEquals(false, maze.reachEnd(4, 5));
		assertEquals(false, maze.reachEnd(1, 5));
		assertEquals(true, maze.reachEnd(2, 5));	
	}//end testReachEnd
	
	
	/*
	 * Test if a valid (non-wall) position in the maze.
	 */
	
	@Test
	public final void testCanMove()
	{
		assertEquals(false, maze.canMove(2, 3));
		assertEquals(true, maze.canMove(3, 3));
		assertEquals(false, maze.canMove(4, 1));
		
	}//end testCanMove
	
	
	/*
	 * Test updating the maze after the player has answered a question.
	 */
	
	@Test
	public final void testPostUpdate()
	{
		maze.setPosition(2,2);
		maze.postUpdate(true);
		assertEquals(CellType.VISITED, maze.getCell(2, 2));
		
		
		maze.setPrevPosition(2,1);
		maze.postUpdate(false);
		assertEquals(CellType.WALL, maze.getCell(2, 2));
		
		/* Should go back to the previous position */
		assertEquals(2, maze.getCurRow());
		assertEquals(1, maze.getCurCol());
		
	}//end testPostUpdate
	
	
}// Maze Test
