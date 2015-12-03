package triviamaze;

/**
 * TrueFalseQuestion.java
 * Author: Jenia Rousseva
 * Revision: 2, Jenia Rousseva
 * Date: 11/20/2015
 * This file provides the implementation of a true/false question object.
 * For these types of questions the user must answer T or F and no hints 
 * are allowed as of now.
 * Reference:
 * 
 * http://www.regexplanet.com/advanced/java/index.html
 */

public class TrueFalseQuestion extends Question 
{
	private static final String REGEX = "((?i)[tf])";
	
	
	/*
	 * Initializes a new node with the default values. 
	 * Calls superclass constructor and sets the appropriate
	 * regex expression.
	 */
	
	public TrueFalseQuestion()
	{
		super(null, null);
		super.setPattern(REGEX);
	}//end TrueFalseQuestion
	
	
	/*
	 * Initializes a new TrueFalseQuestion with the parameters passed in.
	 * Parameters:
	 * String question - The question prompt
	 * String correctAnswer - The right answer for the question
	 */
	
	public TrueFalseQuestion(String question, String correctAnswer) 
	{
		super(question, correctAnswer);
		super.setPattern(REGEX);
	}//end TrueFalseQuestion
	
	
	/*
	 * Overridden method from the Question superclass to display the
	 * question and a prompt to enter T or F.
	 */
	
	@Override
	public void printQuestion()
	{		
		super.printQuestion();
		System.out.println("\nChoose T or F:");
	}//end printQuestion
	
	
	/*
	 * Overridden method to provide a hint for a true/false question.
	 */

	@Override
	public void hint() // Allow hints here?
	{
		// TODO Auto-generated method stub
	}//end hint

}//end TrueFalseQuestion
