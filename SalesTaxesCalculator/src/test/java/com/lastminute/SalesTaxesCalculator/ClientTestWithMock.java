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

public class ClientTestWithMock {
	
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
	private IParser parser; 
	
	@InjectMocks
	private Client client;
	
	@Before
	public void setup() {
		client = new Client(parser);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_return_output_for_1_book() {
		when(parser.parse("1 book at 12.49")).thenReturn("1 book: 12.49\nSales Taxes: 0\nTotal: 12.49");
		client.input("1 book at 12.49");
		assertThat(client.output(),equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 12.49"));
		verify(parser, times(1)).parse("1 book at 12.49");
	}
	
	@Test
	public void should_return_output_for_1_cd() {
		when(parser.parse("1 music CD at 14.99")).thenReturn("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 17.99");
		client.input("1 music CD at 14.99");
		assertThat(client.output(),equalTo("1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 17.99"));
		verify(parser, times(1)).parse("1 music CD at 14.99");
	}
	
	@Test
	public void should_return_output_for_2_items() {
		when(parser.parse("1 book at 12.49\n1 music CD at 14.99")).thenReturn("1 book: 12.49\n1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 28.98");
		client.input("1 book at 12.49");
		client.input("1 music CD at 14.99");
		assertThat(client.output(),equalTo("1 book: 12.49\n1 music CD: 16.49\nSales Taxes: 1.50\nTotal: 28.98"));
		verify(parser, times(1)).parse("1 book at 12.49\n1 music CD at 14.99");
	}
	
	@Test
	public void should_return_output_for_3_items() {
		when(parser.parse("1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85")).thenReturn("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar at 0.85\nSales Taxes: 1.50\nTotal: 29.83");
		client.input("1 book at 12.49");
		client.input("1 music CD at 14.99");
		client.input("1 chocolate bar at 0.85");
		assertThat(client.output(),equalTo("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar at 0.85\nSales Taxes: 1.50\nTotal: 29.83"));
		verify(parser, times(1)).parse("1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85");
	}

}
