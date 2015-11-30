package triviamaze;

/**
 * QuestionHandler.java
 * Author: Jenia Rousseva
 * Revision: 1, Jenia Rousseva
 * Date: 11/28/2015
 * 
 * This file provides an utility for managing question retrieval from the database.
 */

public class QuestionHandler 
{	
	/*
	 * Retrieves a question of a the given type from the database.
	 * Parameters:
	 * String type - a description of the type of question needed
	 * DatabaseUtility db - an object that connects and retrieves questions
	 * from the database
	 * Returns:
	 * Question - either a true/false, multiple choice, or short answer question
	 */
	
	public Question getQuestionFromDB(String type, DatabaseUtility db)
	{
		boolean validQuestion = false;
		Question question;
		
		do
		{
			question = db.retrieveQuestion(type);
			if (question.getError() == false)
				validQuestion = true;
		} while (!validQuestion);
		
		return question;
	}//end getQuestionFromDB
	
	
	/*
	 * This method displays the question to the player, obtains an answer, 
	 * checks the answer, and then updates the maze.
	 * Parameters:
	 * Question question - either a true/false, multiple choice, or short answer question
	 * Maze maze - a maze 
	 */
	
	public void handleQuestion(Question question, Maze maze)
	{
		String input;
		boolean isCorrect;
		
		question.specialFunction(); //scrambles up the answer choices if a multiple choice question
		question.printQuestion();
		
		/* This could be where we allow for the player to use hints. */
		
		input = question.getValidInput();
		isCorrect = question.checkCorrectAnswer(input);
		
		maze.postUpdate(isCorrect); //updating the maze could be done somewhere else
		
	}//end handleQuestion

}//end QuestionHandler
