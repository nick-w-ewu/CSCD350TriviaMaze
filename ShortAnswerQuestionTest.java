package triviamaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * ShortAnswerQuestionTest.java
 * Author: Jenia Rousseva
 * Revision: 1, Jenia Rousseva
 * Date: 11/19/2015
 * This file provides unit tests for short answer questions.
 */

public class ShortAnswerQuestionTest 
{
	Question test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	
	/*
	 * Set up a sample short answer question.
	 */
	
	@Before
	public void setUp() throws Exception 
	{
		test = new ShortAnswerQuestion();
		test.setQuestion("Name one color present on the French flag.");
		test.setCorrectAnswer("blue");
		test.setChoices(new String[] {"red", "white", "blue"});
	}//end setUp
	

	@After
	public void tearDown() throws Exception {}

	
	/*
	 * Test retrieving the question prompt.
	 */
	
	@Test
	public void testGetQuestion() 
	{
		assertEquals("Name one color present on the French flag.", test.getQuestion());
	}//end testGetQuestion

	
    /* 
     * Test retrieving the correct answer to the question.
     */

	@Test
	public void testGetCorrectAnswer() 
	{
		assertEquals("blue", test.getCorrectAnswer());
	}//end testGetCorrectAnswer
	
	
	/*
	 * Test retrieving the keywords from the array.
	 */
	
	@Test
	public void testGetKeywords() 
	{
		assertEquals("red", test.getChoices()[0]);
		assertEquals("white", test.getChoices()[1]);
		assertEquals("blue", test.getChoices()[2]);
	}//end testGetKeywords
	
	
	/*
	 * Test checking if the answer provided by the player is correct.
	 */
	
	@Test
	public void testCheckCorrectAnswer() 
	{
		/* These are acceptable answers */
		String a1 = "red";
		String a2 = "WHITE";
		String a3 = "A color is blue.";
		String a4 = "Red is on the French flag";
		
		assertEquals(true, test.checkCorrectAnswer(a1));
		assertEquals(true, test.checkCorrectAnswer(a2));
		assertEquals(true, test.checkCorrectAnswer(a3));
		assertEquals(true, test.checkCorrectAnswer(a4));
		
		/* These are unacceptable answers */
		String a5 = "RRED is there!";
		String a6 = "b lue";
		String a7 = "Whiite";
		String a8 = " w@hite";
		
		assertEquals(false, test.checkCorrectAnswer(a5));
		assertEquals(false, test.checkCorrectAnswer(a6));
		assertEquals(false, test.checkCorrectAnswer(a7));
		assertEquals(false, test.checkCorrectAnswer(a8));	
	}//end testCheckCorrectAnswer

	
//	@Test
//	public void testHint() 
//	{
//		fail("Not yet implemented"); // TODO
//	}

}//end ShortAnswerQuestionTest
