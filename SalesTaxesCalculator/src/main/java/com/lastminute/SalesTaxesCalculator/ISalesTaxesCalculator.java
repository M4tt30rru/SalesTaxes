package com.lastminute.SalesTaxesCalculator;

public interface ISalesTaxesCalculator {

	Double getTaxFromItem(String string);
	
	public void add(Item item);

	public Double getTotalPrice();
	
	public double getTotalTaxes();
	
}
