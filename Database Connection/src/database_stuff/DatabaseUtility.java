/**
 *DatabaseUtility.java
 *Author: Nicholas Witmer
 *Revision: 1, Nicholas Witmer
 *Date: 11/4/2015
 *Utility to connect to a specified SQLite database and provides functionality to insert
 *3 types of questions and hints for those questions into the database.
 *
 */

package database_stuff;
import java.sql.*;

import questions.Question;
import questions.QuestionFactory;

public class DatabaseUtility
{
	private Connection conn;
	private PreparedStatement stmt;

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
			conn = DriverManager.getConnection("jdbc:sqlite:questions.db");

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/*
	 * Inserts a truefalse question into the database
	 * Parameters:
	 * String question - the question to be inserted into the database
	 * int answer - the answer for the question, should be 1 for true and 0 for false
	 * Returns:
	 * boolean - true if the database update was successful and false if there was an SQLException meaning the update was unsuccessful
	 */

	public boolean insertQuestion(String question, int answer)
	{
		String sql = "insert into truefalse values (null, ?, ?, 0);";

		try
		{
			this.stmt = conn.prepareStatement(sql);

			stmt.setString(1, question);
			stmt.setInt(2, answer);
			this.stmt.executeUpdate();
			this.stmt = null;
			return true;
		}
		catch (SQLException e)
		{
			this.stmt = null;
			return false;
		}
	}

	/*
	 * Inserts a shortanswer question into the database
	 * Parameters:
	 * String question - the question to be inserted into the database
	 * String answer - the answer for the question
	 * Returns:
	 * boolean - true if the database update was successful and false if there was an SQLException meaning the update was unsuccessful
	 */

	public boolean insertQuestion(String question, String answer, String keywords)
	{
		String sql = "insert into shortanswer values (null, ?, ?, ?, 0);";

		try
		{
			this.stmt = conn.prepareStatement(sql);

			stmt.setString(1, question);
			stmt.setString(2, answer);
			stmt.setString(3, keywords);
			this.stmt.executeUpdate();
			this.stmt = null;
			return true;
		}
		catch (SQLException e)
		{
			this.stmt = null;
			return false;
		}
	}

	/*
	 * Inserts a multiplechoice question into the database
	 * Parameters:
	 * String question - the question to be inserted into the database
	 * int answer - the option which is the correct answer for the question
	 * String option1 - an option for the question
	 * String option2 - an option for the question
	 * String option3 - an option for the question
	 * String option4 - an option for the question
	 * Returns:
	 * boolean - true if the database update was successful and false if there was an SQLException meaning the update was unsuccessful
	 */

	public boolean insertQuestion(String question, int answer, String option1, String option2, String option3, String option4)
	{
		String sql = "insert into multiplechoice values (null, ?, ?, ?, ?, ?, ?, 0);";

		try
		{
			this.stmt = conn.prepareStatement(sql);

			stmt.setString(1, question);
			stmt.setInt(2, answer);
			stmt.setString(3, option1);
			stmt.setString(4, option2);
			stmt.setString(5, option3);
			stmt.setString(6, option4);
			this.stmt.executeUpdate();
			this.stmt = null;
			return true;
		}
		catch (SQLException e)
		{
			this.stmt = null;
			return false;
		}
	}

	/*
	 * Inserts a hint question into the database
	 * Parameters:
	 * int id - the question id which this hint is for
	 * String type - the type of question the hint is for
	 * String hint - the hint for the user about this question
	 * Returns:
	 * boolean - true if the database update was successful and false if there was an SQLException meaning the update was unsuccessful
	 */

	public boolean insertHint(int id, String type, String hint)
	{
		String sql = "insert into hints values (?, ?, ?);";

		try
		{
			this.stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.setString(2, type);
			stmt.setString(3, hint);
			this.stmt.executeUpdate();
			this.stmt = null;
			return true;
		}
		catch (SQLException e)
		{
			this.stmt = null;
			return false;
		}
	}

	public Question retrieveQuestion(String type)
	{
		String sql = "";
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
			Question toReturn = null;
			QuestionFactory factory = new QuestionFactory();
			switch(type.toLowerCase())
			{
				case("truefalse"):
					toReturn = processTrueFalse(results, factory);
					break;
				case("multiplechoice"):
					toReturn = processShortAnswer(results, factory);
					break;
				case("shortanswer"):
					sql = "select * from shortanswer where answered = 0 ORDER BY RANDOM() LIMIT 1;";
					break;
			}
			return toReturn;
		}
		catch(SQLException e)
		{
			return null;
		}
	}
	
	private Question processTrueFalse(ResultSet results, QuestionFactory factory)
	{
		try
		{
			int id = results.getInt("question_id");
			String question = results.getString("question");
			int answer = results.getInt("answer");
			String booleanAnswer;
			if(answer == 1)
			{
				booleanAnswer = "T";
			}
			else
			{
				booleanAnswer = "F";
			}

			Question newQuestion = factory.createQuestion("truefalse");
			newQuestion.setQuestion(question);
			newQuestion.setCorrectAnswer(booleanAnswer);
			return newQuestion;
		} 
		catch (SQLException e)
		{
			return null;
		}
	}
	
	private Question processShortAnswer(ResultSet results, QuestionFactory factory)
	{
		try
		{
			int id = results.getInt("question_id");
			String question = results.getString("question");
			String answer = results.getString("answer");


			Question newQuestion = factory.createQuestion("shortanswer");
			newQuestion.setQuestion(question);
			newQuestion.setCorrectAnswer(answer);
			return newQuestion;
		} 
		catch (SQLException e)
		{
			return null;
		}
	}
	
}//End DatabaseUtility
