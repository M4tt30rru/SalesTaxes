package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.decorator.ImportedItemDecorator;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;

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
		salesTaxesCalculator = new SalesTaxesCalculator();
	}
	
	@Ignore
	@Test
	public void should_return_zero_taxes_for_books() {
		IItem book = new ConcreteItem("book",12.49);
		assertThat(salesTaxesCalculator.getTaxFromItem(book), equalTo(0.0));
	}
	
	@Test
	public void should_return_price_including_taxes_for_books() {
		IItem book = new ConcreteItem("book",12.49);
		assertThat(salesTaxesCalculator.getPrice(book), equalTo(12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_1_book() {
		IItem book = new ConcreteItem(1,"book",12.49);
		assertThat(salesTaxesCalculator.getPrice(book), equalTo(12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_2_books() {
		int quantity = 2;
		IItem book = new ConcreteItem(quantity,"book",12.49);
		assertThat(salesTaxesCalculator.getPrice(book), equalTo(quantity * 12.49));
	}
	
	@Test
	public void should_return_price_including_taxes_for_cd() {
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		double formula = 14.99 + 14.99 * 0.1;
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_3_cds() {
		double price = 14.99;
		Integer quantity = 3;
		IItem cd = new ConcreteItem(quantity, "cd", price);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		double formula = quantity * (price + price * 0.1);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(formula));
	}
	
	@Test
	public void should_return_price_including_taxes_for_chocholate_bar() {
		double price = 0.85;
		IItem chocolate_bar = new ConcreteItem("chocolate bar",price);
		assertThat(salesTaxesCalculator.getPrice(chocolate_bar), equalTo(price));
	}
	
	@Test
	public void should_return_price_including_taxes_for_more_items() {

		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		cart.add(chocolate_bar);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double formula = 14.99 + 14.99 * 0.1 + 0.85;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_two_different_items_strategy() {

		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		cart.add(chocolate_bar);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double formula = 14.99 + 14.99 * 0.1 + 0.85;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_two_different_items_different_quantity() {
		int quantity_cd = 3;
		IItem cd = new ConcreteItem(quantity_cd,"cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		int quantity_chocolate = 2;
		IItem chocolate_bar = new ConcreteItem(quantity_chocolate,"chocolate bar",0.85);
		cart.add(chocolate_bar);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double formula = quantity_cd * (14.99 + 14.99 * 0.1) + quantity_chocolate * 0.85;
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

		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		IItem book = new ConcreteItem("book",12.49);
		cart.add(book);
		cart.add(chocolate_bar);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double total = 29.83;
		assertThat(round(tiid.getFullPrice()), equalTo(round(14.99 + 0.1 * 14.99)));
		assertThat(round(book.getFullPrice()), equalTo(round(12.49)));
		assertThat(round(chocolate_bar.getFullPrice()), equalTo(round(0.85)));
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(total));
	}
	
	@Test
	public void should_return_taxes_for_input_1() {

		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		IItem book = new ConcreteItem("book",12.49);
		cart.add(book);
		cart.add(chocolate_bar);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double total_taxes = round(14.99 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
	}
	
	@Test
	public void should_return_total_taxes() {

		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double total_taxes = round(14.99 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
	}
	
	@Test
	public void should_return_total_taxes_2() {

		IItem item = new ConcreteItem("item",11.00);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(item);
		cart.add(tiid);
		salesTaxesCalculator.setCart(cart);
		double total_taxes = round(11.00 * 0.1);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(total_taxes));
	}
	
	@Test
	public void should_return_price_including_taxes_for_three_different_items_strategy_2() {

		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator tiid = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		IItem book = new ConcreteItem("book",12.49);
		cart.add(chocolate_bar);
		cart.add(tiid);
		cart.add(book);
		salesTaxesCalculator.setCart(cart);
		double formula = 14.99 + 14.99 * 0.1 + 0.85 + 12.49;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_book() {

		IItem book = new ConcreteItem("book",12.49);
		ImportedItemDecorator iid = new ImportedItemDecorator(book);
		cart.add(iid);
		salesTaxesCalculator.setCart(cart);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(12.49 + 12.49 * 0.05)));
	}
	
//	Input 2:
//		1 imported box of chocolates at 10.00 --> 10.00 + 10.00*5% = 10.50
//		1 imported bottle of perfume at 47.50 --> 47.50 + 47.50*0.1 + 47.50*0.05 = 52.25 + 2.37 = 54.62
	
	@Test
	public void should_return_price_including_taxes_for_imported_chocolate() {

		IItem chocolate = new ConcreteItem("box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);
		cart.add(iid);
		salesTaxesCalculator.setCart(cart);
		assertThat(salesTaxesCalculator.getTotalPrice(), equalTo(10.00 + 10.00 * 0.05));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_perfume() {

		IItem perfume = new ConcreteItem("bottle of perfume",47.50);
		cart.add(new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume)));
		salesTaxesCalculator.setCart(cart);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(47.50 + 47.50 * 0.1 + 47.50 * 0.05)));
	}
	
//	Output 2:
//	1 imported box of chocolates: 10.50
//	1 imported bottle of perfume: 54.65
//	Sales Taxes: 7.65
//	Total: 65.15
	
	@Test
	public void should_return_price_including_taxes_for_2nd_input() {

		IItem chocolate = new ConcreteItem("box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);

		IItem perfume = new ConcreteItem("bottle of perfume",47.50);
		ImportedItemDecorator itiid = new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume));

		cart.add(iid);
		cart.add(itiid);
		salesTaxesCalculator.setCart(cart);

		double formula = (10.00 + 10.00 * 0.05) 
				+ (47.50 + 47.50 * 0.1 + 47.50 * 0.05);
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_taxes_for_2nd_input() {
		
		IItem chocolate = new ConcreteItem("box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);

		IItem perfume = new ConcreteItem("bottle of perfume",47.50);
		ImportedItemDecorator itiid = new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume));
		
		cart.add(iid);
		cart.add(itiid);
		salesTaxesCalculator.setCart(cart);

		double taxes = (10.00 * 0.05) 
				+ (47.50 * 0.1 + 47.50 * 0.05);
		assertThat(round(salesTaxesCalculator.getTotalTaxes()), equalTo(round(taxes)));
	}
	
	@Test
	public void should_return_taxes_for_2nd_input_multiple_items() {
		
		int chocolate_quantity = 2;
		IItem chocolate = new ConcreteItem(chocolate_quantity, "box of chocolate",10.00);
		ImportedItemDecorator iid = new ImportedItemDecorator(chocolate);

		int perfume_quantity = 3;
		IItem perfume = new ConcreteItem(perfume_quantity, "bottle of perfume",47.50);
		ImportedItemDecorator itiid = new ImportedItemDecorator(new TaxIncludedItemDecorator(perfume));
		
		cart.add(iid);
		cart.add(itiid);
		salesTaxesCalculator.setCart(cart);

		double taxes = chocolate_quantity * (10.00 * 0.05) 
				+ perfume_quantity * (47.50 * 0.1 + 47.50 * 0.05);
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
		
		IItem imported_perfume = new ConcreteItem("bottle of perfume",27.99);
		ImportedItemDecorator imported_perfume_decorator = new ImportedItemDecorator(new TaxIncludedItemDecorator(imported_perfume));
		
		IItem perfume = new ConcreteItem("bottle of perfume",18.99);
		TaxIncludedItemDecorator perfume_decorator = new TaxIncludedItemDecorator(perfume);
		
		IItem headache_pills = new ConcreteItem("packet of headache pills", 9.75);
		
		IItem imported_chocolate = new ConcreteItem("box of imported chocolates", 11.25);
		ImportedItemDecorator imported_chocolate_decorator = new ImportedItemDecorator(imported_chocolate);
		
		cart.add(imported_perfume_decorator);
		cart.add(imported_chocolate_decorator);
		cart.add(perfume_decorator);
		cart.add(headache_pills);
		
		salesTaxesCalculator.setCart(cart);


		double formula = (27.99 + 27.99 * 0.1 + 27.99 * 0.05) + 
				(18.99 + 18.99 * 0.1) + 9.75 + (11.25 + 11.25 * 0.05);
		
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(formula));
	}
	
	@Test
	public void should_return_price_including_taxes_for_3rd_input_multiple_quantities() throws Exception {
		
		int imported_perfume_quantity = 7;
		IItem imported_perfume = new ConcreteItem(imported_perfume_quantity,"bottle of perfume",27.99);
		ImportedItemDecorator imported_perfume_decorator = new ImportedItemDecorator(new TaxIncludedItemDecorator(imported_perfume));
		
		int perfume_quantity = 11;
		IItem perfume = new ConcreteItem(perfume_quantity,"bottle of perfume",18.99);
		TaxIncludedItemDecorator perfume_decorator = new TaxIncludedItemDecorator(perfume);
		
		int pills_quantity = 4;
		IItem headache_pills = new ConcreteItem(pills_quantity,"packet of headache pills", 9.75);
		
		int imported_chocolate_quantity = 10;
		IItem imported_chocolate = new ConcreteItem(imported_chocolate_quantity,"box of imported chocolates", 11.25);
		ImportedItemDecorator imported_chocolate_decorator = new ImportedItemDecorator(imported_chocolate);
		
		cart.add(imported_perfume_decorator);
		cart.add(imported_chocolate_decorator);
		cart.add(perfume_decorator);
		cart.add(headache_pills);
		
		salesTaxesCalculator.setCart(cart);


		double formula = imported_perfume_quantity * (27.99 + 27.99 * 0.1 + 27.99 * 0.05) + 
				perfume_quantity * (18.99 + 18.99 * 0.1) + pills_quantity * 9.75 
				+ imported_chocolate_quantity * (11.25 + 11.25 * 0.05);
		
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
	
	@Test
	public void should_return_price_taxes_for_3rd_input() throws Exception {
		
		double price_imported_perfume = 27.99;
		double price_perfume = 18.99;
		double price_pills = 9.75;
		double price_chocolate = 11.25;
		
		IItem imported_perfume = new ConcreteItem("bottle of perfume",27.99);
		ImportedItemDecorator imported_perfume_decorator = new ImportedItemDecorator(new TaxIncludedItemDecorator(imported_perfume));
		
		IItem perfume = new ConcreteItem("bottle of perfume",18.99);
		TaxIncludedItemDecorator perfume_decorator = new TaxIncludedItemDecorator(perfume);
		
		IItem headache_pills = new ConcreteItem("packet of headache pills", 9.75);
		
		IItem imported_chocolate = new ConcreteItem("box of imported chocolates", 11.25);
		ImportedItemDecorator imported_chocolate_decorator = new ImportedItemDecorator(imported_chocolate);
		
		cart.add(imported_perfume_decorator);
		cart.add(imported_chocolate_decorator);
		cart.add(perfume_decorator);
		cart.add(headache_pills);
		
		salesTaxesCalculator.setCart(cart);


		double taxes = (price_imported_perfume * 0.1 + price_imported_perfume * 0.05) + 
				(price_perfume * 0.1) + (price_chocolate * 0.05);
		
		assertThat(salesTaxesCalculator.getTotalTaxes(), equalTo(round(taxes)));
	}
	
	@Test
	public void should_return_price_including_taxes_for_imported_medical() throws Exception {
		
		IItem headache_pills = new ConcreteItem("packet of headache pills", 9.75);
		ImportedItemDecorator headache_pills_decorator = new ImportedItemDecorator(headache_pills);
		
		cart.add(headache_pills_decorator);
		salesTaxesCalculator.setCart(cart);

		double formula =  9.75 + 9.75 * 0.05;
		assertThat(round(salesTaxesCalculator.getTotalPrice()), equalTo(round(formula)));
	}
}
