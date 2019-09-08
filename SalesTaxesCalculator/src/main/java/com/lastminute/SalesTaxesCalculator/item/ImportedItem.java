package com.lastminute.SalesTaxesCalculator.item;

@Deprecated
public class ImportedItem extends Item {

	public ImportedItem(String category, double price) {
		super(category, price);
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
