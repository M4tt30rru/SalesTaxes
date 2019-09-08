package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClientSystemTest {

	private ISalesTaxesCalculator salesTaxesCalculator;
	private IParser parser;
	private Client client;

	@Before
	public void setup() {
		salesTaxesCalculator = new SalesTaxesCalculator();
		parser = new Parser(salesTaxesCalculator);
		client = new Client(parser);
	}
	
	
	@Test
	public void should_return_complete_output_for_input_1() {
		client.setInput("1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85");
		String output = client.runInput();
		assertThat(output,equalTo("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83"));
	}
	
	
	@Test
	public void should_return_complete_output_for_input_2() {
		client.setInput("1 imported box of chocolates at 10.00\n"
								   + "1 imported bottle of perfume at 47.50");
		String output = client.runInput();
		
		assertThat(output,equalTo("1 imported box of chocolates: 10.50\n"
				+ "1 imported bottle of perfume: 54.63\n"
				+ "Sales Taxes: 7.63\nTotal: 65.13"));
	}
	
	@Test
	public void should_return_complete_output_for_input_3() {
		
		client.setInput("1 imported bottle of perfume at 27.99\n" + 
									 "1 bottle of perfume at 18.99\n" + 
									 "1 packet of headache pills at 9.75\n" + 
									 "1 box of imported chocolates at 11.25");
				
		String output = client.runInput();
		
		assertThat(output,equalTo("1 imported bottle of perfume: 32.19\n" + 
								  "1 bottle of perfume: 20.89\n" + 
								  "1 packet of headache pills: 9.75\n" + 
								  "1 box of imported chocolates: 11.81\n" + 
								  "Sales Taxes: 6.66\n" + 
								  "Total: 74.64"));
	}

}
