package com.lastminute.SalesTaxesCalculator.decorator;

public class ConcreteItem implements IItem {
	
	private static double TAX = 0;
	
	protected String category;
	protected double price;
	protected String name;
	public boolean imported;
	
	public ConcreteItem(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public ConcreteItem(String name, String category, double price) {
		this.category = category;
		this.price = price;
		this.imported = false;
		this.name = name;
	}

	public String getCategory() {
		return this.category;
	}
		
	public Double getTax() {
		return TAX * 100;
	}
	
	private double addTaxes() {
		return TAX == 0.0 ? TAX : this.price * TAX;
	}

	protected double addCustomsIfImported() {
		return imported ? round(this.price * 0.05) : 0;
	}
	
	public Double getFullPrice() {
		return this.price;
		// return round(this.price + getAllTaxes());
	}

	public Double getPrice() {
		return this.price;
	}

	public Double getAllTaxes() {
		return 0.0; // round(addTaxes() + addCustomsIfImported());
	}

	public void setPrice(double price) {
		this.price = price;		
	}

	protected double round(double input) {
		return (double) Math.round(input*100.00)/100.00;
	}

	@Override
	public String getName() {
		return this.name ;
	}
	
	@Override	
	public String toString() {
		return "1 " + getName() + " at " + getPrice();
	}

}
