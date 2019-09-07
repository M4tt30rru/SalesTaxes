package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

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
