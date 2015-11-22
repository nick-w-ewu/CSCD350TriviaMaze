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

public class DatabaseUtility
{
	private Connection conn;
	private PreparedStatement stmt;
	private String dbName;
	
	public DatabaseUtility(String name)
	{
		this.stmt = null;
		this.dbName = name;
	}
	
	public boolean connectDatabase()
	{
		try
		{
	      Class.forName("org.sqlite.JDBC");
	      conn = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
	      return true;
	    }
		catch (Exception e)
		{
	      return false;
	    }
	}
	
	public boolean insertQuestion(String question, int answer) throws SQLException
	{
		if(this.conn == null)
		{
			throw new SQLException("No database connected, you must sucessfully call connectDatabase() first");
		}
		String sql = "insert into truefalse values (null, ?, ?);";
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
			e.printStackTrace();
			this.stmt = null;
			return false;
		}
	}
	
	public boolean insertQuestion(String question, String answer) throws SQLException
	{
		if(this.conn == null)
		{
			throw new SQLException("No database connected, you must sucessfully call connectDatabase() first");
		}
		String sql = "insert into shortanswer values (null, ?, ?);";
		try
		{
			this.stmt = conn.prepareStatement(sql);
			stmt.setString(1, question);
			stmt.setString(2, answer);
			this.stmt.executeUpdate();
			this.stmt = null;
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			this.stmt = null;
			return false;
		}
	}
	
	public boolean insertQuestion(String question, int answer, String option1, String option2, String option3, String option4) throws SQLException
	{
		if(this.conn == null)
		{
			throw new SQLException("No database connected, you must sucessfully call connectDatabase() first");
		}
		String sql = "insert into multiplechoice values (null, ?, ?, ?, ?, ?, ?, ?);";
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
			e.printStackTrace();
			this.stmt = null;
			return false;
		}
	}
	
	public boolean insertHint(int id, String type, String hint) throws SQLException
	{
		if(this.conn == null)
		{
			throw new SQLException("No database connected, you must sucessfully call connectDatabase() first");
		}
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
			e.printStackTrace();
			this.stmt = null;
			return false;
		}
	}
}
