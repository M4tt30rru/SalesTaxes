package com.lastminute.SalesTaxesCalculator;

public class Other extends Item {
	
	private static double TAX = 0.1;

	public Other(String name, double price) {
		super("other", price);
		this.name = name;
	}

	public Other(String name, double price, boolean imported) {
		this(name,price);
		this.imported = imported;
	}

	public double getPrice() {
		return this.price + addTaxes() + addCustomsIfImported();
	}

	protected double addTaxes() {
		return TAX == 0.0 ? TAX : this.price * TAX;
	}

	public double getTax() {
		return TAX * 100;
	}

}
