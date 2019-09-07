package com.lastminute.SalesTaxesCalculator;

public class ItemFactory extends AbstractItemFactory {

	@Override
	public Item createItem(int i, String string, double d) {
		return null;
	}

	@Override
	public Item createItem(String itemName, double price) {
		switch(itemName.toLowerCase()) {
			case "music cd": 
				return new Other(itemName,price,false);
			case "book": 
				return new Book(itemName,price,false);
			default:
				break;
		}
		return null;
	}

}
