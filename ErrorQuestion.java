//package triviamaze;

/**
 * ErrorQuestion.java
 * Author: Nick Witmer
 * Revision: 1, Nick Witmer
 * Date: 11/22/2015
 * This file provides the implementation of an errorquestion used to represent when there was an error
 * retrieving a question from the database
 */

public class ErrorQuestion extends Question
{
	/*
	 * Initializes a new question with the default values. 
	 * Calls superclass constructor and set the error to true
	 */
	public ErrorQuestion()
	{
		super(null, null);
		this.setError(true);
	}

	//not used because the errorquestion has no other data in it and it wouldn't have a hint
	@Override
	public void hint()
	{
		// TODO Auto-generated method stub
		
	}
}
