package questions;

import java.util.Scanner;

/**
 * Question.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/09/2015
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
	
	public Question(String question, String correctAnswer)
	{
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.pattern = "(.*?)"; //matches anything
	}
	
	public String getQuestion() 
	{
		return question;
	}
	
	public void setQuestion(String question) 
	{
		this.question = question;
	}
	
	public String getCorrectAnswer() 
	{
		return correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) 
	{
		this.correctAnswer = correctAnswer;
	}
	
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
		
	public boolean checkCorrectAnswer(String answer)
	{
		return answer.equalsIgnoreCase(this.correctAnswer);
	}
			
	public void printQuestion()
	{	
		System.out.println(this.question);
	}
	
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
		
		sc.close();
		return answer;
	}
	
	public abstract void hint();
	
}//end Question
