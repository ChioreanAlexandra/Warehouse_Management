package businessLayer;

import java.util.LinkedList;
import java.util.List;

import dataLayer.ClientDAO;
import model.Clients;

public class ClientBLL {
	public Clients c;
	private ClientDAO dao=new ClientDAO();
	public ClientBLL()
	{
		
	}
	public ClientBLL(Clients client)
	{
		super();
		this.c=client;
		
	}
	public void validateEmail(String email)
	{
		if(email.length()<8 || !email.contains("@") )
		{
			throw new IllegalArgumentException("Email is not a valid!");
		}
			
	}
	public void validatePhone(String phone)
	{
		if(phone.length()!=10 || !phone.matches("[0-9]+"))
		{
			throw new IllegalArgumentException("Phone number is not a valid!");
		}
	}
	public void validateName(String name)
	{
		if(name.contains(" "))
		{
			String[] s=name.split(" ");
			for(String i:s)
			{
				if (i.charAt(0)!=i.toUpperCase().charAt(0))
				{
					throw new IllegalArgumentException("Name is not a valid!");
				}
			}
		}
		else 
		{
			throw new IllegalArgumentException("Name is not a valid!");
		}
	}
	public void validateBudeget(float budget)
	{
		if(budget<0)
		{
			throw new IllegalArgumentException("Budget must be a positive number");
		}
	}
	public Clients insertClient()
	{
		this.validateBudeget(c.getBudget());
		this.validateName(c.getName());
		this.validatePhone(c.getPhone());
		this.validateEmail(c.getEmail());
		if(!dao.findByEmail(c.getEmail()))
				return dao.insert(this.c);
		else 
			System.out.println("Email already exists");
		return null;
	}
	public List<Object> transform(List<Clients> list)
	{
		List<Object> objects=new LinkedList<Object>();
		for(Clients c:list)
		{
			Object o=(Object)c;
			objects.add(o);
		}
		return objects;
	}
	
	public List<Object> getAllClients()
	{
		
		List<Clients> list=dao.findAll();
		return transform(list);
	}
	public Clients updateClient()
	{
		
		Clients dbClient=dao.findById(c.getId());
		if(dbClient!=null)
		{
			if(c.getName().equals(""))
			{
				c.setName(dbClient.getName());
			}
			if(c.getEmail().equals(""))
			{
				c.setEmail(dbClient.getEmail());
			}
			if(c.getPhone().equals(""))
			{
				c.setPhone(dbClient.getPhone());
			}
			if(c.getBudget()==0)
			{
				c.setBudget(dbClient.getBudget());
			}
		}
			return dao.update(c);
			//System.out.println("UPDATE"+c.toString());
	}
	public void deleteClient()
	{
		dao.delete(c.getId());
	}
	public int getClientsTotal()
	{
		return dao.nrOfClients();
	}
	public Clients findClient(int id)
	{
		return dao.findById(id);
	}
}
