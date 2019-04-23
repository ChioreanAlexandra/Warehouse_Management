package model;

public class Components {
	private int id;
	private int idClient;
	private int idProduct;
	private int idOrder;
	private int productQuantity;
	public Components()
	{
		
	}
	public Components(int id_client,int id_product,int id_order,int quantity)
	{
		this.setIdClient(id_client);
		this.setIdProduct(id_product);
		this.setIdOrder(id_order);
		this.setProductQuantity(quantity);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int id_clients) {
		this.idClient = id_clients;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int id_order) {
		this.idOrder = id_order;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
}
