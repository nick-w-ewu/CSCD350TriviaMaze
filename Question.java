package triviamaze;

import java.util.Scanner;

/**
 * Question.java
 * Author: Jenia Rousseva
 * Revision: 3, Nick Witmer
 * Date: 11/22/2015
 * This file provides the implementation of an abstract class for a generic
 * question. All questions must have a question itself and a correct answer.
 * The class also contains an abstract method called hint.
 * Reference:
 * 
 * https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
 */

public abstract class Question 
{
	private String question;
	private String correctAnswer;
	private String pattern;  // a regex pattern for input validation
	private String[] choices;
	private String hint;
	private boolean error;//indicates if there was an error with the question, should be checked before using the question
	
	
	/*
	 * Initializes a new Question with the parameters passed in.
	 * Parameters:
	 * String question - Assigned to the Question's question field
	 * String correctAnswer - The correct answer for the question assigned
	 * to the Question's correctAnswer field
	 */
	
	public Question(String question, String correctAnswer)
	{
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.pattern = "(.*?)"; //matches anything
		this.error = false;
	}//end Question
	
	
	/*
	 * Returns the actual question.
	 * Returns:
	 * String - The question field for the Question
	 */
	
	public String getQuestion() 
	{
		return question;
	}//end getQuestion
	
	
	/*
	 * Set a new question for the question field.
	 * Parameters:
	 * String question - A question prompt
	 */
	
	public void setQuestion(String question) 
	{
		this.question = question;
	}//end setQuestion
	
	
	/*
	 * Return the correct answer for the question.
	 * Returns:
	 * String - The Question's correctAnswer field
	 */
	
	public String getCorrectAnswer() 
	{
		return correctAnswer;
	}//end correctAnswer
	
	
	/*
	 * Sets the Question's correctAnswer field.
	 * Parameters:
	 * String correctAnswer - The correct answer for the Question
	 */
	
	public void setCorrectAnswer(String correctAnswer) 
	{
		this.correctAnswer = correctAnswer;
	}//end setCorrectAnswer
	
	
	/*
	 * Set the regex pattern to be used for validating user input.
	 * Parameters: 
	 * String pattern - The specific regex pattern for that question type.
	 */
	
	protected void setPattern(String pattern)
	{
		this.pattern = pattern;
	}//end setPattern
	
	
	/*
	 * Sets the choices field for the Question.
	 * Parameters:
	 * String[] choices - Either the answer choices (for a multiple
	 * choice question) or the keywords (for the a short answer question)
	 */
	
	public void setChoices(String[] choices)
	{
		this.choices = choices;
	}//end setChoices
	
	
	/*
	 * Returns the array of choices for the Question
	 * Returns:
	 * String [] - The question choices field 
	 */
	
	public String[] getChoices()
	{
		return this.choices;
	}//end getChoices
	
	
	/*
	 * Set the Question's hint field.
	 * Parameters:
	 * String hint - The hint for the question
	 */
	
	public void setHint(String hint)
	{
		this.hint = hint;
	}//end setHint
	
	
	/*
	 * Return the Question's hint field.
	 * Returns:
	 * String - The hint field
	 */
	
	public String getHint()
	{
		return this.hint;
	}//end getHint
	
	/*
	 * Set the Question's error field.
	 * Parameters:
	 * Boolean error - the error status for the question
	 */
	
	public void setError(boolean error)
	{
		this.error = error;
	}
	
	/*
	 * Return the Question's error field.
	 * Returns:
	 * Boolean - the error field
	 */
	
	public boolean getError()
	{
		return this.error;
	}
	
	/*
	 * Check if the answer passed in is the correct answer, ignoring 
	 * case. This method may be overridden in the subclasses for more
	 * specific functionality.
	 * Parameters:
	 * String answer - An answer provided by the player
	 * Returns:
	 * boolean - Whether the passed in answer is correct or not
	 */
	
	public boolean checkCorrectAnswer(String answer)
	{
		return answer.equalsIgnoreCase(this.correctAnswer);
	}//end checkCorrectAnswer
	
	
	/*
	 * This is a hook to print the question field with instructions to
	 * how to answer the specific question type. May be overridden in 
	 * the subclasses.
	 */
			
	public void printQuestion()
	{	
		System.out.println(this.question);
	}//end printQuestion
	
	
	/*
	 * A method to scrub the user input for valid answer choices 
	 * based on the regex expression. May be overridden in the 
	 * subclasses.
	 * Returns:
	 * String - A valid input from the user for that question type
	 */
	
	public String getValidInput()
	{
		Scanner sc = new Scanner(System.in);
		String answer = null;
		boolean validInput = false;
	
		do 
		{
			answer = sc.next();
			answer.trim();

			if (answer.matches(this.pattern))
				validInput = true;			
			else
				System.out.println("Invalid input. Try again.");
		} while (!validInput);	
		
		//sc.close();
		return answer;
	}//end getValidInput

	
	/*
	 * This is a hook for any specific functionality for a question
	 * not described above to be implemented as desired in the subclasses.
	 */
	
	public void specialFunction(){}
	
	
	/*
	 * A abstract method for giving the player a hint for that 
	 * specific question. 
	 */
	
	public abstract void hint();
	
}//end Question
