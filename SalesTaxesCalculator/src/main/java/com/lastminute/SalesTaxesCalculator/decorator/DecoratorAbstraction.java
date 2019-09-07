package com.lastminute.SalesTaxesCalculator.decorator;

public abstract class DecoratorAbstraction implements IItem {
	
	protected IItem decoratedTarget;

	public DecoratorAbstraction(IItem decoratedTarget) {
		this.decoratedTarget = decoratedTarget;
	}
	
}
