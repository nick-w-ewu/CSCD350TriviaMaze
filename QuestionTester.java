//package triviamaze;

public class QuestionTester 
{
	public static void main(String[] args) 
	{
		/* To test a short answer question */
		String question = "Name one color present in French flag.";
		String answer = "blue"; //Not necessarily the best answer in this case
		String[] keywords = {"red", "white", "blue"};
		
		Question q = new ShortAnswerQuestion(question, answer, keywords);
		
		/* These are all acceptable answers */
		String a1 = "red";
		String a2 = "WHITE";
		String a3 = "A color is blue.";
		String a4 = "Red is on the French flag";
		
		System.out.println("Answer: " + a1 + " is " + q.checkCorrectAnswer(a1) + "\n");
		System.out.println("Answer: " + a2 + " is " + q.checkCorrectAnswer(a2) + "\n");
		System.out.println("Answer: " + a3 + " is " + q.checkCorrectAnswer(a3) + "\n");
		System.out.println("Answer: " + a4 + " is " + q.checkCorrectAnswer(a4) + "\n");
		
		/* These are unacceptable answers */
		String a5 = "RRED is there!";
		String a6 = "b lue";
		String a7 = "Whiite";
		String a8 = " w@hite";
		
		System.out.println("Answer: " + a5 + " is " + q.checkCorrectAnswer(a5) + "\n");
		System.out.println("Answer: " + a6 + " is " + q.checkCorrectAnswer(a6) + "\n");
		System.out.println("Answer: " + a7 + " is " + q.checkCorrectAnswer(a7) + "\n");
		System.out.println("Answer: " + a8 + " is " + q.checkCorrectAnswer(a8) + "\n");
		
	}

}
