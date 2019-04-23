 package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import businessLayer.*;
import model.*;

public class Controller {

	private View view;
	private ClientBLL client;
	private ProductBLL product=new ProductBLL();
	private OrderBLL order;
	private BillBLL orderComp=new BillBLL();
	private Orders ord;
	private String billContent="";
	private String clientInfo="";
	
	public Controller(View view)
	{
		this.view=view;
		view.addProduct.addActionListener(new AddProductAL());
		view.editClient.addActionListener(new EditClientActionListener());
		view.addClient.addActionListener(new RegistrationActionListener());
		view.clientFrame.addActionListener(new ClientFrameAL());
		view.productFrame.addActionListener(new ProductFrameAL());
		view.editProduct.addActionListener(new EditProductAL());
		view.table.addMouseListener(new MouseL());
		view.order.addActionListener(new OrderFrameAL());
		view.cart.addActionListener(new AddCartAL());
		view.finish.addActionListener(new AddOrderAL());
		view.deleteClient.addActionListener(new DeleteClientAL());
		view.deleteProduct.addActionListener(new DeleteProductAL());
		view.getReport.addActionListener(new GetReportAL());
	}
	public class ClientFrameAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			client=new ClientBLL();
			List<Object> list=client.getAllClients();
			view.createClientFrame(view.createTable(list));
		}

	}
	public class ProductFrameAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			List<Object> list=product.getAllProducts();
			view.createProductFrame(view.createTable(list));
		}
	}
	public class OrderFrameAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			view.createOrderFrame();
			client=new ClientBLL();
			List<Object> list=client.getAllClients();
			view.populateBox(view.clientBox, list, "id");
			list=product.getAllProducts();
			view.populateBox(view.productBox, list, "name");
			order=new OrderBLL();
			ord=new Orders();
			ord=order.insertOrder();
		} 
	}
	
	public class EditClientActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name=view.tf[0].getText();
			String email=view.tf[1].getText();
			String phone=view.tf[2].getText();
			float budget=0.0f;
			if(!view.tf[3].getText().equals(""))
			{
				budget=Float.parseFloat(view.tf[3].getText());
			}
			int id=0;
			if(!view.tf[4].getText().equals(""))
			{
				id=Integer.parseInt(view.tf[4].getText());
			}
			Clients c=new Clients(name,email,phone,budget);
			int i=view.table.getSelectedRow();
			if(i>=0)
			{
				DefaultTableModel t=(DefaultTableModel) view.table.getModel();
				c.setId(Integer.parseInt(t.getValueAt(i, 0).toString()));
				client=new ClientBLL(c);
				Clients test=client.updateClient();
				if(test!=null)
				{
					t.setValueAt(c.toObject()[1], i,1);
					t.setValueAt(c.toObject()[2], i,2);
					t.setValueAt(c.toObject()[3], i,3);
					t.setValueAt(c.toObject()[4], i,4);
					view.table.setModel(t);
				}
			}
			else
			{
				c.setId(id);
				client=new ClientBLL(c);
				Clients test=client.updateClient();
				if(test!=null)
					view.updateFrame(view.createTable(client.getAllClients()),view.content1);
			}	
		}
	}
	public class AddProductAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name=view.tf[0].getText();
			int quantity=Integer.parseInt(view.tf[1].getText());
			float price=Float.parseFloat(view.tf[2].getText());
			Product p=new Product(name,price,quantity);
			product=new ProductBLL(p);
			Product pr=product.insertProduct();
			DefaultTableModel t=(DefaultTableModel) view.table.getModel();
			if(pr!=null)
			{
				t.addRow(pr.toObject());
				view.table.setModel(t);
			}
			System.out.println("Added product");
		}
	}
	public class RegistrationActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name=view.tf[0].getText();
			String email=view.tf[1].getText();
			String phone=view.tf[2].getText();
			float budget=Float.parseFloat(view.tf[3].getText());
			Clients c=new Clients(name,email,phone,budget);
			client=new ClientBLL(c);
			Clients cl=client.insertClient();
			DefaultTableModel t=(DefaultTableModel) view.table.getModel();
			if(cl!=null)
			{
				t.addRow(cl.toObject());
				view.table.setModel(t);
			}
			System.out.println("Regist");
		}
	}
	public class EditProductAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name=view.tf[0].getText();
			int quantity=0;
			if(!view.tf[1].getText().equals(""))
			{
				quantity=Integer.parseInt(view.tf[1].getText());
			}
			float price=0.0f;
			if(!view.tf[2].getText().equals(""))
			{
				price=Float.parseFloat(view.tf[2].getText());
			}
			int id=0;
			if(!view.tf[3].getText().equals(""))
			{
				id=Integer.parseInt(view.tf[3].getText());
			}
			Product p=new Product(name,price,quantity);
			int i=view.table.getSelectedRow();
			if(i>=0)
			{
				DefaultTableModel t=(DefaultTableModel) view.table.getModel();
				p.setId(Integer.parseInt(t.getValueAt(i, 0).toString()));
				product=new ProductBLL(p);
				Product test=product.updateProduct();
				if(test!=null)
				{
					t.setValueAt(p.toObject()[1], i,1);
					t.setValueAt(p.toObject()[2], i,2);
					t.setValueAt(p.toObject()[3], i,3);
					view.table.setModel(t);
				}
			}
			else
			{
				p.setId(id);
				product=new ProductBLL(p);
				Product test=product.updateProduct();
				if(test!=null)
					view.updateFrame(view.createTable(product.getAllProducts()),view.content2);
			}
			System.out.println("Updated product");
		}
	}
	
	public class MouseL implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			int index=view.table.getSelectedRow();
			DefaultTableModel t=(DefaultTableModel) view.table.getModel();
			int counter=0;
			for(JTextField f:view.tf)
			{
				counter++;
				if(counter<=t.getColumnCount()-1)
				{
					f.setText(t.getValueAt(index, counter).toString());
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class AddCartAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			view.label.setText("");
			int quantity=Integer.parseInt(view.tf[0].getText());
			int clientId=Integer.parseInt((String)view.clientBox.getSelectedItem());
			Clients clientDetalies=client.findClient(clientId);
			if(clientInfo.equals(""))
			{
				clientInfo+="Name:"+clientDetalies.getName()+"\nEmail:"+clientDetalies.getEmail()+"\nPhone number:"+clientDetalies.getPhone()+"\n";	
			}
			String productName=(String) view.productBox.getSelectedItem();
			Product prod=product.getProductByName(productName);
			float sum=ord.getPrice();
			if(prod.getQuantity()>=quantity)
			{
				prod.setQuantity(prod.getQuantity()-quantity);
				sum+=prod.price*quantity;
				billContent+=prod.getName()+" quantity: "+quantity+" price per unit "+prod.price+" total "+prod.price*quantity+"\n";
				orderComp.bill.setIdClient(clientId);
				orderComp.bill.setIdOrder(ord.getId());
				orderComp.bill.setIdProduct(prod.getId());
				orderComp.bill.setProductQuantity(quantity);
				Components addedBill=orderComp.insertBill();
				view.label.setText("Product added to cart");
				ord.setPrice(sum);
				product.p=prod;
				product.updateProduct();
				if(addedBill!=null)
				{
					System.out.println("Added bill");
				}
			}
			else
			{
				view.label.setText("Under-stock");
			}
		}
		
	}
	
	public class AddOrderAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			view.label.setText("Order has been sent");
			order.ord=ord;
			ord.setNrProducts(order.nrOfProducts());
			billContent+="Order total:"+ord.getPrice()+"\nDate:"+ord.getPlacementDate();
			order.ord=ord;
			ord=order.updateOrder();
			String fileName="Order nr."+ord.getId()+".txt";
			File file=new File(fileName);
			try {
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.write(clientInfo+billContent);
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	public class DeleteClientAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DefaultTableModel t=(DefaultTableModel) view.table.getModel();
			int index=view.table.getSelectedRow();
			String str=t.getValueAt(index, 0).toString();
			int i=Integer.parseInt(str);
			Clients c=new Clients();
			c.setId(i);
			client.c=c;
			orderComp.bill.setIdClient(i);
			Components billToDelete=orderComp.findByClient();
			while(billToDelete!=null)
			{
				orderComp.bill.setId(billToDelete.getId());
				orderComp.deleteBill();
				billToDelete=orderComp.findByClient();
			}
			
			client.deleteClient();
			t.removeRow(index);
			
			
		}
		
	}
	public class DeleteProductAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			DefaultTableModel t=(DefaultTableModel) view.table.getModel();
			int index=view.table.getSelectedRow();
			String str=t.getValueAt(index, 0).toString();
			int i=Integer.parseInt(str);
			Product c=new Product();
			c.setId(i);
			product.p=c;
			orderComp.bill.setIdProduct(i);
			Components billToDelete=orderComp.findByProduct();
			while(billToDelete!=null)
			{
				orderComp.bill.setId(billToDelete.getId());
				orderComp.deleteBill();
				billToDelete=orderComp.findByProduct();
			}
			product.deleteProduct();
			t.removeRow(index);
		}
		
	}
	public class GetReportAL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int productsNumber=product.getProductsTotal();
			client=new ClientBLL();
			int clientsNumber=client.getClientsTotal();
			order=new OrderBLL();
			float orderTotal=order.getOrderTotal();
			view.ta.setVisible(true);
			view.ta.append("Total number of products is:"+productsNumber+"\n");
			view.ta.append("Total number of clients is:"+clientsNumber+"\n");
			view.ta.append("Total value of orders is:"+orderTotal+"");
		}
		
	}
}
