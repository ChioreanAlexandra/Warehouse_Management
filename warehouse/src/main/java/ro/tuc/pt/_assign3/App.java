package ro.tuc.pt._assign3;

import dataLayer.*;
import java.lang.reflect.*;
import java.util.List;

import model.*;
import presentation.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       /*Clients c=new Clients("user","pass","Ale",12);
        Product pp=new Product("mere",12,2);
        Product produs=new Product("pere",12,3);
        produs.setId(6);
        ClientDAO cl=new ClientDAO();
       // cl.getObjectDetalies();
        List<Clients> q=cl.findAll();
        for(Clients i:q)
        {
        	System.out.println(i.toString());
        }
        ProductDAO p=new ProductDAO();
        List<Product> qq=p.findAll();
        for(Product i:qq)
        {
        	System.out.println(i.toString());
        }
        Clients client=cl.selectClient(c.getUsername(), c.getPassword());*/
    	View v=new View();
    	Controller c=new Controller(v);
    	
    }
}
