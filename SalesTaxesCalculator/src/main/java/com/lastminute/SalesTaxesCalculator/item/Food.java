package com.lastminute.SalesTaxesCalculator.item;

public class Food extends Item {
	
	private static double TAX = 0;

	public Food(String name, double price) {
		super("food", price);
		this.name = name;
	}

	public Food(String name, double price, boolean imported) {
		this(name, price);
		this.imported = imported;
	}

	public double getTax() {
		return TAX * 100;
	}

	@Override
	public double addTaxes() {
		return TAX == 0.0 ? TAX : this.price * TAX;
	}

}
