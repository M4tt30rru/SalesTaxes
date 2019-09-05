package com.lastminute.SalesTaxesCalculator;

public class Item {
	
	String category;

	public Item(String category, double price) {
		this.category = category;
	}

	public Item(String name, String category, double price) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}

}
