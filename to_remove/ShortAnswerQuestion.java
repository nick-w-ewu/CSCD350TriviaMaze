package triviamaze;

/**
 * ShortAnswerQuestion.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/09/2015
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
	String [] keywords; // acceptable answer choices; this can be changed to only one keyword
	String hint; // may use a special object
	
	public ShortAnswerQuestion() 
	{
		super(null, null);
	}
	
	public ShortAnswerQuestion(String question, String correctAnswer, String[] keywords)
	{
		super(question, correctAnswer);
		this.keywords = keywords;
	}

	public String[] getKeywords() 
	{
		return keywords;
	}

	public void setKeywords(String[] keywords)
	{
		this.keywords = keywords;
	}

	public String getHint() 
	{
		return hint;
	}

	public void setHint(String hint) 
	{
		this.hint = hint;
	}

	@Override
	public void printQuestion()
	{		
		super.printQuestion();
		System.out.println("\n\nEnter your answer\n");
	}
	
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
	}

	@Override
	public void hint() 
	{
		System.out.println("Hint: " + this.hint);
	}
	
	/*
	 *  Method to compare the user's input to the keyword(s) 
	 *  using regex expressions.
	 */
	public boolean compareAnswer(String input)
	{
		return false;
	}

}//end ShortAnswerQuestion
