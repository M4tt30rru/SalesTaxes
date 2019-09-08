package com.lastminute.SalesTaxesCalculator.decorator;

public class TaxIncludedItemDecorator extends DecoratorAbstraction {

	public TaxIncludedItemDecorator(IItem decoratedTarget) {
		super(decoratedTarget);
	}

	@Override
	public Double getTax() {
		return 0.1;
	}

	@Override
	public Double getPrice() {
		return this.decoratedTarget.getPrice();
	}

	@Override
	public Double getFullPrice() {
		return this.decoratedTarget.getFullPrice() + getTax() * this.decoratedTarget.getPrice();
	}

	@Override
	public Double getAllTaxes() {
		return getTax() * this.decoratedTarget.getPrice() + this.decoratedTarget.getAllTaxes();
	}
	
	@Override
	public String getName() {
		return this.decoratedTarget.getName();
	}
	
	@Override	
	public String toString() {
		return "1 " + decoratedTarget.getName() + " at " + decoratedTarget.getPrice();
	}
	
}
