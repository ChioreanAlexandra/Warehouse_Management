package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DataBaseConnection;
import model.Product;

public class ProductDAO extends GenericDAO<Product>{

	
	public Product findByName(String name)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT * FROM product where name=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setString(1, name);
			rs = stm.executeQuery();
			if(rs.next())
			{
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getFloat("price"));
				return product;
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
		return null;
	}
	public int nrOfProducts()
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT count(id) as number FROM product";
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
			System.out.println("Getting nr of products failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return -1;
	}
}
