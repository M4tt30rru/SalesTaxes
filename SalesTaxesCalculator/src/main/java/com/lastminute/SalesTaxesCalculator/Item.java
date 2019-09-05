package com.lastminute.SalesTaxesCalculator;

public class Item {
	
	String category;
	private double price;

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

	public double getPrice() {
		return this.price;
	}

}
