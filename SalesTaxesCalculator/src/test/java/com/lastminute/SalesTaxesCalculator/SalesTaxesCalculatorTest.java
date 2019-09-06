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
	
//	Output 1:
//	1 book : 12.49
//	1 music CD: 16.49
//	1 chocolate bar: 0.85
//	Sales Taxes: 1.50
//	Total: 29.83
	
	@Test
	public void testSumWithRounding() throws Exception {
		assertThat(12.49 + 16.49 + 0.85, equalTo(29.83));
		double input = 14.99 + 0.1 * 14.99;
		assertThat(round(input), equalTo(16.49));
	}

	private double round(double input) {
		return (double) Math.round(input*100.0)/100.0;
	}
	
	@Test
	public void should_return_price_including_taxes_for_input_1() {
		Item book = new Book("book",12.49);
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(book);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		double total = 29.83;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(total));
	}
	
	@Test
	public void should_return_taxes_for_input_1() {
		Item book = new Book("book",12.49);
		Item chocolate_bar = new Food("chocolate bar",0.85);
		Item cd = new Other("cd",14.99);
		salesTaxesCalculator.add(book);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(cd);
		double total_taxes = round(14.99 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
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
	
	
//	Input 3:
//		1 imported bottle of perfume at 27.99
//		1 bottle of perfume at 18.99
//		1 packet of headache pills at 9.75
//		1 box of imported chocolates at 11.25
	
	@Test
	public void should_return_price_including_taxes_for_3rd_input() throws Exception {
		
		double price_imported_perfume = 27.99;
		Item imported_perfume = new Other("bottle of perfume",price_imported_perfume,true);
		double price_perfume = 18.99;
		Item perfume = new Other("bottle of perfume",price_perfume,false);
		double price_pills = 9.75;
		Item headache_pills = new Medical("packet of headache pills", price_pills, false);
		double price_chocolate = 11.25;
		Item imported_chocolate = new Food("box of imported chocolates", price_chocolate, true);
		
		salesTaxesCalculator.add(imported_perfume);
		salesTaxesCalculator.add(imported_chocolate);
		salesTaxesCalculator.add(perfume);
		salesTaxesCalculator.add(headache_pills);

		double formula = (price_imported_perfume + price_imported_perfume * 0.1 + price_imported_perfume * 0.05) + 
				(price_perfume + price_perfume * 0.1) + price_pills + (price_chocolate + price_chocolate * 0.05);
		
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_medical() throws Exception {
		
		double price_pills = 9.75;
		Item headache_pills = new Medical("packet of headache pills", price_pills, true);
		
		salesTaxesCalculator.add(headache_pills);

		double formula =  price_pills + price_pills * 0.05;
		
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));
	}



}
