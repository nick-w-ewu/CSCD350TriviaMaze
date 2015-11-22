package questions;

/**
 * MultipleChoiceQuestion.java
 * Author: Jenia Rousseva
 * Revision: 2, Jenia Rousseva
 * Date: 11/19/2015
 * This file provides the implementation of a multiple choice question object.
 * These types of questions differ from other types in that it contains an 
 * array for the four possible answer choices. There is also a method to
 * shuffle the answer choices, so that they are displayed in random order
 * to the user each time.
 */

public class MultipleChoiceQuestion extends Question
{	
	private static final String REGEX = "((?i)[abcd])";
	
	
	/*
	 * Initializes a new node with the default values. 
	 * Calls superclass constructor and sets the appropriate
	 * regex expression.
	 */
	
	public MultipleChoiceQuestion()
	{
		super(null, null);
		super.setPattern(REGEX);
	}//end MultipleChoiceQuestion
	
	
	/*
	 * Initializes a new MultipleChoiceQuestion with the parameters passed in.
	 * Parameters:
	 * String question - The question prompt
	 * String correctAnswer - The right answer for the question
	 * String[] choices - The list of answer choices for the question
	 */
	
	public MultipleChoiceQuestion(String question, String correctAnswer, String[] choices) 
	{
		super(question, correctAnswer);
		super.setPattern(REGEX);
		super.setChoices(choices);
	}//end MultipleChoiceQuestion
	
	
	/*
	 * Overridden method from the Question superclass to display the
	 * question and its four possible answer choices A-D.
	 */
	
	@Override
	public void printQuestion()
	{		
		String[] choices = this.getChoices();
		String s = "";
		super.printQuestion();
	
		for (int i = 0; i < this.getChoices().length; i ++)
		{
			char letter =  (char)(65 + i);  // ASCII of A = 65
			s += letter + ".) " + choices[i] + "\n";
		}//end for
		
		s += "\nEnter you answer (A-D):\n";
		System.out.println(s);
	}//end printQuestion
	
	
	/*
	 * Returns the answer choice corresponding to the letter passed in.
	 * Parameters:
	 * char letter - The letter choice A, B, C, or D expected
	 * Returns:
	 * String - The answer choice corresponding to the indicated 
	 * letter position in the array (0-A, 1-B, 2-C, 3-D).
	 */
	
	private String getAnswerAtIndex(char letter)
	{	
		String[] choices = this.getChoices();
		int i = letter % 65;
		return choices[i];
	}//end getAnswerAtIndex
		
	
	/*
	 *  Method to compare the user's entered letter choice to the 
	 *  correct.
	 *  Parameters:
	 *  String input - Player's answer to the question
	 *  Returns:
	 *  boolean - Whether the user answered the question correctly or not
	 */
	
	@Override
	public boolean checkCorrectAnswer(String input)
	{
		char ch = input.toUpperCase().charAt(0);
		String answer = getAnswerAtIndex(ch);
		return answer.equalsIgnoreCase(this.getCorrectAnswer());
	}//end checkCorrectAnswer
	
	
	/* 
	 * This overrides the hook for the Question superclass.
	 * Multiple choice questions have the special functionality to have 
	 * the answer choices randomly shuffled.
	 */
	
	@Override
	public void specialFunction()
	{ 
		String [] choices = this.getChoices();
		for (int i = 0; i < choices.length; i ++)
		{
			int j = (int)(Math.random() * choices.length);
			String temp = choices[i];
			choices[i] = choices[j];
			choices[j] = temp;
		}//end for
	}//end specialFunction
	

	/*
	 * Maybe eliminate one or two of the wrong answer choices. 
	 * Notify the user of the wrong choice(s).
	 */
	
	@Override
	public void hint() 
	{
		// TODO Auto-generated method stub	
	}//end hint
	
}//end MultipleChoiceQuestion
