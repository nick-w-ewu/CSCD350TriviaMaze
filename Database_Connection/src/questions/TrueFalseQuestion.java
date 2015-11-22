package questions;

/**
 * TrueFalseQuestion.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/09/2015
 * This file provides the implementation of a true/false question object.
 * For these types of questions the user must answer T or F and no hints 
 * are allowed as of now.
 * Reference:
 * 
 * http://www.regexplanet.com/advanced/java/index.html
 */

public class TrueFalseQuestion extends Question 
{
	public TrueFalseQuestion()
	{
		super(null, null);
		super.setPattern("((?i)[tf])");
	}
	
	public TrueFalseQuestion(String question, String correctAnswer) 
	{
		super(question, correctAnswer);
		super.setPattern("((?i)[tf])");
	}
	
	@Override
	public void printQuestion()
	{		
		super.printQuestion();
		System.out.println("\n\nChoose T or F:\n");
	}

	@Override
	public void hint() // cannot have hints here
	{
		// TODO Auto-generated method stub
	}

}//end TrueFalseQuestion
