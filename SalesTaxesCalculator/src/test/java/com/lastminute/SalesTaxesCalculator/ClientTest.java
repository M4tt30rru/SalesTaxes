package com.lastminute.SalesTaxesCalculator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
	
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
	
	@Mock
	Parser parser; 

	@Test
	public void should_return_output_for_1_book() {
		// IParser parser = new Parser();
		Client client = new Client(parser);
		when(parser.parse("1 book at 12.49")).thenReturn("1 book: 12.49\nSales Taxes: 0\nTotal: 29.83");
		client.input("1 book at 12.49");
		assertThat(client.output(),equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 29.83"));
		verify(parser, times(1)).parse("1 book at 12.49");
	}
	
	@Test
	public void should_return_output_for_1_cd() {
		// IParser parser = new Parser();
		Client client = new Client(parser);
		when(parser.parse("1 music CD at 14.99")).thenReturn("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 17.99");
		client.input("1 music CD at 14.99");
		assertThat(client.output(),equalTo("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 17.99"));
		verify(parser, times(1)).parse("1 music CD at 14.99");
	}

}
