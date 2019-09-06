package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParserTest {
	
	@Mock
	private ISalesCalculator salesCalculator;
	
	@InjectMocks
	private IParser parser;

	@Before
	public void setup() {
		parser = new Parser(salesCalculator);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_return_complete_output_for_1_book() {
		String parsed = parser.parse("1 book at 12.49");
		assertThat(parsed,equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 12.49"));
	}
	
	@Test
	public void should_return_complete_output_for_1_cd() {
		String parsed = parser.parse("1 music CD at 14.99");
		assertThat(parsed,equalTo("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 17.99"));
	}

}
