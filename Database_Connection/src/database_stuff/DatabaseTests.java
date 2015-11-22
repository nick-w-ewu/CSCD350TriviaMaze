package database_stuff;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import questions.Question;

public class DatabaseTests
{
	
	DatabaseUtility test;
	int id = 1;
	
	@Before
	public void setUp()
	{
		test = new DatabaseUtility();
	}
	
	@Test
	public void testInsertTrueFalse()
	{
		assertTrue(test.insertQuestion("Will it rain today", 1));
		assertFalse(test.insertQuestion(null, 0));
	}
	
	@Test
	public void testInsertShortAnswer()
	{
		assertTrue(test.insertQuestion("Will it rain today", "Maybe, it could", "maybe, probably"));
		assertFalse(test.insertQuestion("What is my name", null, null));
	}
	
	@Test
	public void testInsertMultipleChoice()
	{
		assertTrue(test.insertQuestion("How many children are in Mary Poppins", 2, "55", "2", "7", "25"));
		assertFalse(test.insertQuestion("What is my name", 3, "Nick", "Fish", null, "Nothing"));
	}
	
	@Test
	public void testInsertHint()
	{
		assertTrue(test.insertHint(id++, "shortanswer", "its possible"));
		assertFalse(test.insertHint(8, null, null));
	}
	
	@Test
	public void testretrieveQuestions()
	{
		Question q1 = test.retrieveQuestion("shortanswer");
		Question q2 = test.retrieveQuestion("truefalse");
		Question q3 = test.retrieveQuestion("multiplechoice");
		assertEquals("t", q2.getCorrectAnswer());
		
	}

	
}
