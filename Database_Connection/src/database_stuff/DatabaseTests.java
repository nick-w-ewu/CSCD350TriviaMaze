/**
 *DatabaseTests.java
 *Author: Nicholas Witmer
 *Revision: 1, Nicholas Witmer
 *Date: 11/28/2015
 *Unit tests for functionality provided by DatabaseInsert.java and DatabaseUtility.java
 *
 */

package database_stuff;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import questions.Question;

public class DatabaseTests
{
	
	DatabaseUtility test;
	DatabaseInsert test1;
	int id = 30;
	
	@Before
	public void setUp()
	{
		test = new DatabaseUtility();
		test1 = new DatabaseInsert();
	}
	
	/*
	 * Tests the insertion of a true false question using the DatabaseInsert insertQuestion() method
	 */
	
	@Test
	public void testInsertTrueFalse()
	{
		assertTrue(test1.insertQuestion("Will it rain today", "t"));
		assertFalse(test1.insertQuestion(null, "t"));
	}
	
	/*
	 * Tests the insertion of a short question using the DatabaseInsert insertQuestion() method
	 */
	
	@Test
	public void testInsertShortAnswer()
	{
		assertTrue(test1.insertQuestion("Will it rain today", "Maybe, it could", "maybe,probably"));
		assertFalse(test1.insertQuestion("What is my name", null, null));
	}
	
	/*
	 * Tests the insertion of a multiple choice question using the DatabaseInsert insertQuestion() method
	 */
	
	@Test
	public void testInsertMultipleChoice()
	{
		assertTrue(test1.insertQuestion("How many children are in Mary Poppins", "2", "55", "2", "7", "25"));
		assertFalse(test1.insertQuestion("What is my name", "3", "Nick", "Fish", null, "Nothing"));
	}
	
	/*
	 * Tests resetting the answered flags in the database for various question types with the resetFlags() method in DatabaseUtility.java
	 */
	
	@Test
	public void testResetFlags()
	{
		assertTrue(test.resetFlags("multiplechoice"));
		assertTrue(test.resetFlags("shortanswer"));
		assertTrue(test.resetFlags("truefalse"));
		assertFalse(test.resetFlags("test"));
	}
}//End DatabaseTests
