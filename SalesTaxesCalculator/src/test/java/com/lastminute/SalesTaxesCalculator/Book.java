package com.lastminute.SalesTaxesCalculator;

public class Book extends Item {
	
	private static final double TAX = 0;


	public Book(String name, double price) {
		super("book", price);
		this.name = name;

	}

	public double getPrice() {
		return this.price;
	}

	public double getTax() {
		return TAX * 100;
	}

}
