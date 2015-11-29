package database_stuff;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DatabaseTests
{
	DatabaseInsert test1;
	int id = 20;
	
	@Before
	public void setUp()
	{
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
}
