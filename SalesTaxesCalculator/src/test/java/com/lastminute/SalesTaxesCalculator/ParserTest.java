package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParserTest {

	@Test
	public void should_return_complete_output_for_1_book() {
		IParser parser = new Parser();
		String parsed = parser.parse("1 book at 12.49");
		assertThat(parsed,equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 12.49"));
	}

}
