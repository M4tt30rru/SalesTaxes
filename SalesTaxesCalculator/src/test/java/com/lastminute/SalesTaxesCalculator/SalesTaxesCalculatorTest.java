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
		Item book = new Book("book",12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book), equalTo(0.0));
	}
	
	
	@Test
	public void should_return_10_taxes_for_cd() {
		Item cd = new Other("cd",14.99);
		assertThat(salesTaxesCalculator.getTaxFromItem(cd), equalTo(10.0));
	}
	
	@Test
	public void should_return_zero_taxes_for_chocholate_bar() {
		Item chocolate_bar = new Food("chocolate bar",0.85);
		assertThat(salesTaxesCalculator.getTaxFromItem(chocolate_bar), equalTo(0.0));
	}
	
	@Test
	public void should_return_price_including_taxes_for_books() {
		Item book = new Book("book",12.49);
		assertThat(salesTaxesCalculator.getPrice(book), equalTo(12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_cd() {
		Item cd = new Other("cd",14.99);
		assertThat(salesTaxesCalculator.getPrice(cd), equalTo(14.99 + 14.99 * 0.1));
	}
	
	@Test
	public void should_return_price_including_taxes_for_chocholate_bar() {
		double price = 0.85;
		Item chocolate_bar = new Food("chocolate bar",price);
		assertThat(salesTaxesCalculator.getPrice(chocolate_bar), equalTo(price));
	}
	
	@Test
	public void should_return_price_including_taxes_for_more_items() {
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(14.99 + 14.99 * 0.1 + 0.85));
	}
	
	@Test
	public void should_return_price_including_taxes_for_two_different_items_strategy() {
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(14.99 + 14.99*0.1 + 0.85));

	}
	
	@Test
	public void should_return_price_including_taxes_for_three_different_items_strategy_2() {
		Item book = new Book("book",12.49);
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		salesTaxesCalculator.add(book);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(14.99 + 14.99*0.1 + 0.85 + 12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_book() {
		double price = 12.49;
		Item book = new Book("book",price, true);
		salesTaxesCalculator.add(book);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(price + price * 0.05));
	}

}
