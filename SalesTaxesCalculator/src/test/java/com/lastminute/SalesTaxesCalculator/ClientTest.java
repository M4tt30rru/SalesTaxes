package com.lastminute.SalesTaxesCalculator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

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

	@Test
	public void should_return_output_for_1_book() {
		Client client = new Client();
		client.input("1 book at 12.49");
		assertThat(client.output(),equalTo("1 book: 12.49\nSales Taxes: 0\nTotal: 29.83"));
	}

}
