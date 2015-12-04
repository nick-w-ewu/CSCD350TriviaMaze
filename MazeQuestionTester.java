//package triviamaze;

public class MazeQuestionTester {

	public MazeQuestionTester() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String [] args)
	{
		DatabaseUtility db = new DatabaseUtility();
		Maze m = new Maze(3, 3);
		QuestionHandler qh = new QuestionHandler();
		Question question;
		
		boolean isQuestionCell = false;
		String questionType = "";
		
		do
		{
			m.print();
			m.getDirection();
			isQuestionCell = m.getLandOnQuestion();
			
			if(isQuestionCell)
			{
				questionType = m.getQuestionType();
				
				try
				{
					question = qh.checkFlagsReset(questionType, db);
					qh.handleQuestion(question, m); //also updates the maze
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}//end if
			
		} while(m.pathExists() && !m.getIsEnd());
	}// end main

}//end MazeQuestionTester
