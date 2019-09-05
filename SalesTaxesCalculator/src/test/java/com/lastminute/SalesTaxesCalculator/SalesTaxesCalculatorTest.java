package com.lastminute.SalesTaxesCalculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class SalesTaxesCalculatorTest {
	
//	INPUT:
//
//		Input 1:
//		1 book at 12.49
//		1 music CD at 14.99
//		1 chocolate bar at 0.85

	@Test
	public void test() {
		SalesTaxesCalculator salesTaxesCalculator = new SalesTaxesCalculator();
		Book book = new Book(12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book),equalTo(0.0));
	}

}
