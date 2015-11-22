package questions;

/**
 * ShortAnswerQuestion.java
 * Author: Jenia Rousseva
 * Revision: 2, Jenia Rousseva
 * Date: 11/19/2015
 * This file provides the implementation of a short answer question object.
 * These types of questions differ from other types in that a set of keyword(s)
 * will be used to match the user's answer to the correct answer.
 * Reference:
 * 
 * https://docs.moodle.org/22/en/Regular_Expression_Short-Answer_question_type
 * https://docs.moodle.org/29/en/Short_answer_analysis
 */

import java.util.Scanner;

public class ShortAnswerQuestion extends Question 
{
	
	/*
	 * Initializes a new node with the default values. 
	 * Calls superclass constructor.
	 */
	
	public ShortAnswerQuestion() 
	{
		super(null, null);
	}//end ShortAnswerQuestion
	
	
	/*
	 * Initializes a new ShortAnswerQuestion with the parameters passed in.
	 * Parameters:
	 * String question - The question prompt
	 * String correctAnswer - The right answer for the question
	 * String[] keywords - A list of keywords, possible answers, for the question
	 */
	
	public ShortAnswerQuestion(String question, String correctAnswer, String[] keywords)
	{
		super(question, correctAnswer);
		super.setChoices(keywords);
	}//end ShortAnswerQuestion

	
	/*
	 * Overrides the printQuestion method from the Question class.
	 */
	
	@Override
	public void printQuestion()
	{		
		super.printQuestion();
		System.out.println("\n\nEnter your answer\n");
	}//end printQuestion
	
	
	/*
	 * Overrides the getInut method from the Question class.
	 * Accepts any String as an answer.
	 * Returns:
	 * String - The player's input for the answer to the question
	 */
	
	@Override
	public String getValidInput() 
	{
		Scanner sc = new Scanner(System.in);
		String answer = null;
	
		System.out.println("Enter you answer: ");
		answer = sc.nextLine();
		answer.trim();
		sc.close();
		
		return answer;
	}//end getValidInput

	
	/*
	 *  Method to compare the user's input to the keyword(s) using regex 
	 *  expressions. As long as the user's answer contains one of the 
	 *  keywords in the array they get the question correct.
	 *  Parameters:
	 *  String input - Player's answer to the question
	 *  Returns:
	 *  boolean - Whether the user answered the question correctly or not
	 */
	
	@ Override
	public boolean checkCorrectAnswer(String input)
	{
		String[] keywords = this.getChoices();
		
		for (int i = 0; i < keywords.length; i ++)
		{
			String key = keywords[i];
			String pattern = "(?i).*\\b" + key + "\\b.*";
		//	System.out.println("The pattern is: " + pattern);
			if (input.matches(pattern))
				return true;
		}//end for
		return false;
	}//end checkCorrectAnswer
	
	
	/*
	 * Overridden method from the Question class.
	 */
	
	@Override
	public void hint() 
	{
		// TODO Auto-generated method stub	
	}//end hint

}//end ShortAnswerQuestion
