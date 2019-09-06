package com.lastminute.SalesTaxesCalculator;

public class Client {

	private String input;
	private IParser parser;

	public Client(IParser parser) {
		this.parser = parser;
	}

	public void input(String input) {
		this.input = input;
		// parser.parse(input);
	}

	public String output() {
		return parser.parse(input);// "1 book: 12.49\nSales Taxes: 0\nTotal: 29.83";
	}

}
