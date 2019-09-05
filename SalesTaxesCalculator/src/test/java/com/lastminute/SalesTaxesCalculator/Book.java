package com.lastminute.SalesTaxesCalculator;

public class Book extends Item {
	
	private static final double TAX = 0;
	private boolean imported;


	public Book(String name, double price) {
		super("book", price);
		this.name = name;

	}

	public Book(String name, double price, boolean imported) {
		this(name, price);
		this.imported = imported;
	}

	public double getPrice() {
		return this.price + (imported ? this.price * 0.05 : 0);
	}

	public double getTax() {
		return TAX * 100;
	}

}
