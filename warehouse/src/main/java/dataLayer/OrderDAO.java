package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DataBaseConnection;
import model.Orders;
import model.Product;

public class OrderDAO extends GenericDAO<Orders> {

	public int getNrOfProducts(int id)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT count(components.id) as number FROM components INNER JOIN orders on components.idOrder=orders.id where orders.id=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if(rs.next())
			{
				return rs.getInt("number");
			}
				
		} 
		catch (SQLException e)
		{
			System.out.println("FindByName failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return 0;
	}
	
	public float getTotal()
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT sum(price) as total FROM orders";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			if(rs.next())
			{
				return rs.getFloat("total");
			}
				
		} 
		catch (SQLException e)
		{
			System.out.println("Getting order total failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return 0;
	}
	
}
