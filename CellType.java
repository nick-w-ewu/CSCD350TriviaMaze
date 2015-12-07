package triviamaze;

/**
 * CellType.java
 * Author: Jenia Rousseva
 * Revision: 2, Jenia Rousseva
 * Date: 11/20/2015
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
	/* Fields for the specific question types */
	TFQUESTION,
	MCQUESTION,
	SAQUESTION,
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
			case TFQUESTION: return "1";
			case MCQUESTION: return "2";
			case SAQUESTION: return "3";
			default: return " ";
		}//end switch
		
	}//end toString
	
}//end CellType



