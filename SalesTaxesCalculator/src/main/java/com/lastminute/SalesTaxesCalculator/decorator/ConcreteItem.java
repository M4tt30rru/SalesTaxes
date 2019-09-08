package com.lastminute.SalesTaxesCalculator.decorator;

public class ConcreteItem implements IItem {
	
	private static double TAX = 0;
	
	protected String category;
	protected double price;
	protected String name;
	public boolean imported;

	private Integer quantity;
	
	public ConcreteItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = new Integer(1);
	}

	public ConcreteItem(String name, String category, double price) {
		this.category = category;
		this.price = price;
		this.imported = false;
		this.name = name;
	}

	public ConcreteItem(int quantity, String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;	
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
		return getPrice() + getTax() * getPrice();
		// return round(this.price + getAllTaxes());
	}

	public Double getPrice() {
		return this.quantity * this.price;
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
		return "" + quantity + " " + getName() + " at " + getPrice();
	}

	@Override
	public Integer getQuantity() {
		return this.quantity;
	}
	
	
}
