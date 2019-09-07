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
		return imported ? round(this.price * 0.05) : 0;
	}

	public double getFullPrice() {
		return round(this.price + getAllTaxes());
	}

	public double getPrice() {
		return this.price;
	}

	public double getAllTaxes() {
		return round(addTaxes() + addCustomsIfImported());
	}

	public void setPrice(double price) {
		this.price = price;		
	}

	protected double round(double input) {
		return (double) Math.round(input*100.0)/100.0;
	}
}
