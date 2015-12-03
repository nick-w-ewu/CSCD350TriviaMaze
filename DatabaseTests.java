package triviamaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	
	//Will fail on first run if the database is empty
	@Test
	public void testretrieveQuestions()
	{
		Question q1 = test.retrieveQuestion("shortanswer");
		Question q2 = test.retrieveQuestion("truefalse");
		Question q3 = test.retrieveQuestion("multiplechoice");
		assertFalse(q1.getError());
		assertFalse(q2.getError());
		assertFalse(q3.getError());
	}
	
	@Test
	public void testResetFlags()
	{
		assertTrue(test.resetFlags("multiplechoice"));
		assertTrue(test.resetFlags("shortanswer"));
		assertTrue(test.resetFlags("truefalse"));
		assertFalse(test.resetFlags("test"));
	}

	
}
