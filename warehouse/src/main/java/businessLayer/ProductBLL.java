package businessLayer;

import java.util.LinkedList;
import java.util.List;

import dataLayer.ProductDAO;
import model.Product;

public class ProductBLL {
	private ProductDAO dao=new ProductDAO();
	public Product p;

	public ProductBLL()
	{
	}
	public ProductBLL(Product p)
	{
		this.p=p;
	}
	public void validateName()
	{
		if (this.p.getName().contains(".*\\d.*"))
		{
			throw new IllegalArgumentException("Name of the product is not a valid!");
		}
	}
	public void validatePrice()
	{
		if (this.p.getPrice()<=0)
		{
			throw new IllegalArgumentException("Price is not a valid!");
			
		}
	}
	public void validateQuantity()
	{
		if (this.p.getQuantity()<=0)
		{
			throw new IllegalArgumentException("Quantity is not a valid!");
			
		}
	}
	public Product insertProduct()
	{
		this.validateName();
		this.validatePrice();
		this.validateQuantity();
		Product product=dao.findByName(p.getName());
		if(product==null)
		{
			return dao.insert(this.p);	
		}
		return null;
		
		
	}
	public List<Object> transform(List<Product> list)
	{
		List<Object> objects=new LinkedList<Object>();
		for(Product c:list)
		{
			Object o=(Object)c;
			objects.add(o);
		}
		return objects;
	}
	
	public List<Object> getAllProducts()
	{
		List<Product> list=dao.findAll();
		return transform(list);
	}
	public Product updateProduct()
	{
		
		Product dbProduct=dao.findById(p.getId());
		if(dbProduct!=null)
		{
			if(p.getName().equals(""))
			{
				p.setName(dbProduct.getName());
			}
			if(p.getPrice()==0)
			{
				p.setPrice(dbProduct.getPrice());
			}
			if(p.getQuantity()==0)
			{
				p.setQuantity(dbProduct.getQuantity());
			}
		}
			return dao.update(p);
			//System.out.println("UPDATE"+p.toString());
	}
	public Product getProductByName(String name)
	{
		return dao.findByName(name);
	}
	public void deleteProduct()
	{
		dao.delete(p.getId());
	}
	public int getProductsTotal()
	{
		return dao.nrOfProducts();
	}
}
