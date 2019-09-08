package com.lastminute.SalesTaxesCalculator.decorator;

//This is the interface target class (decoratee) and all decorators
//need to implement.
public interface IItem {

	Double getTax();

	Double getPrice();

	Double getFullPrice();

	Double getAllTaxes();

	String getName();
	
}

