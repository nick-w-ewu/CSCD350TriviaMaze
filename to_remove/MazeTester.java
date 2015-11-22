package triviamaze;

/**
 * MazeTester.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/08/2015
 * This file tests the generation and traversal a Maze object.
 */

public class MazeTester 
{
	public static void main(String[] args) 
	{
		Maze m = new Maze(3,3);
		
		do
		{
			m.print();
			m.getDirection();
		} while(m.pathExists() && !m.getIsEnd());
		
	}//end main 

}//end MazeTester
