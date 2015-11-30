//package triviamaze;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Maze.java
 * Author: Jenia Rousseva
 * Revision: 2, Jenia Rousseva
 * Date: 11/20/2015
 * This file provides the basic construction for a 2-D non-perfect maze.
 * The maze contains slots which are either walls, open spaces, or specific question types.
 * This file also contains the methods for traversal through the maze and 
 * for finding if a path(s) exist(s) from the start to the end of the maze.
 */

public class Maze 
{
	static final double PROB_OPEN = 0.5;
	static final double PROB_QUESTION = 0.65;

	private int curRow, curCol, prevRow, prevCol;
	private CellType[][] maze;
	
	private boolean isEnd;
	

	/*
	 * Calls Eller's algorithm to generate a perfect maze. Then, converts this
	 * maze into a non-perfect maze
     * Parameters:
     * int rows - actual number of rows
     * int cols - actual number of columns
	 */
	
	public Maze(int rows, int cols)
	{
		Ellers ellerMaze = new Ellers(rows, cols);
		this.maze = ellerMaze.getMaze();

		findEntrance();
		findExit();

		System.out.println("Before Find Path");
		printMaze(this.maze);

		findPath(this.maze, this.curRow, this.curCol, true);

		System.out.println("BEFORE setting the final maze!");
		printMaze(this.maze);

		setFinalMaze();

		System.out.println("After setting the final maze!");
		printMaze(this.maze);

		CellType[][] copy = createCopy(this.maze);

		System.out.println("curRow = " + this.curRow + "  curCol = " + this.curCol);
		copy[this.curRow][this.curCol] = CellType.START;

		System.out.println("Total number of possible paths to the end: " + findAllPaths(copy, this.curRow, curCol));
		printMaze(this.maze);
	}//Maze
	
	
	/*
	 * Returns whether the current position is the target position.
	 * Returns:
	 * boolean - If the cell is the target end cell, i.e. equal to 'G'
	 */
	
	public boolean getIsEnd()
	{
		return this.isEnd;
	}//end getIsEnd

	 /*
	 * Returns the maze
	 * using it for the writing to save file process
	 */
	public CellType[][] getMaze()
	{
		return maze;
	}

	/*
* Overridden to accept PrintWriter for writing/file saving
* Parameter:
* CellType[][] mazeCopy - The maze to be printed
*/
	protected void printMaze(PrintWriter pout)
	{
		for(int i = 0; i < maze.length; i++)
		{
			for(int j=0; j < maze.length; j++)
			{
				pout.print(maze[i][j] + " ");
			}//end for
			pout.println();
		}//end for
		pout.println();
	}//end printMaze

	/*
	 * Resets the current row and column.
	 * Parameters:
	 * int i - The new row index
	 * int j - Ths new column index
	 */
	
	private void setPosition(int i, int j)
	{
		this.curRow = i;
		this.curCol = j;
	}//end setPosition

	
	/*
	 * Find the String indicator for the given cell.
	 * Parameters:
	 * int i - The row number to check
	 * int j - The column number to check
	 * Returns:
	 * String - A String indicator for the type of String
	 */
	
	private String getCellType(int i, int j)
	{
		return this.maze[i][j].toString();
	}//end getCellType
	
	
	/* 
	 * Finds the start position in the maze assuming there is at least one open 
	 * slot in the column 1 of the maze based on the maze generated using
	 * Eller's algorithm. Returns as soon as the cell marked with an 'S' is found.
     * 
	*/ 
	
    private void findEntrance()
    { 
    	for (int i = 1; i < this.maze.length - 1; i ++)
    	{
    		if(this.maze[i][1] == CellType.OPEN)
    		{
    			this.maze[i][1] = CellType.START;
    			this.curRow = i;
    			this.curCol = 1;
    			return;
    		}//end if
    	}//end for
    }//end findEntrace
    
    
    /* 
	 * Finds the target position in the maze assuming there is at least one open 
	 * slot in the column # maze.length - 2 of the maze based on the maze 
	 * generated using Eller's algorithm. 
	 * Returns as soon as the cell marked with an 'G' is found.
	*/ 
    
    private void findExit()
    {
    	for (int i = 0; i < this.maze.length - 1; i++)
    	{
    		if(this.maze[i][this.maze.length - 2] == CellType.OPEN)
    		{
    			this.maze[i][this.maze.length - 2] = CellType.END;
    			return;
    		}//end if
    	}//end for	
    }//end findExit
    
    
	/*
	 * Prints the maze to be displayed to the user. Only cells adjacent to the
	 * player current position are displayed.
	 */
    
	public void print() 
	{
		CellType [][] result = new CellType[maze.length][maze[0].length];

		for (int i = 0; i < maze.length; i++) 
		{
			for (int j = 0; j < maze[i].length; j++) 
			{			
				result[i][j] = CellType.EMPTY;
				
				if ((i == this.curRow - 1 || i == this.curRow + 1 || i == this.curRow)
						&& (j == this.curCol - 1 || j == this.curCol + 1 || j == this.curCol)) 
				{
					if (maze[i][j] == CellType.WALL)
						result[i][j] = CellType.WALL;
					else
						result[i][j] = CellType.BLANK;
				}//end if
				if (maze[i][j] == CellType.END || maze[i][j] == CellType.VISITED)
				{
					result[i][j] = maze[i][j];
				}//end if						
				if (i == this.curRow && j == this.curCol)
				{
					result[i][j] = CellType.PLAYER;
				}//end if
				if (i == 0 || i == maze.length - 1 || j == 0 || j == maze.length - 1)
				{
					result[i][j] = CellType.WALL;
				}//end if
			}//end for
		}//end for
		
		printMaze(result);
	}//end print


	/*
	 * This is a private method that to print a maze. It is used for
	 * debugging purposes
	 * Parameter:
	 * CellType[][] mazeCopy - The maze to be printed
	 */

	private void printMaze(CellType[][] mazeCopy)
	{
		for(int i = 0; i < mazeCopy.length; i++)
		{
			for(int j=0; j < mazeCopy.length; j++)
			{
				System.out.print(mazeCopy[i][j] + " ");
			}//end for
			System.out.println();
		}//end for
		System.out.println();
	}//end printMaze

	
	
	/*
	 * Creates a copy of the maze passed in.
	 * Parameters:
	 * CellType mazeCopy - The maze to be copied
	 * Returns:
	 * CellType[][] - A copy of the maze
	 */
	
	private CellType[][] createCopy(CellType[][] mazeCopy)
	{
		CellType [][] copy = new CellType[mazeCopy.length][mazeCopy[0].length];

		for (int i = 0; i < mazeCopy.length; i ++)
		{
			for (int j = 0; j < mazeCopy[0].length; j ++)
			{
				copy[i][j] = mazeCopy[i][j];
			}//end for
		}//end for
		return copy;
	}//end createCopy

	
	/*
	 * Checks of the user has reached the target position in the maze and 
	 * notifies the user.
	 * Parameters:
	 * int i - The row number to check
	 * int j - The column number to check
	 * Returns:
	 * boolean - If the user has reached the target position
	 */
	
	private boolean reachEnd(int i, int j) 
	{
		if (maze[i][j] == CellType.END) 
		{
			System.out.println("\nCONGRAGULATIONS! You've made it to the end!");
			return true;
		}//end if
		return false;
	}//end reachEnd
	
	
	/*
	 * Checks if a cell in the maze is a wall.
	 * Parameters:
	 * int i - The row number to check
	 * int j - The column number to check
	 * Returns:
	 * boolean - If the cell is a wall i.e. marked 'W'
	 */
	private boolean canMove(int i, int j) 
	{
		if (maze[i][j] == CellType.WALL)
			return false;
		return true;
	}//end canMove
	

	/*
	 * Gets the direction to move from the player (north, east, south, or west)
	 * and verifies that this is a valid direction. Loops until the user quits or
	 * enters a valid direction.
	 */
	
	public void getDirection() 
	{
		Scanner sc = new Scanner(System.in);
		boolean validInput = false;
		String choice = "";
		char ch = 'N';

		do {
			System.out.print("Enter the direction you wish to move (N, E, S, W), V to view your party, or Q to quit): ");
			choice = sc.next();
			choice.trim();

			if (choice.length() == 1) 
			{
				ch = choice.toUpperCase().charAt(0);

				if (ch == 'N' || ch == 'E' || ch == 'S' || ch == 'W' ||  ch == 'Q')
				{
					validInput = true;
					if (ch == 'Q')
					{
						System.out.println("\nQuitting the current game...\n");
						this.isEnd = true;
					}//end if
				}//end if
				else
					System.out.println("Must select a valid option.");
			}//end if
			else 
			{
				System.out.println("Invalid input. Please try again.");
			}//end else
		} while (!validInput);
		
		if (ch != 'Q')
			verifyDirection(ch);		
	//	sc.close();
	}//end getDirection

	
	/*
	 * Checks whether the direction in which to move entered by the user is 
	 * indeed a valid direction, i.e. not a wall. If valid marks the cell 
	 * as visited and updates the current row and column.
	 * Parameter:
	 * char ch - A direction in which to move (north, east, south, or west)
	 */
	
	private void verifyDirection(char ch) 
	{
		int i = this.curRow, j = this.curCol;

		// Remember the previous position
		this.prevRow = this.curRow;
		this.prevCol = this.curCol;

		if (ch == 'N') 
		{
			if (canMove(i - 1, j))
				i -= 1;
		}//end if
		else if (ch == 'S')
		{
			if (canMove(i + 1, j))
				i += 1;
		}//end else if
		else if (ch == 'E') 
		{
			if (canMove(i, j + 1))
				j += 1;
		}//end else if
		else if (ch == 'W') 
		{
			if (canMove(i, j - 1))
				j -= 1;
		}//end else if
		if (i == this.curRow && j == this.curCol) 
		{
			System.out.println("This is an invalid move. Please select another option.");
			getDirection();
		}//end if
		else 
		{
			maze[this.curRow][this.curCol] = CellType.VISITED;
			setPosition(i, j);
			reachEnd(i, j); 
		}//end else
	}//end verifyDirection
	

	/*
	 * Either open up or block off the path position in the maze depending on
	 * if the player answered the question correctly or not.
	 * Parameter:
	 * boolean success - If the user answered the question correctly
	 */
	
	public void postUpdate(boolean success) 
	{
		if (success) 
		{
			maze[this.curRow][this.curCol] = CellType.VISITED;
		}//end if 
		else 
		{
			maze[this.curRow][this.curCol] = CellType.WALL;
			setPosition(this.prevRow, this.prevCol); // Go back to the previous position
		}//end else
	}//end postUpdate 

	
	/*
	 * Checks if a path exists to the goal position from the current position.
	 * If not, we want to end the current game.
	 */
	
	public boolean pathExists() 
	{
		
		CellType[][] mazeCopy = createCopy(this.maze);
		mazeCopy[this.curRow][this.curCol] = CellType.START;

		System.out.println("Number of possible paths to the end: " + findAllPaths(mazeCopy, this.curRow, this.curCol));
		
		mazeCopy = createCopy(this.maze);
		mazeCopy[this.curRow][this.curCol] = CellType.START;
		
		boolean pathExists = findPath(mazeCopy, this.curRow, this.curCol, false);	

		if (pathExists) 
		{
			System.out.println("A path exists to the end of the maze.\n");
		}//end if 
		else 
		{
			System.out.println("No path can be found to the end of the maze.\n");
		}//end else
		return pathExists;
	}//end pathExists

	
	/*
	 * Turns a cell marked as a wall into an open cell in efforts to create
	 * a non-perfect maze.
	 * Parameters:
	 * CellType[][] mazeCopy - A maze object
	 * int i - The number of the row of the cell in the maze to potentially
	 * convert into an open cell
	 * int j - The number of the column of the cell in the maze to potentially
	 * convert into an open cell
	 */
	
	private void breakWall(CellType[][] mazeCopy, int i, int j)
	{
		if (i > 0 &&  j > 0 && i < this.maze.length - 1 && j < this.maze[0].length - 1)
		{
			if(mazeCopy[i][j] == CellType.WALL && Math.random() < PROB_OPEN)
			{
				mazeCopy[i][j] = CellType.OPEN;
			}//end if
		}//end if
	}//end breakWall
	
	
	/*
	 * Converts a perfect maze into a non-perfect maze by converting some of 
	 * the walls along the path from start to finish into open spaces.
	 * Parameters:
	 * CellType[][] mazeCopy - A maze object
	 * int i - The number of the row of the cell in the maze to potentially
	 * convert into an open cell
	 * int j - The number of the column of the cell in the maze to potentially
	 * convert into an open cell
	 */
	
    private void makeNonPerfect(CellType[][] mazeCopy, int i, int j)
    { 	
    	breakWall(mazeCopy, i - 1, j);
    	breakWall(mazeCopy, i + 1, j);
    	breakWall(mazeCopy, i, j - 1);
    	breakWall(mazeCopy, i, j + 1);
    }//end makeNonPerfect
    
    
    /*
     * Converts an open cell into a cell containing either a true/false, 
     * multiple choice, or short answer question.
     * Parameters:
     * int i - A column number in the maze
     * int j - A row number in the maze
     */
    private void chooseQuestionSpaces(int i, int j)
    {
    	if (Math.random() < PROB_QUESTION)
    	{
    		//this.maze[i][j] = CellType.QUESTION;
    		
    		/* The code below determines what type of question to place in the slot. */
    		double typeProb = Math.random();
    		if (typeProb < 0.33)
    			this.maze[i][j] = CellType.TFQUESTION;
    		else if (typeProb >= 0.33 && typeProb < 0.67)
    			this.maze[i][j] = CellType.MCQUESTION;
    		else
    			this.maze[i][j] = CellType.SAQUESTION; 
    	}//end if
    	else
    	{
    		this.maze[i][j] = CellType.OPEN;
    	}//end else
    }//end chooseQuestionSpaces
    
    
    /*
     * Converts a non-perfect maze with only walls and open cells into
     * a maze with walls, open cells, and questions cells.
     */
    
    private void setFinalMaze()
    {
    	for(int i = 1; i < this.maze.length - 1; i ++)
    	{
    		for(int j = 1; j < this.maze[0].length - 1; j ++)
    		{
    			if (this.maze[i][j] != CellType.WALL && this.maze[i][j] != CellType.START
    				&& this.maze[i][j] != CellType.END)
    			{
    				chooseQuestionSpaces(i, j);
     			}//end if
    		}//end for
    	}//end for
    }//end setFinalMaze
    
    
    /*
     * Checks if the indicated cell is a valid move. 
     * For use in findPath.
     * Parameters:
     * CellType[][] mazeCopy - A maze
     * int i - The row number of the cell to check
     * int j - The column number of the cell to check 
     * Returns: 
     * boolean - If this is a valid cell to move to 
     */
    
    private boolean isValidMove(CellType[][] mazeCopy, int i, int j) 
	{
    	if(mazeCopy[i][j] != CellType.WALL && mazeCopy[i][j] != CellType.BEEN_HERE)
    		return true;
		return false;
	}//end isValidMove

    
    /*
     * Checks if the indicated cell is a valid move. 
     * For use in findAllPaths.
     * Parameters:
     * CellType[][] mazeCopy - A maze
     * int i - The row number of the cell to check
     * int j - The column number of the cell to check 
     * Returns: 
     * boolean - If this is a valid cell to move to 
     */
    
    private boolean isValidMove2(CellType[][] mazeCopy, int i, int j)
	{
		if(mazeCopy[i][j] != CellType.WALL) 
		{
			if(mazeCopy[i][j] == CellType.START || mazeCopy[i][j] == CellType.OPEN ||
			   mazeCopy[i][j] == CellType.TFQUESTION || mazeCopy[i][j] == CellType.MCQUESTION ||
			   mazeCopy[i][j] == CellType.SAQUESTION || mazeCopy[i][j] == CellType.END)
				return true;
		}//end if
		return false;
	}//end isValidMove2
    
    
    /*
     * Finds a path from the cell in row i and column j to the position marked 
     * 'G' in the maze if it exists. May also convert a perfect maze into a non-
     * perfect one along the way if branchOff is set to true.
     * Parameters:
     * CellType[][] mazeCopy - A maze
     * int i - The row number of the cell to check
     * int j - The column number of the cell to check 
     * boolean branchOff - If we want to create more open paths along the way
     * to the target position
     * Returns: 
     * boolean - If this is a cell to move to along the path 
     */
    
    public boolean findPath(CellType[][] mazeCopy, int i, int j, boolean branchOff) 
    {
		boolean validPath = false;
	
		if (isValidMove(mazeCopy, i, j)) 
		{
			if(mazeCopy[i][j] == CellType.END)
				return true;

			if (mazeCopy[i][j] != CellType.START)
				mazeCopy[i][j] = CellType.BEEN_HERE;

			validPath = findPath(mazeCopy, i - 1, j, branchOff);
			if (!validPath)
				validPath = findPath(mazeCopy, i + 1, j, branchOff);
			if (!validPath)
				validPath = findPath(mazeCopy, i, j - 1, branchOff);
			if (!validPath)
				validPath = findPath(mazeCopy, i, j + 1, branchOff);

			if (validPath && mazeCopy[i][j] != CellType.START)
			{
				mazeCopy[i][j] = CellType.SUCCESS;
				if(branchOff)
					makeNonPerfect(mazeCopy, i, j);
			}//end if
			
		}//end if
		return validPath;
	}//end findPath
    
    
    /*
     * Counts all possible paths from the cell in row i and column j to the position marked 
     * 'G' in the maze if it exists. May also convert a perfect maze into a non-
     * perfect one along the way if branchOff is set to true.
     * Parameters:
     * CellType[][] mazeCopy - A maze
     * int i - The row number of the cell to check
     * int j - The column number of the cell to check 
     * Returns: 
     * int - The number of paths to the target position
     */
    
	public int findAllPaths(CellType[][] mazeCopy, int i, int j)
	{
		if(!isValidMove2(mazeCopy, i, j))
		{
			return 0;
		}//end if	

		if(mazeCopy[i][j] == CellType.END)
		{
		//	printMaze(mazeCopy);
			return 1;
		}//end if
		
		mazeCopy[i][j] = CellType.SUCCESS;
		CellType [][] copy2 = createCopy(mazeCopy);
		
		int paths = 0;
		
		paths += findAllPaths(copy2, i - 1, j);
		paths += findAllPaths(copy2, i + 1, j);
		paths += findAllPaths(copy2, i, j - 1);
		paths += findAllPaths(copy2, i, j + 1);
	
		mazeCopy[i][j] = CellType.OPEN;
		
		return paths;
	}//end findAllPaths
	
}//end Maze
