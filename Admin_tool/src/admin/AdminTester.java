/**
 *AdminTester.java
 *Author: Nicholas Witmer
 *Revision: 1, Nicholas Witmer
 *Date: 12/6/2015
 *Tests the functionality of the AdminTool with unit tests for various methods of that class 
 *
 */

package admin;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database_stuff.DatabaseInsert;

public class AdminTester
{
	static DatabaseInsert database;
	Scanner test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		database = new DatabaseInsert();
	}
	
	@Before
	public void setUp()
	{
		File fin = new File("test.txt");
		try
		{
			test = new Scanner(fin);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown()
	{
		test.close();
	}

	/*
	 * Tests accepting integer input from the user with the intInput() method
	 */
	
	@Test
	public void testIntInput()
	{
		assertEquals(6, AdminTool.intInput(test, "Get Test input"));
	}
	
	/*
	 * Tests displaying the menu and asking a user to select an option from it using the displayMenu() and intInput() functions
	 */
	
	@Test
	public void testDisplayMenu()
	{
		assertEquals(2, AdminTool.displayMenu(test));
	}
	
	/*
	 * Tests accepting input for a multiple choice question option with the getOption() method
	 */
	
	@Test
	public void testGetOption()
	{
		assertEquals("Far far away", AdminTool.getOption("Get Test input", test));
	}
	
	/*
	 * Tests the getCorrectAnswer() method
	 */
	
	@Test
	public void testGetCorrectAnswer()
	{
		assertEquals("two", AdminTool.getCorrectAnswer("one", "two", "three", "four", 2));
	}
	
	/*
	 * Tests accepting input for the actual question with the getQuestion() method
	 */
	
	@Test
	public void testGetQuestion()
	{
		assertEquals("Far far away", AdminTool.getQuestion(test));
	}
	
	/*
	 * Tests accepting input for a short answer keywords with the getKeyWords() method
	 */
	
	@Test
	public void testGetKeyWords()
	{
		assertEquals("test,Far far away,f,behind the word mountains,far from the countries,Vokalia and Consonantia", AdminTool.getKeyWords(test, "test"));
	}
	
	/*
	 * Tests accepting and processing input for inserting a short answer question
	 */
	
	@Test
	public void testInsertShortAnwer()
	{
		assertTrue(AdminTool.insertShortAnswer(test, database));
	}
	
	/*
	 * Tests accepting and processing input for inserting a true false question
	 */
	
	@Test
	public void testInsertTrueFalse()
	{
		assertTrue(AdminTool.insertTrueFalse(test, database));
	}
	
	/*
	 * Tests accepting and processing input for inserting a multiple choice question
	 */
	
	@Test
	public void testInsertMultipleChoice()
	{
		assertTrue(AdminTool.insertMultipleChoice(test, database));
	}
}//end AdminTester
