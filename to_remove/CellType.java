package triviamaze;

/**
 * CellType.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/08/2015
 * This file defines an enum for the various types of cells that may appear
 * in the 2-D maze.
 */

public enum CellType 
{
	START,
	END, 
	OPEN,
	WALL,
	QUESTION,
	VISITED, 
	SUCCESS,
	BEEN_HERE,
	BLANK,
	PLAYER,
	EMPTY;
	
	/*
	 * Returns a string representation of the particular enum.
	 * Returns:
	 * String - A string character for the enum
	 */
	@Override 
	public String toString()
	{
		switch(this)
		{
			case START: return "S";
			case END: return "G";
			case OPEN: return "O";
			case WALL: return "W";
			case QUESTION: return "?";
			case VISITED: return "V";
			case SUCCESS: return ".";
			case BEEN_HERE: return "#";
			case BLANK: return "_";
			case PLAYER: return "P";
			case EMPTY: return " ";
			default: return " ";
		}
		
	}//end toString
	
}//end CellType



