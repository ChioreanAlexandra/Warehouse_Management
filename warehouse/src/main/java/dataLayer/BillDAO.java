package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DataBaseConnection;
import model.Components;

public class BillDAO extends GenericDAO<Components>{
	public Components findByClientId(int id)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT * FROM components WHERE idClient=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			Components comp=new Components();
			if (rs.next())
			{
				comp.setId(rs.getInt("id"));
				comp.setIdClient(rs.getInt("idClient"));
				comp.setIdProduct(rs.getInt("idProduct"));
				comp.setIdOrder(rs.getInt("idOrder"));
				return comp;
			}
			
		} 
		catch (SQLException e)
		{
			System.out.println("FindBillIDC failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return null;
	}
	public Components findByProductId(int id)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT * FROM components WHERE idProduct=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			Components comp=new Components();
			if (rs.next())
			{
				comp.setId(rs.getInt("id"));
				comp.setIdClient(rs.getInt("idClient"));
				comp.setIdProduct(rs.getInt("idProduct"));
				comp.setIdOrder(rs.getInt("idOrder"));
				return comp;
			}
		} 
		catch (SQLException e)
		{
			System.out.println("FindBillByIDP failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return null;
	}
}
