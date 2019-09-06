package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

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
		double formula = 14.99 + 14.99 * 0.1 + 0.85;
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));
	}
	
	@Test
	public void should_return_price_including_taxes_for_two_different_items_strategy() {
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		double formula = 14.99 + 14.99*0.1 + 0.85;
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));

	}
	
	@Test
	public void should_return_price_including_taxes_for_three_different_items_strategy_2() {
		Item book = new Book("book",12.49);
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		salesTaxesCalculator.add(book);
		double formula = 14.99 + 14.99 * 0.1 + 0.85 + 12.49;
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_book() {
		double price = 12.49;
		Item book = new Book("book",price, true);
		salesTaxesCalculator.add(book);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(price + price * 0.05));
	}
	
//	Input 2:
//		1 imported box of chocolates at 10.00 --> 10.00 + 10.00*5% = 10.50
//		1 imported bottle of perfume at 47.50 --> 47.50 + 47.50*0.1 + 47.50*0.05 = 52.25 + 2.37 = 54.62
	
	@Test
	public void should_return_price_including_taxes_for_imported_chocolate() {
		double price = 10.00;
		Item chocolate = new Food("box of chocolate",price, true);
		salesTaxesCalculator.add(chocolate);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(price + price * 0.05));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_perfume() {
		double price = 47.50;
		Item perfume = new Other("bottle of perfume",price, true);
		salesTaxesCalculator.add(perfume);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(price + price * 0.1 + price * 0.05));
	}
	
	@Test
	public void should_return_price_including_taxes_for_2nd_input() {
		double price_chocolate = 10.00;
		double price_perfume = 47.50;
		Item chocolate = new Food("box of chocolate",price_chocolate, true);
		Item perfume = new Other("bottle of perfume",price_perfume, true);
		salesTaxesCalculator.add(perfume);
		salesTaxesCalculator.add(chocolate);
		double formula = (price_chocolate + price_chocolate * 0.05) 
				+ (price_perfume + price_perfume * 0.1 + price_perfume * 0.05);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));
	}

	

}
