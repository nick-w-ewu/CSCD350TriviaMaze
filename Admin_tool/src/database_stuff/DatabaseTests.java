/**
 *DatabaseTests.java
 *Author: Nicholas Witmer
 *Revision: 1, Nicholas Witmer
 *Date: 11/28/2015
 *Unit tests for functionality provided by DatabaseInsert.java
 *
 */

package database_stuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTests
{
	DatabaseInsert test1;
	
	@Before
	public void setUp()
	{
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
}//end DatabaseTests
