package presentation;

import java.awt.Container;
import java.awt.GridLayout;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;


public class View {
	public JFrame f1,f2,f4;;
	public JButton editClient=new JButton("Edit client");
	public JButton deleteClient=new JButton("Delete client");
	public JButton deleteProduct=new JButton("Delete product");
	public JButton addClient=new JButton("Register");
	public JButton clientFrame=new JButton("Client");
	public JButton productFrame=new JButton("Product");
	public JButton addProduct=new JButton("Add Product");
	public JButton editProduct=new JButton("Edit Product");
	public JButton order=new JButton("Add order");
	public JButton cart=new JButton("Add to cart");
	public JButton finish=new JButton("Finish order");
	public JButton getReport=new JButton("Get Report");
	public JTextField[] tf=new JTextField[10];
	public Container content1=new Container();
	public Container content2=new Container();
	public JTable table=new JTable();
	public JComboBox<String> clientBox;
	public JComboBox<String> productBox;
	private JPanel p2;
	public JTextArea ta;
	public JLabel label;
	public View()
	{
		f1=new JFrame("Shop");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setSize(700, 400);
		//JPanel p1=new JPanel();
		JPanel p1=new JPanel();
		SpringLayout layout=new SpringLayout();
		ta=new JTextArea();
		ta.setVisible(false);
		
		p1.setLayout(layout);
		p1.add(clientFrame);
		p1.add(productFrame);
		p1.add(order);
		p1.add(getReport);
		p1.add(ta);
		
		layout.putConstraint(SpringLayout.WEST, order, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, order, 25, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, clientFrame, 25, SpringLayout.EAST, order);
		layout.putConstraint(SpringLayout.NORTH, clientFrame, 25, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, productFrame, 25, SpringLayout.EAST, clientFrame);
		layout.putConstraint(SpringLayout.NORTH, productFrame, 25, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, getReport, 25, SpringLayout.EAST, productFrame);
		layout.putConstraint(SpringLayout.NORTH, getReport, 25, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, ta, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, ta, 50, SpringLayout.NORTH, order);
		
		f1.add(p1);
		f1.setVisible(true);
	}
	public void createClientFrame(JTable jt)
	{
		f2=new JFrame("Client");
		f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f2.setSize(700, 400);
		JPanel p1=new JPanel();
		SpringLayout layout=new SpringLayout();
		p1.setLayout(layout);
		JLabel l1=new JLabel("Name");
		JLabel l2=new JLabel("Email");
		JLabel l3=new JLabel("Phone");
		JLabel l4=new JLabel("Budget");
		JLabel l5=new JLabel("Update client with id");
		tf[0]=new JTextField(20); //name
		tf[1]=new JTextField(20); //email
		tf[2]=new JTextField(20); //phone
		tf[3]=new JTextField(20);//budget
		tf[4]=new JTextField(20);//id
		this.table.setModel(jt.getModel());
		JScrollPane s=new JScrollPane(table);
		p2=new JPanel();
		p2.add(s);
		p1.add(deleteClient);
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(tf[0]);
		p1.add(tf[1]);
		p1.add(tf[2]);
		p1.add(tf[3]);
		p1.add(l4);
		p1.add(l5);
		p1.add(tf[4]);
		p1.add(addClient);
		p1.add(editClient);
		
		layout.putConstraint(SpringLayout.WEST, l1, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l1, 5, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, l2, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l2, 25, SpringLayout.NORTH, l1);
		
		layout.putConstraint(SpringLayout.WEST, l3, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l3, 25, SpringLayout.NORTH, l2);
		
		layout.putConstraint(SpringLayout.WEST, l4, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l4, 25, SpringLayout.NORTH, l3);
		
		layout.putConstraint(SpringLayout.WEST, tf[0], 25, SpringLayout.EAST, l1);  //name
		layout.putConstraint(SpringLayout.NORTH, tf[0], 5, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, tf[1], 25, SpringLayout.EAST, l2);//email
		layout.putConstraint(SpringLayout.NORTH, tf[1], 25, SpringLayout.NORTH, tf[0]);
		
		layout.putConstraint(SpringLayout.WEST, tf[2], 25, SpringLayout.EAST, l3);//phone
		layout.putConstraint(SpringLayout.NORTH, tf[2], 25, SpringLayout.NORTH, tf[1]);
		
		layout.putConstraint(SpringLayout.WEST, tf[3], 25, SpringLayout.EAST, l4);//bugdet
		layout.putConstraint(SpringLayout.NORTH, tf[3], 25, SpringLayout.NORTH, tf[2]);
		
		layout.putConstraint(SpringLayout.WEST, addClient, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, addClient, 25, SpringLayout.NORTH, l4);
		
		layout.putConstraint(SpringLayout.WEST, l5, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l5, 100, SpringLayout.NORTH, l4);
		
		layout.putConstraint(SpringLayout.WEST, tf[4], 25, SpringLayout.EAST, l5);//id
		layout.putConstraint(SpringLayout.NORTH, tf[4], 100, SpringLayout.NORTH, tf[3]);
		
		layout.putConstraint(SpringLayout.WEST, editClient, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, editClient, 25, SpringLayout.NORTH, l5);
		
		layout.putConstraint(SpringLayout.WEST, deleteClient, 25, SpringLayout.EAST, editClient);
		layout.putConstraint(SpringLayout.NORTH, deleteClient, 25, SpringLayout.NORTH, l5);
		
		content1=new Container();
		content1.setLayout(new GridLayout(0,2));
		content1.add(p1);
		content1.add(p2);
		f2.add(content1);
		f2.pack();
		f2.setVisible(true);
	}
	public void updateFrame(JTable jt,Container content)
	{
		content.remove(p2);
		JTable table=jt;
		JScrollPane s=new JScrollPane(table);
		p2=new JPanel();
		p2.add(s);
		content.setLayout(new GridLayout(0,2));
		content.add(p2);
		f2.add(content);
		f2.pack();
		f2.setVisible(true);
		
	}
	public JTable createTable(List<Object> o)
	{
		int nrOfFields=o.get(0).getClass().getDeclaredFields().length;
		Object[] columnName=new String[nrOfFields];
		Object[][] rows=new String[o.size()][nrOfFields];
		int i=-1;
		System.out.println();
		for(Field f:o.get(0).getClass().getDeclaredFields())
		{
			i++;
			columnName[i]=(Object)f.getName();
		}
		i=-1;
		for(Object j:o)
		{
			i++;
			int k=-1;
			for(Field f:o.get(0).getClass().getDeclaredFields())
			{
				k++;
				PropertyDescriptor propertyDescriptor;
				try {
					propertyDescriptor = new PropertyDescriptor(f.getName(),o.get(0).getClass());
					Method method = propertyDescriptor.getReadMethod();
					Type type=f.getType();
					switch(type.getTypeName())
					{
					case "int": Object nr=new Object(); nr=method.invoke(j);
						rows[i][k]=(Object)(Integer.toString((int)nr)); break;
					case "float": Object flt=new Object(); 
					flt=method.invoke(j); 
					rows[i][k]=(Object)(Float.toString((float)flt));break;
					default: Object str=new Object(); str=method.invoke(j); rows[i][k]=str;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		JTable jt=new JTable(new DefaultTableModel(rows,columnName));
		return jt;
	}

	public void createProductFrame(JTable jt)
	{
		f2=new JFrame("Product");
		f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f2.setSize(700, 400);
		JPanel p1=new JPanel();
		SpringLayout layout=new SpringLayout();
		p1.setLayout(layout);
		JLabel l1=new JLabel("Name");
		JLabel l2=new JLabel("Quantity");
		JLabel l3=new JLabel("Price");
		JLabel l4=new JLabel("Update product with id");
		tf[0]=new JTextField(20); //name
		tf[1]=new JTextField(20); //price
		tf[2]=new JTextField(20); //qunatity
		tf[3]=new JTextField(20);//id
		this.table.setModel(jt.getModel());
		JScrollPane s=new JScrollPane(table);
		p2=new JPanel();
		p2.add(s);
		
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(tf[0]);
		p1.add(tf[1]);
		p1.add(tf[2]);
		p1.add(tf[3]);
		p1.add(l4);
		p1.add(addProduct);
		p1.add(editProduct);
		p1.add(deleteProduct);
		
		layout.putConstraint(SpringLayout.WEST, l1, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l1, 5, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, l2, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l2, 25, SpringLayout.NORTH, l1);
		
		layout.putConstraint(SpringLayout.WEST, l3, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l3, 25, SpringLayout.NORTH, l2);
		
		layout.putConstraint(SpringLayout.WEST, tf[0], 25, SpringLayout.EAST, l1);  //name
		layout.putConstraint(SpringLayout.NORTH, tf[0], 5, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, tf[1], 25, SpringLayout.EAST, l2);//price
		layout.putConstraint(SpringLayout.NORTH, tf[1], 25, SpringLayout.NORTH, tf[0]);
		
		layout.putConstraint(SpringLayout.WEST, tf[2], 25, SpringLayout.EAST, l3);//quantity
		layout.putConstraint(SpringLayout.NORTH, tf[2], 25, SpringLayout.NORTH, tf[1]);
		
		layout.putConstraint(SpringLayout.WEST, addProduct, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, addProduct, 25, SpringLayout.NORTH, l3);
		
		layout.putConstraint(SpringLayout.WEST, l4, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, l4, 100, SpringLayout.NORTH, l3);
		
		layout.putConstraint(SpringLayout.WEST, tf[3], 25, SpringLayout.EAST, l4);//id
		layout.putConstraint(SpringLayout.NORTH, tf[3], 100, SpringLayout.NORTH, tf[2]);
		
		layout.putConstraint(SpringLayout.WEST, editProduct, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, editProduct, 25, SpringLayout.NORTH, l4);
		
		layout.putConstraint(SpringLayout.WEST, deleteProduct, 25, SpringLayout.EAST, editProduct);
		layout.putConstraint(SpringLayout.NORTH, deleteProduct, 25, SpringLayout.NORTH, l4);
		
		content2=new Container();
		content2.setLayout(new GridLayout(0,2));
		content2.add(p1);
		content2.add(p2);
		f2.add(content2);
		f2.pack();
		f2.setVisible(true);
	}

	public void populateBox(JComboBox<String> box,List<Object> list,String fieldName)
	{
		for(Object o:list)
		{
			PropertyDescriptor propertyDescriptor;
			try {
				Field f=list.get(0).getClass().getDeclaredField(fieldName);
				propertyDescriptor = new PropertyDescriptor(fieldName,list.get(0).getClass());
				Method method = propertyDescriptor.getReadMethod();
				Type type=f.getType();
				switch(type.getTypeName())
				{
				case "int": Object nr=new Object(); nr=method.invoke(o); box.addItem(Integer.toString((int) nr)); break;
				default: Object str=new Object(); str=method.invoke(o); box.addItem((String)str);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void createOrderFrame()
	{
		f4=new JFrame("Order");
		f4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f4.setSize(700, 400);
		JPanel p1=new JPanel();
		SpringLayout layout=new SpringLayout();
		JLabel title= new JLabel("Place order");
		clientBox=new JComboBox<String>();
		productBox=new JComboBox<String>();
		tf[0]=new JTextField(10);
		label=new JLabel();
		p1.setLayout(layout);
		
		p1.add(tf[0]);
		p1.add(cart);
		p1.add(finish);
		p1.add(title);
		p1.add(clientBox);
		p1.add(productBox);
		p1.add(label);
		
		layout.putConstraint(SpringLayout.WEST, title, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, title, 25, SpringLayout.NORTH, p1);
		
		layout.putConstraint(SpringLayout.WEST, clientBox, 25, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.NORTH, clientBox, 50, SpringLayout.NORTH, title);
		
		layout.putConstraint(SpringLayout.WEST, productBox, 25, SpringLayout.EAST, clientBox);
		layout.putConstraint(SpringLayout.NORTH, productBox, 50, SpringLayout.NORTH, title);
		
		layout.putConstraint(SpringLayout.WEST, tf[0], 25, SpringLayout.EAST, productBox);
		layout.putConstraint(SpringLayout.NORTH, tf[0], 50, SpringLayout.NORTH, title);
		
		layout.putConstraint(SpringLayout.WEST, cart, 25, SpringLayout.EAST, tf[0]);
		layout.putConstraint(SpringLayout.NORTH, cart, 50, SpringLayout.NORTH, title);
		
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, p1);
		layout.putConstraint(SpringLayout.SOUTH, label, -50, SpringLayout.SOUTH, p1);
		
		layout.putConstraint(SpringLayout.EAST, finish, -25, SpringLayout.EAST, p1);
		layout.putConstraint(SpringLayout.SOUTH, finish, -50, SpringLayout.SOUTH, p1);

		f4.add(p1);
		f4.setVisible(true);
	}
}
