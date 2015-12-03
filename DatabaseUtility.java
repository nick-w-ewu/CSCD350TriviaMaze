/**
 *DatabaseUtility.java
 *Author: Nicholas Witmer
 *Revision: 4, Nicholas Witmer
 *Date: 11/30/2015
 *Utility to connect to a specified SQLite database and provides functionality to retrieve a random question given the type from the database
 *
 */

package triviamaze;
import java.sql.*;


public class DatabaseUtility
{
	private Connection conn;
	private PreparedStatement stmt;
	private PreparedStatement updateStmt;
	private final String TRUEFALSE = "truefalse";
	private final String SHORTANSWER = "shortanswer";
	private final String MULTIPLECHOICE = "multiplechoice";
	private final String ERROR = "error";

	/*
	 * Initializes a new DatabaseUtility and connects to the questions database
	 */

	public DatabaseUtility()
	{
		connectDatabase();
	}

	/*
	 * Attempts to connect to the database questions.db
	 * Returns:
	 * boolean - true if the connection is successful and false if there is an error
	 */

	private boolean connectDatabase()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:questions2.db");

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/*
	 * Retrieves a random question from the database given a question type
	 * Parameters:
	 * String type - the type of question to retrieve
	 * Returns:
	 * Question - returns a randomly selected question of the type specified if the retrieval was successful and returns an ErrorQuestion if the retrieval was unsuccessful
	 */

	public Question retrieveQuestion(String type)
	{
		String sql = "";
		Question toReturn = null;
		QuestionFactory factory = new QuestionFactory();
		switch(type.toLowerCase())
		{
		case("truefalse"):
			sql = "select * from truefalse where answered = 0 ORDER BY RANDOM() LIMIT 1;";
		break;
		case("multiplechoice"):
			sql = "select * from multiplechoice where answered = 0 ORDER BY RANDOM() LIMIT 1;";
		break;
		case("shortanswer"):
			sql = "select * from shortanswer where answered = 0 ORDER BY RANDOM() LIMIT 1;";
		break;
		default:
			throw new IllegalArgumentException("You have provided an invalid question type");
		}
		try
		{
			this.stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();

			switch(type.toLowerCase())
			{
			case("truefalse"):
				toReturn = processTrueFalse(results, factory);
			break;
			case("multiplechoice"):
				toReturn = processMultipleChoice(results, factory);
			break;
			case("shortanswer"):
				toReturn = processShortAnswer(results, factory);
			break;
			}
			return toReturn;
		}
		catch (SQLException e)
		{
			return factory.createQuestion(ERROR);
		}
		finally
		{
			try
			{
				this.stmt.close();
			} 
			catch (SQLException e1)
			{
				
			}
		}
	}

	/*
	 * Processes the ResultSet for a truefalse question retrieved from the database
	 * Parameters:
	 * ResultSet results - the result set returned from the select query in retrieveQuestion()
	 * QuestionFactory factory - a QuestionFactory to create the question to be returned
	 * Returns:
	 * Question - returns a Question containing the data retrieved from the database if successful and returns an ErrorQuestion if the retrieval was unsuccessful
	 */

	private Question processTrueFalse(ResultSet results, QuestionFactory factory)
	{
		try
		{
			int id = results.getInt("question_id");
			String question = results.getString("question");
			String answer = results.getString("answer");

			setAnswered(id, TRUEFALSE);
			Question newQuestion = factory.createQuestion(TRUEFALSE);
			newQuestion.setCorrectAnswer(answer);
			newQuestion.setQuestion(question);
			return newQuestion;
		} 
		catch (SQLException e)
		{
			return factory.createQuestion(ERROR);
		}
	}

	/*
	 * Processes the ResultSet for a shortanswer question retrieved from the database
	 * Parameters:
	 * ResultSet results - the result set returned from the select query in retrieveQuestion()
	 * QuestionFactory factory - a QuestionFactory to create the question to be returned
	 * Returns:
	 * Question - returns a Question containing the data retrieved from the database if successful and returns an ErrorQuestion if the retrieval was unsuccessful
	 */

	private Question processShortAnswer(ResultSet results, QuestionFactory factory)
	{
		try
		{
			int id = results.getInt("question_id");
			String question = results.getString("question");
			String answer = results.getString("answer");
			String words = results.getString("keywords");
			String[] keywords = words.split(",");

			setAnswered(id, SHORTANSWER);
			Question newQuestion = factory.createQuestion(SHORTANSWER);
			newQuestion.setCorrectAnswer(answer);
			newQuestion.setChoices(keywords);
			newQuestion.setQuestion(question);
			return newQuestion;
		} 
		catch (SQLException e)
		{
			return factory.createQuestion(ERROR);
		}
	}

	/*
	 * Processes the ResultSet for a multiplechoice question retrieved from the database
	 * Parameters:
	 * ResultSet results - the result set returned from the select query in retrieveQuestion()
	 * QuestionFactory factory - a QuestionFactory to create the question to be returned
	 * Returns:
	 * Question - returns a Question containing the data retrieved from the database if successful and returns an ErrorQuestion if the retrieval was unsuccessful
	 */

	private Question processMultipleChoice(ResultSet results, QuestionFactory factory)
	{
		try
		{
			int id = results.getInt("question_id");
			String question = results.getString("question");
			String answer = results.getString("correct_answer");
			String option1 = results.getString("option1");
			String option2 = results.getString("option2");
			String option3 = results.getString("option3");
			String option4 = results.getString("option4");
			String[] choices = {option1, option2, option3, option4};

			setAnswered(id, MULTIPLECHOICE);
			Question newQuestion = factory.createQuestion(MULTIPLECHOICE);
			newQuestion.setChoices(choices);
			newQuestion.setCorrectAnswer(answer);
			newQuestion.setQuestion(question);
			return newQuestion;
		} 
		catch (SQLException e)
		{
			return factory.createQuestion(ERROR);
		}
	}

	/*
	 * Sets the answered flag associated with a retrieved question in the database based on the question_id
	 * Parameters:
	 * int id - the id of the question to update
	 * String type - the type of the question being updated 
	 * Throws:
	 * SQLException - exception is thrown if the update fails
	 */

	private void setAnswered(int id, String type) throws SQLException
	{
		String sql = "";

		switch(type.toLowerCase())
		{
			case("truefalse"):
				sql = "update truefalse set answered = 1 where question_id = ?;";
				break;
			case("multiplechoice"):
				sql = "update multiplechoice set answered = 1 where question_id = ?;";
				break;
			case("shortanswer"):
				sql = "update shortanswer set answered = 1 where question_id = ?;";
				break;
		}
		this.updateStmt = this.conn.prepareStatement(sql);
		this.updateStmt.setInt(1, id);
		this.updateStmt.executeUpdate();		
	}

	/*
	 * Sets the answered flag for all the questions in the database of the specified type to false
	 * Returns:
	 * boolean - true if the update was successful, false if it was not
	 */
	
	public boolean resetFlags(String type)
	{
		String sql = "";

		switch(type.toLowerCase())
		{
			case("truefalse"):
				sql = "update truefalse set answered = 0 where answered = 1;";
				break;
			case("multiplechoice"):
				sql = "update multiplechoice set answered = 0 where answered = 1;";
				break;
			case("shortanswer"):
				sql = "update shortanswer set answered = 0 where answered = 1;";
				break;
			default:
				return false;
		}
		try
		{
			runUpdate(sql);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Runs the database update specified by the passed in sql string and then closes the PreparedStatement
	 * Parameters:
	 * String sql - SQL to run an update to the database;
	 * Throws:
	 * SQLException - exception is thrown if the update fails
	 */
	
	private void runUpdate(String sql) throws SQLException
	{
		this.updateStmt = this.conn.prepareStatement(sql);
		this.updateStmt.executeUpdate();
		this.updateStmt.close();
	}
}//End DatabaseUtility
