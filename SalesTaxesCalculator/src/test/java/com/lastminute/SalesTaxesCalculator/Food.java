package com.lastminute.SalesTaxesCalculator;

public class Food extends Item {
	
	private static final double TAX = 0;

	public Food(String name, double price) {
		super("food", price);
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public double getTax() {
		return TAX * 100;
	}

}
