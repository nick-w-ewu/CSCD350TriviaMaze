/**
 *AdminTool.java
 *Author: Nicholas Witmer
 *Revision: 1, Nicholas Witmer
 *Date: 11/28/2015
 *Designed to allow the administrator for the Trivia Maze game to enter new questions into the database.
 *The user is presented with a menu of the question types and once a type is selected the user is 
 *prompted for the different required fields of that question type.
 *
 */

package admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import database_stuff.DatabaseInsert;

public class AdminTool
{
	/*
	 * Starts the admin program and interacts with the user to process selections
	 */
	
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

	/*
	 * Accepts user input for a short answer question and parses it to make sure it is correct
	 * before inserting the question into the database
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * DatabaseInsert insert - a DatabaseInsert object that is connected to the database used to insert
	 * a question once the user has entered all the required fields
	 * 
	 */
	
	private static void insertShortAnswer(Scanner input, DatabaseInsert insert)
	{
		String question, answer, keyWords;
		boolean sucess;
		
		System.out.println("Please enter the question you wish to add to the Triva Maze");
		question = getQuestion(input);
		System.out.println("Please enter the answer to this question");
		answer = input.nextLine();
		while(answer == null || answer.equals(""))
		{
			System.out.println("Please enter the answer to this question");
			answer = input.nextLine();
		}
		keyWords = getKeyWords(input, answer);
		sucess = insert.insertQuestion(question, answer, keyWords);
		if(sucess)
		{
			System.out.println("Question sucessfully inserted into Triva Maze");
		}
		else
		{
			System.out.println("There was an error inserting the question into the Triva Maze");
		}
	}
	
	/*
	 * Prompts the user to enter the keywords for a the short answer question until they enter ],
	 * checking that the user didn't enter a blank string. All these keywords are concatinated into
	 * one keywords String, separated by a comma
	 * question
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * String answer - the correct answer to the question to be added to the key words list
	 * Returns:
	 * String - the keywords entered by the user
	 * 
	 */

	private static String getKeyWords(Scanner input, String answer)
	{
		String keyWords, keyWord;
		
		System.out.println("Please enter the keywords associated with this question");
		keyWords = "" + answer;
		keyWord = input.nextLine();
		while((keyWord == null || keyWord.equals("")) && !keyWord.equals("]"))
		{
			keyWords = keyWords + "," + keyWord;
			System.out.println("Enter another keyword or type ] if you are finished");
			keyWord = input.nextLine();
		}
		return keyWords;
	}

	/*
	 * Prompts the user to enter the question they wish to enter into the game,
	 * checking that the user didn't enter a blank string
	 * question
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * Returns:
	 * String - the question the user entered
	 * 
	 */
	
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

	/*
	 * Accepts user input for a true false question and parses it to make sure it is correct
	 * before inserting the question into the database
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * DatabaseInsert insert - a DatabaseInsert object that is connected to the database used to insert
	 * a question once the user has entered all the required fields
	 * 
	 */
	
	private static void insertTrueFalse(Scanner input, DatabaseInsert insert)
	{
		String pattern = "((?i)[tf])";
		boolean validInput = false, sucess;
		String question, answer;
		
		System.out.println("Please enter the question you wish to add to the Triva Maze");
		question = getQuestion(input);
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
		sucess = insert.insertQuestion(question, answer);
		if(sucess)
		{
			System.out.println("Question sucessfully inserted into Triva Maze");
		}
		else
		{
			System.out.println("There was an error inserting the question into the Triva Maze");
		}
	}
	
	/*
	 * Accepts user input for a multiple choice question and parses it to make sure it is correct
	 * before inserting the question into the database
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * DatabaseInsert insert - a DatabaseInsert object that is connected to the database used to insert
	 * a question once the user has entered all the required fields
	 * 
	 */
	
	private static void insertMultipleChoice(Scanner input, DatabaseInsert insert)
	{
		String question, option1, option2, option3, option4, correctAnswer;
		int answer;
		boolean sucess;
		
		System.out.println("Please enter the question you wish to add to the Triva Maze");
		question = getQuestion(input);
		option1 = getOption("Choice 1", input);
		option2 = getOption("Choice 2", input);
		option3 = getOption("Choice 3", input);
		option4 = getOption("Choice 4", input);
		
		answer = intInput(input, "Please enter the choice which is the correct answer for this question");
		while(answer > 4)
		{
			answer = intInput(input, "Please enter the choice which is the correct answer for this question");
		}
		correctAnswer = getCorrectAnswer(option1, option2, option3, option4, answer);
		sucess = insert.insertQuestion(question, correctAnswer, option1, option2, option3, option4);
		if(sucess)
		{
			System.out.println("Question sucessfully inserted into Triva Maze");
		}
		else
		{
			System.out.println("There was an error inserting the question into the Triva Maze");
		}
	}
	
	/*
	 * Given the number of the option which is the correct answer to a multiple choice question
	 * returns the full string of that option to be stored so the user doesn't have to enter it twice
	 * Parameters:
	 * String option1 - an option for the question
	 * String option2 - an option for the question
	 * String option3 - an option for the question
	 * String option4 - an option for the question
	 * int answer - the user specified option which is the correct answer
	 * Returns;
	 * String - full string of the correct answer
	 * 
	 */
	
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

	/*
	 * Prompts the user to enter the possible answer for the specified choice in a multiple choice,
	 * checking that the user didn't enter a blank string
	 * question
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * String prompt - a message used to prompt the user for input
	 * Returns:
	 * String - the possible answer a
	 * 
	 */
	
	private static String getOption(String prompt, Scanner input)
	{
		String option;
		
		System.out.println("Please enter the possible answer for " + prompt + " of this question");
		option = input.nextLine();
		while(option == null || option.equals(""))
		{
			System.out.println("Please enter the possible answer for " + prompt + " of this question, it cannot be blank");
			option = input.nextLine();
		}
		return option;
	}

	/*
	 * Displays the menu until the user has entered a valid menu choice
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * Returns:
	 * int - the integer the user entered
	 */

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
	
	/*
	 * Prompts the user to enter an integer that is greater then zero, the user is prompted
	 * until they enter a valid integer
	 * Parameters:
	 * Scanner input - a scanner attached to system.in to interact with the user
	 * String prompt - a message used to prompt the user for input
	 * 
	 */
	
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
}//End AdminTool
