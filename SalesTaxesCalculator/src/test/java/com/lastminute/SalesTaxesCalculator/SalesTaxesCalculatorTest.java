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
		Item book = new Item("book","book",12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book), equalTo(0.0));
	}
	
	
	@Test
	public void should_return_10_taxes_for_cd() {
		Item cd = new Item("cd","electronics",14.99);
		assertThat(salesTaxesCalculator.getTaxFromItem(cd), equalTo(10.0));
	}
	
	@Test
	public void should_return_zero_taxes_for_chocholate_bar() {
		Item chocolate_bar = new Item("chocolate bar","food",0.85);
		assertThat(salesTaxesCalculator.getTaxFromItem(chocolate_bar), equalTo(0.0));
	}
	
	@Test
	public void should_return_price_including_taxes_for_books() {
		Item book = new Item("book","book",12.49);
		assertThat(salesTaxesCalculator.getPrice(book), equalTo(12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_cd() {
		Item cd = new Item("cd","electronics",14.99);
		assertThat(salesTaxesCalculator.getPrice(cd), equalTo(14.99 + 14.99*0.1));
	}
	
	@Test
	public void should_return_price_including_taxes_for_chocholate_bar() {
		double price = 0.85;
		Item chocolate_bar = new Item("chocolate bar","food",price);
		assertThat(salesTaxesCalculator.getPrice(chocolate_bar), equalTo(price));
	}

}
