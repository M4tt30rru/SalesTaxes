package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class SalesTaxesCalculatorTest {
	
//	INPUT:
//
//		Input 1:
//		1 book at 12.49
//		1 music CD at 14.99
//		1 chocolate bar at 0.85

	@Test
	public void should_return_zero_taxes_for_books() {
		SalesTaxesCalculator salesTaxesCalculator = new SalesTaxesCalculator();
		Book book = new Book(12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book), equalTo(0.0));
	}
	
	
	@Test
	public void should_return_10_taxes_for_cd() {
		SalesTaxesCalculator salesTaxesCalculator = new SalesTaxesCalculator();
		CD cd = new CD(14.99);
		assertThat(salesTaxesCalculator.getTaxFromItem(cd), equalTo(10.0));
	}
	
	@Test
	public void should_return_zero_taxes_for_chocholate_bar() {
		SalesTaxesCalculator salesTaxesCalculator = new SalesTaxesCalculator();
		ChocolateBar chocolate_bar = new ChocolateBar(0.85);
		assertThat(salesTaxesCalculator.getTaxFromItem(chocolate_bar), equalTo(0.0));
	}

}
