package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.decorator.ImportedItemDecorator;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;
import com.lastminute.SalesTaxesCalculator.item.Book;
import com.lastminute.SalesTaxesCalculator.item.Food;
import com.lastminute.SalesTaxesCalculator.item.Item;
import com.lastminute.SalesTaxesCalculator.item.Medical;
import com.lastminute.SalesTaxesCalculator.item.Other;

public class SalesTaxesCalculatorTest {
	
//	INPUT:
//
//		Input 1:
//		1 book at 12.49
//		1 music CD at 14.99
//		1 chocolate bar at 0.85
	
	private SalesTaxesCalculator salesTaxesCalculator;
	private ICart cart;

	@Before
	public void setup() {
		cart = new Cart();
		salesTaxesCalculator = new SalesTaxesCalculator(cart);
	}
	
	@Ignore
	@Test
	public void should_return_zero_taxes_for_books() {
		IItem book = new ConcreteItem("book",12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book), equalTo(0.0));
	}
	
	@Ignore
	@Test
	public void should_return_10_taxes_for_cd() {
		Item cd = new Other("cd",14.99);
		assertThat(salesTaxesCalculator.getTaxFromItem(cd), equalTo(10.0));
	}
	
	@Ignore
	@Test
	public void should_return_zero_taxes_for_chocholate_bar() {
		Item chocolate_bar = new Food("chocolate bar",0.85);
		assertThat(salesTaxesCalculator.getTaxFromItem(chocolate_bar), equalTo(0.0));
	}
	
	@Test
	public void should_return_price_including_taxes_for_books() {
		IItem book = new ConcreteItem("book",12.49);
		assertThat(salesTaxesCalculator.getPrice(book), equalTo(12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_cd() {
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		double formula = 14.99 + 14.99 * 0.1;
		salesTaxesCalculator.add(tiid);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_chocholate_bar() {
		double price = 0.85;
		IItem chocolate_bar = new ConcreteItem("chocolate bar",price);
		// Item chocolate_bar = new Food("chocolate bar",price);
		assertThat(salesTaxesCalculator.getPrice(chocolate_bar), equalTo(price));
	}
	
	@Test
	public void should_return_price_including_taxes_for_more_items() {
		// Item chocolate_bar = new Food("chocolate bar",0.85);
		// Item cd = new Other("cd",14.99);
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(tiid);
		double formula = 14.99 + 14.99 * 0.1 + 0.85;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_two_different_items_strategy() {
//		Item chocolate_bar = new Food("chocolate bar",0.85);
//		Item cd = new Other("cd",14.99);
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(tiid);
		double formula = 14.99 + 14.99 * 0.1 + 0.85;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));

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
//		Item book = new Book("book",12.49);
//		Item chocolate_bar = new Food("chocolate bar",0.85);
//		Item cd = new Other("cd",14.99);
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		IItem book = new ConcreteItem("book",12.49);
		salesTaxesCalculator.add(book);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(tiid);
		double total = 29.83;
		assertThat(round(tiid.getFullPrice()), equalTo(round(14.99 + 0.1 * 14.99)));
		assertThat(round(book.getFullPrice()), equalTo(round(12.49)));
		assertThat(round(chocolate_bar.getFullPrice()), equalTo(round(0.85)));
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(total));
	}
	
	@Test
	public void should_return_taxes_for_input_1() {
//		Item book = new Book("book",12.49);
//		Item chocolate_bar = new Food("chocolate bar",0.85);
//		Item cd = new Other("cd",14.99);
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		IItem book = new ConcreteItem("book",12.49);
		salesTaxesCalculator.add(book);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(tiid);
		double total_taxes = round(14.99 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
	}
	
	@Test
	public void should_return_total_taxes() {
		// Item cd = new Other("cd",14.99);
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		salesTaxesCalculator.add(tiid);
		double total_taxes = round(14.99 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
	}
	
	@Test
	public void should_return_total_taxes_2() {
		//Item item = new Other("item",11.00);
		IItem item = new ConcreteItem("item",11.00);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(item);
		salesTaxesCalculator.add(tiid);
		double total_taxes = round(11.00 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
	}
	
	@Test
	public void should_return_price_including_taxes_for_three_different_items_strategy_2() {
//		Item book = new Book("book",12.49);
//		Item chocolate_bar = new Food("chocolate bar",0.85);
//		Item cd = new Other("cd",14.99);
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		IItem book = new ConcreteItem("book",12.49);
		salesTaxesCalculator.add(chocolate_bar);
		salesTaxesCalculator.add(tiid);
		salesTaxesCalculator.add(book);
		double formula = 14.99 + 14.99 * 0.1 + 0.85 + 12.49;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_book() {
		// Item book = new Book("book",12.49, true);
		IItem book = new ConcreteItem("book",12.49);
		ImportedItemDecorator iid = new ImportedItemDecorator(book);
		salesTaxesCalculator.add(iid);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(12.49 + 12.49 * 0.05)));
	}
	
//	Input 2:
//		1 imported box of chocolates at 10.00 --> 10.00 + 10.00*5% = 10.50
//		1 imported bottle of perfume at 47.50 --> 47.50 + 47.50*0.1 + 47.50*0.05 = 52.25 + 2.37 = 54.62
	
	@Test
	public void should_return_price_including_taxes_for_imported_chocolate() {
		// Item chocolate = new Food("box of chocolate",10.00, true);
		IItem chocolate = new ConcreteItem("box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);
		salesTaxesCalculator.add(iid);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(10.00 + 10.00 * 0.05));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_perfume() {
		// Item perfume = new Other("bottle of perfume",47.50, true);
		IItem perfume = new ConcreteItem("bottle of perfume",47.50);
		salesTaxesCalculator.add(new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume)));
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(47.50 + 47.50 * 0.1 + 47.50 * 0.05)));
	}
	
//	Output 2:
//	1 imported box of chocolates: 10.50
//	1 imported bottle of perfume: 54.65
//	Sales Taxes: 7.65
//	Total: 65.15
	
	@Test
	public void should_return_price_including_taxes_for_2nd_input() {
		// Item chocolate = new Food("box of chocolate",10.00, true);
		// Item perfume = new Other("bottle of perfume",47.50, true);
		IItem chocolate = new ConcreteItem("box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);

		IItem perfume = new ConcreteItem("bottle of perfume",47.50);
		ImportedItemDecorator itiid = new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume));

		salesTaxesCalculator.add(iid);
		salesTaxesCalculator.add(itiid);
		double formula = (10.00 + 10.00 * 0.05) 
				+ (47.50 + 47.50 * 0.1 + 47.50 * 0.05);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_taxes_for_2nd_input() {
//		Item chocolate = new Food("box of chocolate",10.00, true);
//		Item perfume = new Other("bottle of perfume",47.50, true);
		
		IItem chocolate = new ConcreteItem("box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);

		IItem perfume = new ConcreteItem("bottle of perfume",47.50);
		ImportedItemDecorator itiid = new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume));
		
		salesTaxesCalculator.add(iid);
		salesTaxesCalculator.add(itiid);
		double taxes = (10.00 * 0.05) 
				+ (47.50 * 0.1 + 47.50 * 0.05);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(round(taxes)));
	}
	
//	Input 3:
//		1 imported bottle of perfume at 27.99
//		1 bottle of perfume at 18.99
//		1 packet of headache pills at 9.75
//		1 box of imported chocolates at 11.25
	
//	Output 3:
//		1 imported bottle of perfume: 32.19
//		1 bottle of perfume: 20.89
//		1 packet of headache pills: 9.75
//		1 imported box of chocolates: 11.85
//		Sales Taxes: 6.70
//		Total: 74.68
	
	@Test
	public void should_return_price_including_taxes_for_3rd_input() throws Exception {
		
		// Item imported_perfume = new Other("bottle of perfume",27.99,true);
		// Item perfume = new Other("bottle of perfume",18.99,false);
		// Item headache_pills = new Medical("packet of headache pills", 9.75, false);
		// Item imported_chocolate = new Food("box of imported chocolates", 11.25, true);
		
		IItem imported_perfume = new ConcreteItem("bottle of perfume",27.99);
		ImportedItemDecorator imported_perfume_decorator = new ImportedItemDecorator(new TaxIncludedItemDecorator(imported_perfume));
		
		IItem perfume = new ConcreteItem("bottle of perfume",18.99);
		TaxIncludedItemDecorator perfume_decorator = new TaxIncludedItemDecorator(perfume);
		
		IItem headache_pills = new ConcreteItem("packet of headache pills", 9.75);
		
		IItem imported_chocolate = new ConcreteItem("box of imported chocolates", 11.25);
		ImportedItemDecorator imported_chocolate_decorator = new ImportedItemDecorator(imported_chocolate);
		
		salesTaxesCalculator.add(imported_perfume_decorator);
		salesTaxesCalculator.add(imported_chocolate_decorator);
		salesTaxesCalculator.add(perfume_decorator);
		salesTaxesCalculator.add(headache_pills);

		double formula = (27.99 + 27.99 * 0.1 + 27.99 * 0.05) + 
				(18.99 + 18.99 * 0.1) + 9.75 + (11.25 + 11.25 * 0.05);
		
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(formula));
	}
	
	@Test
	public void should_return_price_taxes_for_3rd_input() throws Exception {
		
		double price_imported_perfume = 27.99;
//		Item imported_perfume = new Other("bottle of perfume",price_imported_perfume,true);
		double price_perfume = 18.99;
//		Item perfume = new Other("bottle of perfume",price_perfume,false);
		double price_pills = 9.75;
//		Item headache_pills = new Medical("packet of headache pills", price_pills, false);
		double price_chocolate = 11.25;
//		Item imported_chocolate = new Food("box of imported chocolates", price_chocolate, true);
//		
//		salesTaxesCalculator.add(imported_perfume);
//		salesTaxesCalculator.add(imported_chocolate);
//		salesTaxesCalculator.add(perfume);
//		salesTaxesCalculator.add(headache_pills);
		
		IItem imported_perfume = new ConcreteItem("bottle of perfume",27.99);
		ImportedItemDecorator imported_perfume_decorator = new ImportedItemDecorator(new TaxIncludedItemDecorator(imported_perfume));
		
		IItem perfume = new ConcreteItem("bottle of perfume",18.99);
		TaxIncludedItemDecorator perfume_decorator = new TaxIncludedItemDecorator(perfume);
		
		IItem headache_pills = new ConcreteItem("packet of headache pills", 9.75);
		
		IItem imported_chocolate = new ConcreteItem("box of imported chocolates", 11.25);
		ImportedItemDecorator imported_chocolate_decorator = new ImportedItemDecorator(imported_chocolate);
		
		salesTaxesCalculator.add(imported_perfume_decorator);
		salesTaxesCalculator.add(imported_chocolate_decorator);
		salesTaxesCalculator.add(perfume_decorator);
		salesTaxesCalculator.add(headache_pills);

		double taxes = (price_imported_perfume * 0.1 + price_imported_perfume * 0.05) + 
				(price_perfume * 0.1) + (price_chocolate * 0.05);
		
		assertThat(salesTaxesCalculator.getTotalTaxes(), equalTo(round(taxes)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_medical() throws Exception {
		
		// Item headache_pills = new Medical("packet of headache pills", 9.75, true);
		IItem headache_pills = new ConcreteItem("packet of headache pills", 9.75);
		ImportedItemDecorator headache_pills_decorator = new ImportedItemDecorator(headache_pills);
		
		salesTaxesCalculator.add(headache_pills_decorator);

		double formula =  9.75 + 9.75 * 0.05;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
}
