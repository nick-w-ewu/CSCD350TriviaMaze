package triviamaze;

/**
 * AllTest.java
 * Author: Jenia Rousseva
 * Revision: 2, Jenia Rousseva
 * Date: 12/6/2015
 * This class runs all of the JUnit test case files.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MultipleChoiceQuestionTest.class,
		ShortAnswerQuestionTest.class, TrueFalseQuestionTest.class, MazeTest.class})

public class AllTests {

}//end AllTests