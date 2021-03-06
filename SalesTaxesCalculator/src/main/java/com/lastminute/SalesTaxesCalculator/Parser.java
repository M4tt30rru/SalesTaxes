package com.lastminute.SalesTaxesCalculator;

import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.factory.AbstractItemFactory;
import com.lastminute.SalesTaxesCalculator.factory.ItemFactory;

public class Parser implements IParser {
	
	private ISalesTaxesCalculator salesTaxesCalculator;
	private AbstractItemFactory itemFactory;
	

	public Parser(ISalesTaxesCalculator salesCalculator) {
		this.salesTaxesCalculator = salesCalculator;
		this.itemFactory = new ItemFactory();
	}

	public Parser(ISalesTaxesCalculator salesCalculator, AbstractItemFactory itemFactory) {
		this(salesCalculator);
		this.itemFactory = itemFactory;
	}

	public String parse(String input) throws ItemNotMatchingException {
		if(input.contains("\n")) {
			return parseMultiLine(input);
		}
		return parseSingleLine(input) + "\n" + addFooter(salesTaxesCalculator);
	}

	private String parseSingleLine(String input) throws ItemNotMatchingException {
		
		boolean imported = false;
		if(input.contains("imported")) {
				imported = true;
		}
		
		String[] temp = input.split(" ");
		int quantity = Integer.parseInt(temp[0]);
		double price = Double.parseDouble(temp[temp.length - 1]);

		String itemName;
		if(input.contains("imported")) {
			itemName = getItemNameWithImported(temp);
		} else {
			itemName = getItemNameRemoveImported(temp);
		}
		
		IItem item = itemFactory.createItem(itemName, price);
		
		if(item == null) throw new ItemNotMatchingException();
		
		salesTaxesCalculator.add(item);
		String itemrow = quantity + " " + itemName + ": " + printout2decimal(round(item.getFullPrice()));
		
		return itemrow;
	}

	private String addFooter(ISalesTaxesCalculator salesTaxesCalculator) {
		String footer = "Sales Taxes: " + printout2decimal(round(salesTaxesCalculator.getTotalTaxes())) +"\n" + "Total: " 
						+ round(salesTaxesCalculator.getTotalPrice()).toString();
		return footer;
	}

	private String parseMultiLine(String input) throws ItemNotMatchingException {
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

	public String getItemNameWithImported(String[] temp) {
		// start from the second element
		String s = "";
		for(int i = 1; i < temp.length-2; i++) {
				s += temp[i] + " ";
		}
		s = s.substring(0, s.length()-1); // remove the last space
		return s;
	}

	public String getItemNameRemoveImported(String[] temp) {
		// start from the second element
		String s = "";
		for(int i = 1; i < temp.length-2; i++) {
			if(!temp[i].equals("imported")) 
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
		return (double) Math.round(input*100.00)/100.00;
	}

}
