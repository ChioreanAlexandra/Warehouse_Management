package model;

public class Clients {
	private int id;
	private String name;
	private String email;
	private String phone;
	private float budget;
	
	public Clients()
	{
		
	}
	
	public Clients(String name, String email, String phone, float budget)
	{
		super();
		this.email=email;
		this.phone=phone;
		this.name=name;
		this.budget=budget;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getPhone()
	{
		return this.phone;
	}
	public float getBudget()
	{
		return this.budget;
	}
	public String getName()
	{
		return this.name;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setBudget(float budget)
	{
		this.budget=budget;
	}
	public String toString()
	{
		return this.id+" "+this.name+" "+this.budget;
	}
	public Object[] toObject()
	{
		Object[] o=new Object[5];
		o[0]=Integer.toString(id);
		o[1]=this.name;
		o[2]=this.email;
		o[3]=this.phone;
		o[4]=Float.toString(this.budget);
		return o;
	}
}
