package triviamaze;

/**
 * QuestionFactory.java
 * Author: Jenia Rousseva
 * Revision: 3, Nick Witmer
 * Date: 11/22/2015
 * This a simple factory for the creation of one of the three types of 
 * questions: short answer, multiple choice, or true/false.
 */

public class QuestionFactory 
{
	/*
	 * DVC for a QuestionFactory
	 */
	
	public QuestionFactory() {}
	
	
	/*
	 * Creates one of the three question types based in the 
	 * type passed in.
	 * Parameters:
	 * String type - A String description of the type of question 
	 * to create
	 * Returns:
	 * Question - A generic question
	 */
	
	public Question createQuestion(String type) //may change the type of the parameter to int or char
	{
		Question question = null;
		
		if (type.equals("shortanswer"))
		{
			question = new ShortAnswerQuestion();
		}//end if
		else if (type.equals("multiplechoice"))
		{
			question = new MultipleChoiceQuestion();
		}//end else if
		else if (type.equals("truefalse"))
		{
			question = new TrueFalseQuestion();
		}//end else if
		else if(type.equals("error"))
		{
			question = new ErrorQuestion();
		}
		return question;
	}//end createQuestion

}//end QuestionFactory
