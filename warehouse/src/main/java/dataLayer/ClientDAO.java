package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DataBaseConnection;
import model.Clients;

public class ClientDAO extends GenericDAO<Clients>{
	
	public boolean findByEmail(String email)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT * FROM clients where email=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setString(1, email);
			rs = stm.executeQuery();
			if(!rs.next())
				return false;
			return true;
		} 
		catch (SQLException e)
		{
			System.out.println("FindByEmail failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return false;
	}
	public int nrOfClients()
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT count(id) as number FROM clients";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			if(rs.next())
			{
				return rs.getInt("number");
			}
				
		} 
		catch (SQLException e)
		{
			System.out.println("Getting nr of clients failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return -1;
	}
	

}
