package com.lastminute.SalesTaxesCalculator;

public abstract class Item {
	
	protected String category;
	protected double price;
	protected String name;

	public Item(String category, double price) {
		this.category = category;
		this.price = price;
	}

	public Item(String name, String category, double price) {
		this.category = category;
		this.price = price;
	}

	public String getCategory() {
		return this.category;
	}
	
	public abstract double getPrice();
	
	public abstract double getTax();


}
