package model;

public class Product {
	private int id;
	public String name;
	public int quantity;
	public float price;
	
	public Product()
	{
		
	}
	public Product(String name, float price, int quantity)
	{
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	public float getPrice()
	{
		return this.price;
	}
	public void setPrice(float price)
	{
		this.price=price;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String toString()
	{
		return this.id+" "+this.name+" "+this.quantity+" "+this.price;
	}
	public Object[] toObject()
	{
		Object[] o=new Object[4];
		o[0]=Integer.toString(id);
		o[1]=this.name;
		o[3]=Float.toString(this.price);
		o[2]=Integer.toString(this.quantity);
		return o;
		
	}

}
