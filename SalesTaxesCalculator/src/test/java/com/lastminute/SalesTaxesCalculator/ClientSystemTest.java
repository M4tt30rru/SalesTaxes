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
