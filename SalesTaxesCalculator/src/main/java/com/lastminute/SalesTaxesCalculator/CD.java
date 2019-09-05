package com.lastminute.SalesTaxesCalculator;

public class CD extends Item{

	public CD(String category, double price) {
		super(category, price);
	}

	public CD(String name, String category, double price) {
		super(name, category, price);	
	}
}
