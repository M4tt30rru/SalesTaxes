package com.lastminute.SalesTaxesCalculator;

public class Parser implements IParser {
	
	private ISalesTaxesCalculator salesTaxesCalculator;
	private AbstractItemFactory itemFactory;
	

	public Parser(ISalesTaxesCalculator salesCalculator) {
		this.salesTaxesCalculator = salesCalculator;
	}

	public Parser(ISalesTaxesCalculator salesCalculator, AbstractItemFactory itemFactory) {
		this(salesCalculator);
		this.itemFactory = itemFactory;
	}

	public String parse(String input) {
		if(input.contains("\n")) {
			return parseMultiLine(input);
		}
		return parseSingleLine(input) + "\n" + addFooter(salesTaxesCalculator);
	}

	private String parseSingleLine(String input) {
		String[] temp = input.split(" ");
		int quantity = Integer.parseInt(temp[0]);
		double price = Double.parseDouble(temp[temp.length - 1]);

		// input 1 book at 12.49
		// output 1 book: 12.49\\nSales Taxes: 0\\nSales Taxes: 0
		String itemName = join(temp);
		
		Item item = itemFactory.createItem(itemName, price);
		// item.setPrice(price);
		
		salesTaxesCalculator.add(item);
		
		String itemrow = quantity + " " + itemName + ": " + printout2decimal(item.getFullPrice());
		return itemrow;
	}

	private String addFooter(ISalesTaxesCalculator salesTaxesCalculator) {
		String footer = "Sales Taxes: " + printout2decimal(round(salesTaxesCalculator.getTotalTaxes())) +"\n" + "Total: " 
						+ round(salesTaxesCalculator.getTotalPrice()).toString();
		return footer;
	}

	private String parseMultiLine(String input) {
		String[] sv = input.split("\n");
		String temp = "";
		for(String s: sv) {
			temp += parseSingleLine(s) + "\n";
		}
		return temp + addFooter(salesTaxesCalculator);
	}

	String printout2decimal(double addTaxes) {
		String string = "" + addTaxes;
		String[] stringVector = string.split("\\.");
		String s = stringVector[1];
		if(isZero(string))
			return "0";
		if(s.length() < 2)
			s = s + "0";
		return stringVector[0] + "." + s;
	}

	private boolean isZero(String withTaxes) {
		String[] stringVector = withTaxes.split("\\.");
		for(String s: stringVector)
			if(Integer.parseInt(s) > 0)
				return false;
		return true;
	}

	private Item lookUp(String itemName) {
			switch(itemName) {
				case "music CD":
					return new Other(itemName,0.0,false);
				default:
					break;
			}
		return null;
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

	@Override
	public String getItemName(String string) {
		return null;
	}
	
	protected Double round(Double input) {
		// return (double) Math.round(input*100.00)/100.00;
		return (double) Math.round(input*200.00)/200.00;

	}

}
