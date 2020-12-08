package gov.sandia.jess.example.pricing.demo;
import gov.sandia.jess.example.pricing.MysqlConnection.SqlConnection;
import gov.sandia.jess.example.pricing.model.CatalogItem;
import gov.sandia.jess.example.pricing.model.Customer;
import gov.sandia.jess.example.pricing.model.Order;
import gov.sandia.jess.example.pricing.model.OrderItem;
import gov.sandia.jess.example.pricing.model.CreditCard;
import gov.sandia.jess.example.pricing.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A toy implementation of the Database interface with some
 * hard-coded order data.
 */

public class DemoDatabase implements Database {

	private ArrayList items;
	private ArrayList itemsCard;
	private Map orders;
	private CreditCard cardB;
	private CreditCard cardL;
	private ArrayList myItems = new ArrayList();;
	private SqlConnection sql = new SqlConnection();

	public DemoDatabase() {
		createCreditCards();
		//createCatalogItems();
		createOrders();
		createMyCatalog();
	}
	public void createMyCatalog(){
		myItems = sql.readCatalog();
		for(int i = 0; i < myItems.size(); i++) {   
    		System.out.print(myItems.get(i));
		}  
	}
	
	private void createOrders() {
		orders = new HashMap();
		cardB = (CreditCard) itemsCard.get(0);
		cardL = (CreditCard) itemsCard.get(1);
		Customer customer = new Customer(6);
		ArrayList orderItems = new ArrayList();
		orderItems.add(new OrderItem("CD Writer", 1234, 199.99f, 1));
		orderItems.add(new OrderItem("AA Batteries", 4323, 4.99f, 2));
		orders.put(new Integer(123), new Order(orderItems, customer));
		
		customer = new Customer(1);
		orderItems = new ArrayList();
		orderItems.add(new OrderItem("Gold-tipped cable", 9876, 19.99f, 4));
		orders.put(new Integer(567), new Order(orderItems, customer));
		
		customer = new Customer(1);
		orderItems = new ArrayList();
		orderItems.add(new OrderItem("Incredibles DVD", 222123, 29.99f, 1));
		orders.put(new Integer(666), new Order(orderItems, customer));

		customer = new Customer(5);
		orderItems = new ArrayList();
		orderItems.add(new OrderItem("iphone12",  5555, 630f, 1));
		orders.put(new Integer(87), new Order(orderItems, customer, cardB));

		customer = new Customer(7);
		orderItems = new ArrayList();
		orderItems.add(new OrderItem("Samsung Note 11", 1111, 799f, 1));
		orders.put(new Integer(16), new Order(orderItems, customer, cardL));

		customer = new Customer(9);
		orderItems = new ArrayList();
		orderItems.add(new OrderItem("MacBook Air", 232323, 1273.91f, 1));
		orders.put(new Integer(5), new Order(orderItems, customer, cardB));
	}

	private void createCatalogItems() {
		items = new ArrayList();
		items.add(new CatalogItem("CD Writer", 1234, 199.99f));
		items.add(new CatalogItem("CD-RW Disks", 782321, 5.99f));
		items.add(new CatalogItem("AA Batteries", 4323, 4.99f));
		items.add(new CatalogItem("Gold-tipped cable", 9876, 19.99f));
		items.add(new CatalogItem("Amplifier", 34526, 399.99f));
		items.add(new CatalogItem("Incredibles DVD", 222123, 29.99f));		
		items.add(new CatalogItem("iPhone 11 Pro", 1212, 699f));
		items.add(new CatalogItem("Samsung Note 11", 1111, 799f));
		items.add(new CatalogItem("MacBook Air", 232323, 1273.91f));
	}

	private void createCreditCards(){
		itemsCard = new ArrayList();
		itemsCard.add(new CreditCard("Banamex", "Bank issues"));
		itemsCard.add(new CreditCard("Liverpool VISA", "Bank issues"));
	}
	public Collection getCatalogItems() {
		return items;
	}
	public Collection getItemsDB(){
		return myItems;
	}

	public Collection getCredit() {
		return itemsCard;
	}
	public Order getOrder(int orderNumber) {
		return (Order) orders.get(new Integer(orderNumber));
	}
	
}
