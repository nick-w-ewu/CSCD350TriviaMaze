package questions;

/**
 * QuestionFactory.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/09/2015
 * This a simple factory for the creation of one of the three types of 
 * questions: short answer, multiple choice, or true/false.
 */

public class QuestionFactory 
{
	public QuestionFactory() {}
	
	public Question createQuestion(String type) //may change the type of the parameter to int or char
	{
		Question question = null;
		
		if (type.equals("shortAnswer"))
		{
			question = new ShortAnswerQuestion();
		}
		else if (type.equals("multipleChoice"))
		{
			question = new MultipleChoiceQuestion();
		}
		else if (type.equals("trueFalse"))
		{
			question = new TrueFalseQuestion();
		}
		return question;
	}//end createQuestion

}//end QuestionFactory
