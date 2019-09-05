package com.lastminute.SalesTaxesCalculator;

public class Book extends Item {

	public Book(String category, double price) {
		super(category,price);
	}

	public Book(String name, String category, double price) {
		super(name, category,price);
	}

}
