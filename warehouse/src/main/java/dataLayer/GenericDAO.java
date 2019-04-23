package dataLayer;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
//import java.lang.reflect.ParameterizedType;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.List;

import connection.DataBaseConnection;
import model.Clients;
public class GenericDAO<T> {
	private final Class<T> type;
	public String[] objectDetalies;
	
	@SuppressWarnings("unchecked")
	public GenericDAO()
	{
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}
	
	public void getObjectDetalies()
	{
		int i=0;
		int nrOfFields=type.getDeclaredFields().length;
		objectDetalies=new String[nrOfFields+1];
		objectDetalies[i]=this.type.getSimpleName().toLowerCase();
		for(Field f:type.getDeclaredFields())
		{
			i++;
			objectDetalies[i]=f.getName();
		}
	}
	protected List<T> parseObject(ResultSet rs)
	{
		List<T> list=new LinkedList<T>();
		try
		{
			while (rs.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields())
				{
					Object value = rs.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		return list;
	}
	public List<T> findAll()
	{
		
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT * FROM "+this.type.getSimpleName().toLowerCase();
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			rs = stm.executeQuery();
			return parseObject(rs);
		} 
		catch (SQLException e)
		{
			System.out.println("FindAll failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return null;
	}
	private String getInsertQuery()
	{
		this.getObjectDetalies();
		StringBuilder s=new StringBuilder();
		StringBuilder val=new StringBuilder();
		s.append("INSERT INTO ");
		s.append(objectDetalies[0]);
		s.append(" (");
		val.append('(');
		for(int i=2;i<objectDetalies.length;i++)
		{
			s.append(objectDetalies[i]);
			val.append('?');
			if(i!=objectDetalies.length-1)
			{
				s.append(',');
				val.append(',');
			}
		}
		val.append(')');
		s.append(") VALUES ");
		s.append(val);
		return s.toString();
		
		
	}
	public PreparedStatement setStatementParam(PreparedStatement stm, T obj)
	{
		try{
			Object value = obj;
			int i=0;
			for (Field field : type.getDeclaredFields())
			{
				if(!field.getName().equals("id"))
				{
					i++;
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
					Method method = propertyDescriptor.getReadMethod();
					Type type=field.getType();
					switch(type.getTypeName())
					{
					case "int": Object nr=new Object(); nr=method.invoke(value);
						stm.setInt(i, (int)nr); break;
					case "float": Object f=new Object(); f=method.invoke(value); stm.setFloat(i, (float)f);break;
					default: Object str=new Object(); str=method.invoke(value); stm.setString(i, (String)str); break;
					}
				}
			}
			return stm;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return stm;
	}
	public T insert(T obj)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query=getInsertQuery();
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm=setStatementParam(stm,obj);
			stm.executeUpdate();
			List<T> list=this.findAll();
			return list.get(list.size()-1);
		} 
		catch (Exception e)
		{
			System.out.println("Insert failed"+e.getMessage());
			
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return null;
	}

	private String getUpdateQuery()
	{
		this.getObjectDetalies();
		StringBuilder s=new StringBuilder();
		s.append("UPDATE ");
		s.append(objectDetalies[0]);
		s.append(" SET ");
		for(int i=2;i<objectDetalies.length;i++)
		{
			s.append(objectDetalies[i]);
			s.append("=?");
			if(i!=objectDetalies.length-1)
			{
				s.append(", ");
			}
		}
		s.append(" WHERE id=?");
		return s.toString();
		
		
	}
	public T update(T obj)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query=getUpdateQuery();
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm=setStatementParam(stm,obj);
			int index=this.objectDetalies.length-1;
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id",type);
			Method method = propertyDescriptor.getReadMethod();
			Object o=method.invoke(obj);
			stm.setInt(index, (int)o);
			stm.executeUpdate();
			return findById((int)o);
				
		} 
		catch (Exception e)
		{
			System.out.println("Update failed"+e.getMessage());
			
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return null;
	}

	public T findById(int id)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="SELECT * FROM "+this.type.getSimpleName()+" WHERE id=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			return parseObject(rs).get(0);
		} 
		catch (SQLException e)
		{
			System.out.println("FindAllByID failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		return null;
	}
	public void delete(int id)
	{
		Connection con=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		String query="DELETE FROM "+this.type.getSimpleName().toLowerCase()+" WHERE id=?";
		try {
			con = DataBaseConnection.getConnection();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			stm.executeUpdate();
		} 
		catch (SQLException e)
		{
			System.out.println("Delete failed");
		} 
		finally {
			DataBaseConnection.close(rs);
			DataBaseConnection.close(stm);
			DataBaseConnection.close(con);
		}
		
	}
}
