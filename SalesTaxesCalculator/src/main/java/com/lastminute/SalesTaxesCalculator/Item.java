package com.lastminute.SalesTaxesCalculator;

public abstract class Item {
	
	protected String category;
	protected double price;
	protected String name;
	public boolean imported;

	public Item(String category, double price) {
		this.category = category;
		this.price = price;
	}

	public Item(String name, String category, double price) {
		this.category = category;
		this.price = price;
		this.imported = false;
	}

	public String getCategory() {
		return this.category;
	}
		
	public abstract double getTax();
	
	protected abstract double addTaxes();

	protected double addCustomsIfImported() {
		return imported ? this.price * 0.05 : 0;
	}

	public double getFullPrice() {
		return this.price + addTaxes() + addCustomsIfImported();
	}

	public double getPrice() {
		return this.price;
	}


}
