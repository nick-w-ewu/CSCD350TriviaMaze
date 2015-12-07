package triviamaze;

/**
 * MazeTester.java
 * Author: Jenia Rousseva
 * Revision: 1, Jenia Rousseva
 * Date: 12/03/2015
 * This file tests the generation and traversal a Maze object along with 
 * the retrieval of a question from the database..
 */

public class MazeQuestionTester 
{

	public static void main(String [] args)
	{
		DatabaseUtility db = new DatabaseUtility();
		Maze m = new Maze(2, 2);
		QuestionHandler qh = new QuestionHandler();
		Question question;
		
		boolean isQuestionCell = false;
		String questionType = "";
		
		do
		{
			m.print();
			m.getDirection();
			isQuestionCell = m.getLandOnQuestion();
			
			if(isQuestionCell)
			{
				questionType = m.getQuestionType();
				
				try
				{
					question = qh.checkFlagsReset(questionType, db);
					qh.handleQuestion(question, m); //also updates the maze
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}//end if
			
		} while(m.pathExists() && !m.getIsEnd());
	}// end main

}//end MazeQuestionTester
