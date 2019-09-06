package com.lastminute.SalesTaxesCalculator;

public class Parser implements IParser {
	
	private ISalesCalculator salesCalculator;

	public Parser(ISalesCalculator salesCalculator) {
		this.salesCalculator = salesCalculator;
	}

	public String parse(String input) {
		
		String[] temp = input.split(" ");
		int quantity = Integer.parseInt(temp[0]);
		double price = Double.parseDouble(temp[temp.length - 1]);

		// input 1 book at 12.49
		// output 1 book: 12.49\\nSales Taxes: 0\\nSales Taxes: 0
		String item = join(temp);
		String itemrow = quantity + " " + item + ": " + price;
		String footer = "Sales Taxes: 0\n" + "Total: " + price;
		
		return itemrow + "\n" + footer;
	}

	private String join(String[] temp) {
		// start from the second element
		String s = "";
		for(int i = 1; i < temp.length-2; i++) {
			//if(!s.equals("at"))
				s += temp[i] + " ";
		}
		s = s.substring(0, s.length()-1); // remove the last space
		return s;
	}

}
