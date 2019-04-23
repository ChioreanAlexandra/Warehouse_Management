package businessLayer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dataLayer.OrderDAO;
import model.*;

public class OrderBLL {
	public Orders ord=new Orders();
	private OrderDAO dao=new OrderDAO();
	
	
	public Orders insertOrder()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		ord.setPlacementDate(dtf.format(localDate));
		return dao.insert(ord);
	}
	public int nrOfProducts()
	{
		return dao.getNrOfProducts(ord.getId());
	}
	
	public Orders updateOrder()
	{
		return dao.update(ord);
			
	}
	public float getOrderTotal()
	{
		return dao.getTotal();
	}
}
