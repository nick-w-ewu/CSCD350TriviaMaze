package admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import database_stuff.DatabaseInsert;

public class AdminTool
{
	
	public static void main(String[] args)
	{
		DatabaseInsert insert = new DatabaseInsert();
		Scanner input = new Scanner(System.in);
		int userSelection = displayMenu(input);
		System.out.println(userSelection);
		while(userSelection != 4)
		{
			switch(userSelection)
			{
				case 1:
					insertTrueFalse(input, insert);
					break;
				case 2:
					insertShortAnswer(input, insert);
					break;
				case 3:
					insertMultipleChoice(input, insert);
					break;
			}
			userSelection = displayMenu(input);
		}
		System.out.println("Thank You for using the Triva Maze Admin Tool, we are not exiting...");
	}

	private static void insertShortAnswer(Scanner input, DatabaseInsert insert)
	{
		System.out.println("Please enter the question you wish to add to the Triva Maze");
		String question = getQuestion(input);
		System.out.println("Please enter the answer to this question");
		String answer = input.nextLine();
		while(answer == null || answer.equals(""))
		{
			System.out.println("Please enter the answer to this question");
			answer = input.nextLine();
		}
		String keyWords = getKeyWords(input, answer);
		boolean sucess = insert.insertQuestion(question, answer, keyWords);
		if(sucess)
		{
			System.out.println("Question sucessfully inserted into Triva Maze");
		}
		else
		{
			System.out.println("There was an error inserting the question into the Triva Maze");
		}
	}

	private static String getKeyWords(Scanner input, String answer)
	{
		System.out.println("Please enter the keywords associated with this question");
		String keyWords = "" + answer;
		String keyWord = input.nextLine();
		while((keyWord == null || keyWord.equals("")) && !keyWord.equals("]"))
		{
			keyWords = keyWords + "," + keyWord;
			System.out.println("Enter another keyword or type ] if you are finished");
			keyWord = input.nextLine();
		}
		return keyWords;
	}

	private static String getQuestion(Scanner input)
	{
		String question = input.nextLine();
		while(question == null || question.equals(""))
		{
			System.out.println("Please enter the question you wish to add to the Triva Maze, it cannot be blank");
			question = input.nextLine();
		}
		return question;
	}

	private static void insertTrueFalse(Scanner input, DatabaseInsert insert)
	{
		String pattern = "((?i)[tf])";
		System.out.println("Please enter the question you wish to add to the Triva Maze");
		boolean validInput = false;
		String question = getQuestion(input);
		String answer;
		System.out.println("Please enter the answer to this question, t or f");
		do 
		{
			answer = input.next();
			answer.trim();

			if (answer.matches(pattern))
				validInput = true;			
			else
				System.out.println("Invalid input, must be t or f. Try again.");
		} while (!validInput);
		boolean sucess = insert.insertQuestion(question, answer);
		if(sucess)
		{
			System.out.println("Question sucessfully inserted into Triva Maze");
		}
		else
		{
			System.out.println("There was an error inserting the question into the Triva Maze");
		}
	}
	
	private static void insertMultipleChoice(Scanner input, DatabaseInsert insert)
	{
		System.out.println("Please enter the question you wish to add to the Triva Maze");
		boolean validInput = false;
		String question = getQuestion(input);
		String option1 = getOption("Choice 1", input);
		String option2 = getOption("Choice 2", input);
		String option3 = getOption("Choice 3", input);
		String option4 = getOption("Choice 4", input);
		
		int answer = intInput(input, "Please enter the choice which is the correct answer for this question");
		while(answer > 4)
		{
			answer = intInput(input, "Please enter the choice which is the correct answer for this question");
		}
		String correctAnswer = getCorrectAnswer(option1, option2, option3, option4, answer);
		
		boolean sucess = insert.insertQuestion(question, correctAnswer, option1, option2, option3, option4);
		if(sucess)
		{
			System.out.println("Question sucessfully inserted into Triva Maze");
		}
		else
		{
			System.out.println("There was an error inserting the question into the Triva Maze");
		}
	}
	
	private static String getCorrectAnswer(String option1, String option2, String option3, String option4, int answer)
	{
		switch(answer)
		{
			case 1:
				return option1;
			case 2:
				return option2;
			case 3:
				return option3;
		}
		return option4;
	}

	private static String getOption(String prompt, Scanner input)
	{
		System.out.println("Please enter the possible answer for " + prompt + " of this question");
		String option = input.nextLine();
		while(option == null || option.equals(""))
		{
			System.out.println("Please enter the possible answer for " + prompt + " of this question, it cannot be blank");
			option = input.nextLine();
		}
		return option;
	}


	private static int displayMenu(Scanner input)
	{
		int userSelection = 0;
		String prompt = "Please select a question type from the list above:";
		
		while (!(userSelection == 1 || userSelection == 2 || userSelection == 3 || userSelection == 4))
		{
			System.out.println(prompt);
			System.out.println("1). True False");
			System.out.println("2). Short Answer");
			System.out.println("3). Multiple Choice");
			System.out.println("4). Exit");
			
			userSelection = intInput(input, prompt);
			if (!(userSelection == 1 || userSelection == 2 || userSelection == 3 || userSelection == 4))
			{
				System.out.println("That is not a valid choice for inserting a question, please try again.\n");
			}
		}
		return userSelection;
	}
	
	private static int intInput(Scanner input, String prompt)
	{
		int ui;
		while (true)
		{
			try
			{
				System.out.println(prompt);
				ui = input.nextInt();
				if (ui <= 0)
				{
					System.out.println("Negative numbers and zero are not accepted as input by this program.");
					throw new InputMismatchException();
				}
				return ui;
			}
			catch (Exception e)
			{
				System.out.println("There was an error with the input please try again.");
				input.nextLine();
			}
		}
	}
}
