package com.lastminute.SalesTaxesCalculator.item;

/**
 * Class that contains items which are not either books, medical products or food
 * @TODO  add quantity
 * @TODO invert the use of constructors: let 2 parameters constructor call the three one with default imported = false
 * @author matteo
 *
 */
@Deprecated
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

//	public double getFullPrice() {
//		return this.price + getAllTaxes();
//	}

	protected double addTaxes() {
		return TAX == 0.0 ? TAX : round(this.price * TAX);
	}

	public double getTax() {
		return TAX * 100;
	}
	
}
