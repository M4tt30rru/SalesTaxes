package com.lastminute.SalesTaxesCalculator.decorator;

public class ImportedItemDecorator extends DecoratorAbstraction {

	public ImportedItemDecorator(IItem decoratedTarget) {
		super(decoratedTarget);
	}

	@Override
	public Double getTax() {
		return 0.05;
	}

	@Override
	public Double getPrice() {
		return this.decoratedTarget.getPrice();
	}

	@Override
	public Double getFullPrice() {
		return this.decoratedTarget.getFullPrice() + getTax() * this.decoratedTarget.getPrice() ;
	}

	@Override
	public Double getAllTaxes() {
		return getTax() * this.decoratedTarget.getPrice() + this.decoratedTarget.getAllTaxes();
	}
}
