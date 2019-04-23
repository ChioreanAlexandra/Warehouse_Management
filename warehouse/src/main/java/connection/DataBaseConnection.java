package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
	
	private String Driver="com.mysql.cj.jdbc.Driver";
	private String URL="jdbc:mysql://localhost:3306/warehouse";
	public  String user="root";
	
	private static DataBaseConnection instance=new DataBaseConnection();
	
	private DataBaseConnection()
	{
		try{
			Class.forName(Driver);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	private Connection createConnection()
	{
		Connection con=null;
		try
		{
			con=DriverManager.getConnection(URL,user,"");
		}
		catch(SQLException e)
		{
			System.out.println("Error while connecting to database");
			e.printStackTrace();
		}
		return con;
	}
	public static Connection getConnection() {
		return instance.createConnection();
	}
	public static void close(Connection con)
	{
		if(con!=null)
		{
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error while closing the database");
			}
		}
	}
	public static void close(Statement stm)
	{
		if(stm!=null)
		{
			try
			{
				stm.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error while closing the statement");
			}
		}
	}
	public static void close(ResultSet result)
	{
		if(result!=null)
		{
			try
			{
				result.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error while closing the result set");
			}
		}
	}
}
