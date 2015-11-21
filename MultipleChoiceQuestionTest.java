package triviamaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MultipleChoiceQuestionTest.java
 * Author: Jenia Rousseva
 * Revision: 1, Jenia Rousseva
 * Date: 11/19/2015
 * This file provides unit tests for multiple choice questions.
 */

public class MultipleChoiceQuestionTest 
{
	Question test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	
	@Before
	public void setUp() throws Exception 
	{
		test = new MultipleChoiceQuestion();
		test.setQuestion("What is the capital of Washington state?");
		test.setCorrectAnswer("Olympia");
		test.setChoices(new String[] {"Seattle", "Olympia", "Spokane", "Washington DC"});
	}//end setUp

	
	@After
	public void tearDown() throws Exception {}
	
	
	@Test
	public void testGetQuestion() 
	{
		assertEquals("What is the capital of Washington state?", test.getQuestion());
	}//end testGetQuestion
	
	
	@Test
	public void testGetCorrectAnswer() 
	{
		assertEquals("Olympia", test.getCorrectAnswer());
	}//end testGetCorrectAnswer
	
	
	@Test
	public void testGetChoices() 
	{
		assertEquals("Seattle", test.getChoices()[0]);
		assertEquals("Olympia", test.getChoices()[1]);
		assertEquals("Spokane", test.getChoices()[2]);
		assertEquals("Washington DC", test.getChoices()[3]);
	}//end testGetChoices

	
	@Test
	public void testCheckCorrectAnswer() 
	{
		/* These are acceptable answers */
		String a1 = "B";
		String a2 = "b";
		
		assertEquals(true, test.checkCorrectAnswer(a1));
		assertEquals(true, test.checkCorrectAnswer(a2));
		
		/* These are unacceptable answers */
		String a3 = "a";
		String a4 = "C";
		
		assertEquals(false, test.checkCorrectAnswer(a3));
		assertEquals(false, test.checkCorrectAnswer(a4));	
	}//end testCheckCorrectAnswer
	
//	@Test
//	public void testHint() 
//	{
//		fail("Not yet implemented"); // TODO
//	}
	
}//end MultipleChoiceQuestionTest
