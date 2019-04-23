package businessLayer;


import dataLayer.BillDAO;
import model.*;

public class BillBLL {
	private BillDAO dao=new BillDAO();
	public Components bill=new Components();
	
	public Components insertBill()
	{
		return dao.insert(this.bill);
	}
	public void deleteBill()
	{
		dao.delete(bill.getId());
	}
	public Components findByClient()
	{
		return dao.findByClientId(bill.getIdClient());
	}
	public Components findByProduct()
	{
		return dao.findByProductId(bill.getIdProduct());
	}
	
}