package triviamaze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TrueFalseQuestionTest.java
 * Author: Jenia Rousseva
 * Revision: 1, Jenia Rousseva
 * Date: 11/19/2015
 * This file provides unit tests for true/false questions.
 */

public class TrueFalseQuestionTest 
{
	Question test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}
	

	@Before
	public void setUp() throws Exception 
	{
		test = new TrueFalseQuestion();
		test.setQuestion("The length of a year on earth is 365 days.");
		test.setCorrectAnswer("F");
	}//end setUp
	

	@After
	public void tearDown() throws Exception {}
	

	@Test
	public void testGetQuestion() 
	{
		assertEquals("The length of a year on earth is 365 days.", test.getQuestion());
	}//end testGetQuestion
	

	@Test
	public void testGetCorrectAnswer() 
	{
		assertEquals("F", test.getCorrectAnswer());
	}//end testGetCorrectAnswer
	

	@Test
	public void testCheckCorrectAnswer() 
	{
		assertEquals(true, test.checkCorrectAnswer("f"));
		assertEquals(true, test.checkCorrectAnswer("F"));
		assertEquals(false, test.checkCorrectAnswer("false"));
		assertEquals(false, test.checkCorrectAnswer("T"));
	}//end testCheckCorrectAnswer

}//end TrueFalseQuestionTest
