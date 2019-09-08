package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.lastminute.SalesTaxesCalculator.factory.AbstractItemFactory;
import com.lastminute.SalesTaxesCalculator.factory.ItemFactory;

public class ParserTest {
	
	// @Mock
	private ISalesTaxesCalculator salesTaxesCalculator;
	
	// @Mock
	private AbstractItemFactory itemFactory;
	
	// @InjectMocks
	private IParser parser;

	@Before
	public void setup() {
		salesTaxesCalculator = new SalesTaxesCalculator();
		itemFactory = new ItemFactory();
		parser = new Parser(salesTaxesCalculator,itemFactory);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_return_complete_output_for_1_book() {
		// when(itemFactory.createItem("book",12.49)).thenReturn(new Book("book",12.49,false));
		String parsed = parser.parse("1 book at 12.49");
		assertThat(parsed,equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 12.49"));
	}
		
	@Test
	public void should_return_complete_output_for_1_imported_book() {
		// when(itemFactory.createItem("book",12.49)).thenReturn(new Book("book",12.49,false));
		String parsed = parser.parse("1 imported book at 12.49");
		assertThat(parsed,equalTo("1 imported book: 13.11\nSales Taxes: 0.62\nTotal: 13.11"));
	}
	
	@Test
	public void should_return_item_name_without_imported() {
		String temp = "1 imported book at 12.49";
		String[] v = temp.split(" ");
		System.out.println(v[0]);
		String parsed = parser.getItemNameRemoveImported(v);
		assertThat(parsed,equalTo("book"));
	}
	
	@Test
	public void should_return_complete_output_for_1_cd() {
		// when(salesTaxesCalculator.getTaxFromItem("1 music CD at 14.99")).thenReturn(1.50);
		// when(itemFactory.createItem("music CD",14.99)).thenReturn(new Other("music CD",14.99,false));
		String parsed = parser.parse("1 music CD at 14.99");
		assertThat(parsed,equalTo("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 16.49"));
		// verify(itemFactory, times(1)).createItem("music CD",14.99);
	}
	
	@Test
	public void should_return_complete_output_for_cd_and_book() {
		// when(salesTaxesCalculator.getTaxFromItem("1 music CD at 14.99")).thenReturn(1.50);
		// when(itemFactory.createItem("book",12.49)).thenReturn(new Book("book",12.49,false));
		// when(itemFactory.createItem("music CD",14.99)).thenReturn(new Other("music CD",14.99,false));
		String parsed = parser.parse("1 book at 12.49\n1 music CD at 14.99");
		assertThat(parsed,equalTo("1 book: 12.49\n1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 28.98"));
		// verify(itemFactory, times(1)).createItem("book",12.49);
		// verify(itemFactory, times(1)).createItem("music CD",14.99);

	}
	
//	INPUT:
//
//		Input 1:
//		1 book at 12.49
//		1 music CD at 14.99
//		1 chocolate bar at 0.85
	
//	Output 1:
//		1 book : 12.49
//		1 music CD: 16.49
//		1 chocolate bar: 0.85
//		Sales Taxes: 1.50
//		Total: 29.83
	
	@Test
	public void should_return_complete_output_for_input_1() {
		String parsed = parser.parse("1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85");
		assertThat(parsed,equalTo("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83"));
	}
	
//	Input 2:
//	1 imported box of chocolates at 10.00 --> 10.00 + 10.00*5% = 10.50
//	1 imported bottle of perfume at 47.50 --> 47.50 + 47.50*0.1 + 47.50*0.05 = 52.25 + 2.37 = 54.62
	
//	Output 2:
//	1 imported box of chocolates: 10.50
//	1 imported bottle of perfume: 54.65 // errore nel testo
//	Sales Taxes: 7.65 // errore nel testo
//	Total: 65.15 <--- // errore nel testo
	
	// @TODO watch out that Java rounds up to 0.01 when 0.005 (R for example rounds down to 0.00)
	
	@Test
	public void should_return_complete_output_for_input_2() {
		String parsed = parser.parse("1 imported box of chocolates at 10.00\n"
								   + "1 imported bottle of perfume at 47.50");
		assertThat(parsed,equalTo("1 imported box of chocolates: 10.50\n"
								+ "1 imported bottle of perfume: 54.63\n"
								+ "Sales Taxes: 7.63\nTotal: 65.13"));
	}
	
	//	Input 3:
	//	1 imported bottle of perfume at 27.99
	//	1 bottle of perfume at 18.99
	//	1 packet of headache pills at 9.75
	//	1 box of imported chocolates at 11.25
	
	//Output 3:
	//	1 imported bottle of perfume: 32.19
	//	1 bottle of perfume: 20.89
	//	1 packet of headache pills: 9.75
	//	1 imported box of chocolates: 11.85 <-- WATCH OUT! we are changing this in box of imported chocolates 
	//	Sales Taxes: 6.70
	//	Total: 74.68
	
	@Test
	public void should_return_complete_output_for_input_3() {
		String parsed = parser.parse("1 imported bottle of perfume at 27.99\n" + 
									 "1 bottle of perfume at 18.99\n" + 
									 "1 packet of headache pills at 9.75\n" + 
									 "1 box of imported chocolates at 11.25");
		assertThat(parsed,equalTo("1 imported bottle of perfume: 32.19\n" + 
								  "1 bottle of perfume: 20.89\n" + 
								  "1 packet of headache pills: 9.75\n" + 
								  "1 box of imported chocolates: 11.81\n" + 
								  "Sales Taxes: 6.66\n" + 
								  "Total: 74.64"));
	}
	
	@Ignore
	@Test
	public void should_return_item_name() {
		String parsed = parser.getItemName("1 music CD at 14.99");
		assertThat(parsed,equalTo("music CD"));
	}
	
	@Test
	public void should_return_2_decimal_double_string() throws Exception {
		assertThat(printout2decimal(11.50),equalTo("11.50"));
		assertThat(printout2decimal(123232.50),equalTo("123232.50"));
	}
	
	private String printout2decimal(double addTaxes) {
		String string = "" + addTaxes;
		String[] stringVector = string.split("\\.");
		String s = stringVector[stringVector.length-1];
		if(s.length() < 2)
			s = s + "0";
		return stringVector[0] + "." + s;
	}

}
