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
			case "chocolate bar": 
				return new Food(itemName,price,false);
				// imported box of chocolates
			case "imported box of chocolates": 
				return new Food(itemName,price,true);
				// imported bottle of perfume
			case "imported bottle of perfume": 
				return new Other(itemName,price,true);
			default:
				break;
		}
		return null;
	}

}
