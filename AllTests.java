package triviamaze;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MultipleChoiceQuestionTest.class,
		ShortAnswerQuestionTest.class, TrueFalseQuestionTest.class })
public class AllTests {

}
