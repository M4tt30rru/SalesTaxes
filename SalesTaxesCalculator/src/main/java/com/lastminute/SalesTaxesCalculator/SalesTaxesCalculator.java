package com.lastminute.SalesTaxesCalculator;

public class SalesTaxesCalculator {

	public Double getTaxFromItem(Item item) {
		if(item.getCategory() != "food" && item.getCategory() != "book" && item.getCategory() != "medicine")
			return 10.0;
		return 0.0;
	}

}
