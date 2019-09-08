package com.lastminute.SalesTaxesCalculator.item;

@Deprecated
public class Medical extends Item {
	
	private static double TAX = 0;

	public Medical(String category, double price) {
		super(category, price);
	}

	public Medical(String name, double price, boolean imported) {
		this(name,price);
		this.imported = imported;
	}

	@Override
	public double getTax() {
		return TAX * 100;
	}

	@Override
	protected double addTaxes() {
		return TAX == 0.0 ? TAX : this.price * TAX;
	}

}
