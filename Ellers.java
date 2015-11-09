package triviamaze;

/**
 * Ellers.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/08/2015
 * This file implements Eller's algorithm to generate a perfect maze.
 * While the general implementation is obtained from the references below,
 * some methods have been added and modified.
 * 
 * References: 
 * https://github.com/mgaut72/Mazes/blob/master/Ellers.java
 * http://www.neocomputer.org/projects/eller.html
 */

import java.util.Random;

public class Ellers 
{
    static final int  UNDETERMINED = -2;
    static final int  SET_WALL = -1;
 
    int       rows;           //the rows in the representative maze
    int       cols;           //the cols in the representative maze

    int       act_rows;       //the actual number of rows in the maze
    int       act_cols;       //the actual number of cols in the maze
    
    CellType[][] maze;

    int[]     current;        //the current row, excluding the outer walls
    int[]     next;           //the next row, excluding the outer walls

    int       numSet;         //track set numbers to make sure not to duplicate
    
    int 	  curRow;
    int       curCol;

    
    /*
     * Generates a perfect maze using Eller's algorithm.
     * Parameters:
     * int nRows - actual number of rows
     * int nCols - actual number of columns
     */
    
    public Ellers (int nRows, int nCols)
    {
        this.act_rows = nRows;
        this.act_cols = nCols;

        this.rows = this.act_rows * 2 + 1;
        this.cols = this.act_cols * 2 + 1;
        
        this.maze = new CellType[this.rows][this.cols];
        
        this.current = new int[this.act_cols*2-1];
        this.next    = new int[this.act_cols*2-1];

        initializeMaze();
        makeMaze();
 
        copyWithBoundary();
      
    }//end EVC
    
    
    /*
     * Returns the current maze.
     * Returns:
     * CellType[][] - The current maze
     */
    public CellType[][] getMaze()
    {
        return this.maze;
    }//end getMaze
    
    
    /*
     * Initializes the maze of the specified size with all the 
     * cells marked as walls.
     */
    
    private void initializeMaze()
    {
    	/* Sets the maze to filled */
        for(int i = 0; i < this.maze[0].length; i ++)
        {
            for(int j = 0; j < this.maze.length; j ++)
            {
            	this.maze[i][j] = CellType.WALL;
            }//end for
        }//end for

        for(int i = 0; i < next.length; i ++)
        {
            next[i] = UNDETERMINED;
        }//end for

        /* initialize the first row to unique sets */
        for(int i = 0; i < this.current.length; i += 2)
        {
            this.current[i] = i / 2 + 1;
            if(i != this.current.length - 1)
            {
                this.current[i+1] = SET_WALL;
            }//end if
        }//end for
        
        this.numSet = this.current[this.current.length - 1];
    }//end initializeMaze

    
    /*
     * This method is responsible for the actual construction of a perfect
     * maze via Eller's algorithm. 
     */
    
    private void makeMaze()
    {
        for(int q = 0; q < this.act_rows - 1; q ++) //for all rows but the last one
        {   
            if(q != 0)
            {
                /* get the current row from the last iteration */
                for(int i = 0; i < this.current.length; i ++)
                {
                    this.current[i] = this.next[i];
                    this.next[i] = UNDETERMINED;
                }//end for
            }//end if

            joinSets();
            makeVerticalCuts();
            
            /* populate the rest of the next row */
            for(int j = 0; j < this.current.length; j += 2)
            {
                if(this.next[j] == UNDETERMINED)
                    this.next[j] = ++ this.numSet;
                if(j != this.current.length-1)
                    this.next[j + 1] = SET_WALL;
            }//end for

            /* record the current row onto the maze */
            for(int k = 0; k < this.current.length; k ++)
            {
                if(this.current[k] == SET_WALL)
                {
                    this.maze[2 * q + 1][k + 1] = CellType.WALL;
                    this.maze[2 * q + 2][k + 1] = CellType.WALL;
                }//end if
                else
                {
                    this.maze[2 * q + 1][k + 1] = CellType.OPEN;
                    if(this.current[k] == this.next[k])
                    {
                    	this.maze[2 * q + 2][k + 1] = CellType.OPEN;
                    }//end if
                }//end else
            }//end for
        }//end for
        makeLastRow();
        makeOpenings();
    }//end makeMaze
    
    /*
     * Join two sets into one.
     */

    private void joinSets()
    {
    	Random rand = new Random();

    	/* Randomly join sets together */
    	for(int i = 1; i < this.current.length - 1; i += 2) //checks only at wall locations
    	{ 
    		/* make sure they are eligible to be combined:
    		 *      they have wall between then
    		 *      they are not part of the same set
    		 *
    		 * then get a random boolean to pick if they actually get combined
    		 */
    		if(this.current[i] == SET_WALL && this.current[i - 1] != this.current[i + 1] && rand.nextBoolean())
    		{
    			this.current[i] = 0; //take away the barrier

    			int old  = Math.max(this.current[i - 1], this.current[i + 1]);
    			int next = Math.min(this.current[i - 1], this.current[i + 1]);

    			/* combine the two sets into 1 (the smallest numbered set) */
    			for(int j = 0; j < this.current.length; j++)
    			{

    				if(this.current[j] == old)
    					this.current[j] = next;
    			}//end for
    		}//end if
    	}//end for
    }//end joinSets


    /* 
     * Randomly pick vertical paths for each set, making sure there
     * is at least 1 vertical path per set.
     */
    
    private void makeVerticalCuts()
    {
        Random rand = new Random();

        int beginning;     //the beginning of the section (inclusive)
        int end;          //the end of the section (inclusive)
        int i;
        boolean madeVertical;  /* tracks if a vertical path has been made
                                * in the section*/
        beginning = 0;
        
        do
        {
            /* find the end of this section */
            i = beginning;
            while(i < this.current.length-1 && this.current[i] == this.current[i + 2])
            {
                i += 2;
            }//end while
      
            end = i;

            /* loop trying to cut a vertical path in the section until it
             * is successful at least 1 time in the section
             */
            madeVertical = false;
            do
            {
                for(int j = beginning; j <= end; j += 2)
                {
                    if(rand.nextBoolean())
                    {
                        this.next[j] = this.current[j];
                        madeVertical = true;
                    }//end if
                }// end for
            }while(!madeVertical);

            beginning = end + 2;  //go to the next section in the row

        }while(end != this.current.length - 1);
    }//end makeVerticalCuts

    
    /*
     * Makes and adds a row to the maze.
     */
    
    private void makeLastRow()
    {
        /* get the current row from the last iteration */
        for(int i = 0; i < this.current.length; i ++)
        {
            this.current[i] = this.next[i];
        }//end for

        for(int i = 1; i < this.current.length - 1; i += 2)
        {
            if(this.current[i] == SET_WALL && this.current[i - 1] != this.current[i + 1])
            {
                this.current[i] = 0;

                int old  = Math.max(this. current[i - 1], this.current[i + 1]);
                int next = Math.min(this.current[i - 1], this.current[i + 1]);

                // combine the two sets into 1 (the smallest numbered set)
                for(int j = 0; j < this.current.length; j ++)
                {
                    if(this.current[j] == old)
                        this.current[j] = next;
                }//end for
            }//end if
        }//end for

        /* add the last row to the maze */
        for(int k = 0; k < this.current.length; k++)
        {
            if(this.current[k] == SET_WALL)
            {
                this.maze[rows - 2][k + 1] = CellType.WALL;
            }//end if
            else
            {
                this.maze[rows - 2][k + 1] = CellType.OPEN;
            }//end else
        }//end for
    }//end makeLastRow

    
    /*
     * Makes random openings in a given row.
     */
    
    private void makeOpenings()
    {
        Random rand = new Random(); //two different random number generators
        Random rand2 = new Random();//just in case

        //a random location for the entrance and exit
        int entrance_row = rand.nextInt(this.act_rows - 1) * 2 + 1;
        int exit_row = rand2.nextInt(this.act_rows - 1) * 2 + 1;

        //clear the location
        this.maze[entrance_row][0] = CellType.OPEN;
        this.maze[exit_row][this.cols - 1] = CellType.OPEN;      
    }//end makeOpenings

    
    /*
     * Prints the maze.
     */
    public void printMaze()
    {
    	for(int i=0; i < this.maze.length; i++)
    	{
            for(int j=0; j < this.maze[0].length; j++)
            {
                System.out.print(this.maze[i][j] + " ");
            }//end for
            System.out.println();
        }//end for
    	System.out.println();
    }//end printMaze
    
 
    /* Creates an outer boundary of cells marked as walls around the 
     * current maze.
     */
    
	private void copyWithBoundary()
	{
		CellType [][] copy = new CellType[this.maze.length + 2][this.maze[0].length + 2];
		
		for (int i = 0; i < copy.length; i ++)
		{
			copy[i][0] = CellType.WALL;
			copy[i][copy[0].length - 1] = CellType.WALL;
		}//end for	
		
		for (int j = 0; j < copy.length; j ++)
		{
			copy[0][j] = CellType.WALL;
			copy[copy.length - 1][j] = CellType.WALL;
		}//end for	
		
		for(int i = 0; i < this.maze.length; i ++)
		{
			for (int j = 0; j < this.maze[0].length; j ++)
			{
				copy[i + 1][j + 1] = this.maze[i][j];
			}//end for
		}//end for
		
		this.maze = copy;
	}//end copyWithBoundary
    
}//end class	
