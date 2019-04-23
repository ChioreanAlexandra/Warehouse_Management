package model;

public class Orders {
	private int id;
	private float price;
	private int nrProducts;
	private String placementDate;
	
	
	public Orders()
	{
		this.price=0.0f;
	}
	public Orders(float price,int nrProducts,String placementDate)
	{
		this.price=price;
		this.nrProducts=nrProducts;
		this.placementDate=placementDate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNrProducts() {
		return nrProducts;
	}
	public void setNrProducts(int nrProducts) {
		this.nrProducts = nrProducts;
	}
	public String getPlacementDate() {
		return placementDate;
	}
	public void setPlacementDate(String placementDate) {
		this.placementDate = placementDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
