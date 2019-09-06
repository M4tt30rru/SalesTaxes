package com.lastminute.SalesTaxesCalculator;

public class Medical extends Item {

	public Medical(String category, double price) {
		super(category, price);
	}

	public Medical(String name, double price, boolean imported) {
		this(name,price);
		this.imported = imported;
	}

	@Override
	public double getTax() {
		return 0;
	}

	@Override
	protected double addTaxes() {
		return 0;
	}

}
