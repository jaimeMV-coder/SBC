package gov.sandia.jess.example.pricing;

import gov.sandia.jess.example.pricing.model.Order;

import java.util.Collection;

public interface Database {
	public Collection getCatalogItems();
	public Collection getCredit();
	public Order getOrder(int orderNumber);
}