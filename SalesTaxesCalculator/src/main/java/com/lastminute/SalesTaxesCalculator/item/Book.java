package com.lastminute.SalesTaxesCalculator.item;

public class Book extends Item {
	
	private static double TAX = 0;
	
	public Book(String name, double price) {
		super("book", price);
		this.name = name;

	}

	public Book(String name, double price, boolean imported) {
		this("book", price);
		this.imported = imported;
	}

	public double getFullPrice() {
		return this.price  + addTaxes() + addCustomsIfImported();
	}

	public double getTax() {
		return TAX * 100;
	}

	@Override
	protected double addTaxes() {
		return TAX == 0.0 ? TAX : this.price * TAX;
	}

}
