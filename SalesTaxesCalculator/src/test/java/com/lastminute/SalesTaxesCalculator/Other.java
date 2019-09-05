package com.lastminute.SalesTaxesCalculator;

public class Other extends Item {
	
	private static final double TAX = 0.1;


	public Other(String name, double price) {
		super("other", price);
		this.name = name;
	}

	public double getPrice() {
		return this.price + this.price * 0.1;
	}

	public double getTax() {
		return TAX * 100;
	}

}
