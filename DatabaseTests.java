package triviamaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import questions.Question;

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
	
	@Test
	public void testInsertTrueFalse()
	{
		assertTrue(test1.insertQuestion("Will it rain today", "t"));
		assertFalse(test1.insertQuestion(null, "t"));
	}
	
	@Test
	public void testInsertShortAnswer()
	{
		assertTrue(test1.insertQuestion("Will it rain today", "Maybe, it could", "maybe,probably"));
		assertFalse(test1.insertQuestion("What is my name", null, null));
	}
	
	@Test
	public void testInsertMultipleChoice()
	{
		assertTrue(test1.insertQuestion("How many children are in Mary Poppins", "2", "55", "2", "7", "25"));
		assertFalse(test1.insertQuestion("What is my name", "3", "Nick", "Fish", null, "Nothing"));
	}
	
	@Test
	public void testInsertHint()
	{
		assertTrue(test1.insertHint(id++, "shortanswer", "its possible"));
		assertFalse(test1.insertHint(8, null, null));
	}
	
	//Will fail on first run if the database is empty
	@Test
	public void testretrieveQuestions()
	{
		Question q1 = test.retrieveQuestion("shortanswer");
		Question q2 = test.retrieveQuestion("truefalse");
		Question q3 = test.retrieveQuestion("multiplechoice");
		String[] choices = {"maybe", "probably"};
		assertArrayEquals(choices, q1.getChoices());
		assertEquals("Will it rain today", q1.getQuestion());
		assertEquals("Maybe, it could", q1.getCorrectAnswer());
		
		assertEquals("t", q2.getCorrectAnswer());
		assertEquals("Will it rain today", q2.getQuestion());
		
		String[] options = {"55", "2", "7", "25"};
		assertEquals("How many children are in Mary Poppins", q3.getQuestion());
		assertEquals("2", q3.getCorrectAnswer());
		assertArrayEquals(options, q3.getChoices());
	}
	
	@Test
	public void testResetFlags()
	{
		assertTrue(test.resetAllFlags());
	}

	
}
