package com.lastminute.SalesTaxesCalculator;

public abstract class Item {
	
	String category;

	public Item(String category, double price) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}

}
