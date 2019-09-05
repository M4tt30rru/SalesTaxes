package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SalesTaxesCalculatorTest {
	
//	INPUT:
//
//		Input 1:
//		1 book at 12.49
//		1 music CD at 14.99
//		1 chocolate bar at 0.85
	
	private SalesTaxesCalculator salesTaxesCalculator;

	@Before
	public void setup() {
		salesTaxesCalculator = new SalesTaxesCalculator();
	}

	@Test
	public void should_return_zero_taxes_for_books() {
		Book book = new Book("book",12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book), equalTo(0.0));
	}
	
	
	@Test
	public void should_return_10_taxes_for_cd() {
		CD cd = new CD("cd",14.99);
		assertThat(salesTaxesCalculator.getTaxFromItem(cd), equalTo(10.0));
	}
	
	@Test
	public void should_return_zero_taxes_for_chocholate_bar() {
		ChocolateBar chocolate_bar = new ChocolateBar("food",0.85);
		assertThat(salesTaxesCalculator.getTaxFromItem(chocolate_bar), equalTo(0.0));
	}

}
