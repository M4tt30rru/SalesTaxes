package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private String input;
	private IParser parser;
	private List<String> inputList;

	public Client(IParser parser) {
		this.parser = parser;
	}

	public void input(String input) {
		this.input = input;
		if(inputList == null)
			inputList = new ArrayList<String>();
		inputList.add(input);
		// parser.parse(input);
	}

	public String output() {
		String temp = String.join("\n", this.inputList);
		return parser.parse(temp);// "1 book: 12.49\nSales Taxes: 0\nTotal: 29.83";
	}

}
