package triviamaze;

/**
 * MultipleChoiceQuestion.java
 * Author: Jenia Rousseva
 * Revision: N/A
 * Date: 11/09/2015
 * This file provides the implementation of a multiple choice question object.
 * These types of questions differ from other types in that it contains an 
 * array for the four possible answer choices. There is also a method to
 * shuffle the answer choices, so that they are displayed in random order
 * to the user each time.
 */

public class MultipleChoiceQuestion extends Question
{
	private String[] choices; // a special object for an answer choice may be used here
	
	public MultipleChoiceQuestion()
	{
		super(null, null);
		super.setPattern("((?i)[abcd])");
	}
	
	public MultipleChoiceQuestion(String question, String correctAnswer, String[] choices) 
	{
		super(question, correctAnswer);
		this.choices = choices;
		super.setPattern("((?i)[abcd])");
	}
	
	public String[] getChoices() 
	{
		return choices;
	}

	public void setChoices(String[] choices) 
	{
		this.choices = choices;
	}
	
	/* Random shuffle of the answer choices. */
	public void shuffleAnswers()
	{ 
		for (int i = 0; i < this.choices.length; i ++)
		{
			int j = (int)(Math.random() * this.choices.length);
			String temp = this.choices[i];
			this.choices[i] = this.choices[j];
			this.choices[j] = temp;
		}
	}
	
	@Override
	public void printQuestion()
	{		
		String s = "";
		super.printQuestion();
	
		for (int i = 0; i < this.getChoices().length; i ++)
		{
			char letter =  (char)(65 + i);  // ASCII of A = 65
			s += letter + ".) " + this.choices[i] + "\n";
		}
		s += "\nEnter you answer (A-D):\n";
		System.out.println(s);
	}
	
	public String getAnswerAtIndex(char letter)
	{	
		int i = letter % 65;
		return this.choices[i];
	}

	/*
	 * Maybe eliminate one or two of the wrong answer choices. 
	 * Notify the user of the wrong choice(s).
	 */
	@Override
	public void hint() 
	{
		// TODO Auto-generated method stub	
	}

}//end MultipleChoiceQuestion
