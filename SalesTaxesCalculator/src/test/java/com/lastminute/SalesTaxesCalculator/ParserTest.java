package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class ParserTest {
	
	// @Mock
	private ISalesTaxesCalculator salesTaxesCalculator;
	
	@Mock
	private AbstractItemFactory itemFactory;
	
	@InjectMocks
	private IParser parser;

	@Before
	public void setup() {
		salesTaxesCalculator = new SalesTaxesCalculator();
		parser = new Parser(salesTaxesCalculator,itemFactory);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_return_complete_output_for_1_book() {
		String parsed = parser.parse("1 book at 12.49");
		assertThat(parsed,equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 12.49"));
	}
	
	@Test
	public void should_return_complete_output_for_1_cd() {
		// when(salesTaxesCalculator.getTaxFromItem("1 music CD at 14.99")).thenReturn(1.50);
		when(itemFactory.createItem(1,"music CD",14.99)).thenReturn(new Other("music CD",14.99,false));
		String parsed = parser.parse("1 music CD at 14.99");
		assertThat(parsed,equalTo("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 16.49"));
		verify(itemFactory, times(1)).createItem(1, "music CD",14.99);
	}
	
	@Test
	public void should_return_item_name() {
		String parsed = parser.getItemName("1 music CD at 14.99");
		assertThat(parsed,equalTo("music CD"));
	}

}
