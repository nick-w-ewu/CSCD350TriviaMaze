package database_stuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTests
{
	DatabaseUtility test;
	
	@Before
	public void setUp()
	{
		test = new DatabaseUtility("questions.db");
	}
	
	@Test
	public void testDatabaseConnect()
	{
		DatabaseUtility db1 = new DatabaseUtility("test");
		assertTrue(test.connectDatabase());
		assertFalse(db1.connectDatabase());
	}

}
