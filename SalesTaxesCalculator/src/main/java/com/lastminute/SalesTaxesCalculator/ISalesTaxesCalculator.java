package com.lastminute.SalesTaxesCalculator;

import com.lastminute.SalesTaxesCalculator.item.Item;

public interface ISalesTaxesCalculator {

	Double getTaxFromItem(String string);
	
	public void add(Item item);

	public Double getTotalPrice();
	
	public double getTotalTaxes();
	
}
