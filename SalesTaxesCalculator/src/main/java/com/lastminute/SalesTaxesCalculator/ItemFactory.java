package com.lastminute.SalesTaxesCalculator;

import java.util.HashMap;
import java.util.HashSet;

import com.lastminute.SalesTaxesCalculator.item.Book;
import com.lastminute.SalesTaxesCalculator.item.Food;
import com.lastminute.SalesTaxesCalculator.item.Item;
import com.lastminute.SalesTaxesCalculator.item.Medical;
import com.lastminute.SalesTaxesCalculator.item.Other;

public class ItemFactory extends AbstractItemFactory {
	
	HashMap<String, HashSet<String>> lookUpTable = new HashMap<String,HashSet<String>>();
	
	public ItemFactory() {
		initializeLookUpTable();
	}


	private void initializeLookUpTable() {
		
		HashSet<String> medicalHS = new HashSet<String>();
		medicalHS.add("packet of headache pills");
		lookUpTable.put("Medical", medicalHS);
		
		HashSet<String> foodHS = new HashSet<String>();
		foodHS.add("chocolate bar");
		foodHS.add("box of chocolates");
		lookUpTable.put("Food", medicalHS);
		
		HashSet<String> bookHS = new HashSet<String>();
		bookHS.add("book");
		lookUpTable.put("Book", medicalHS);
		
		HashSet<String> otherHS = new HashSet<String>();
		otherHS.add("bottle of perfume");
		lookUpTable.put("Other", medicalHS);
	}


	@Override
	public Item createItem(int i, String string, double d) {
		return null;
	}

	@Override
	public Item createItem(String itemName, double price) {
//		if(false) // Math.random() > 3)
//			return lookup(itemName.toLowerCase());
		String lowerCase = itemName.toLowerCase();
		switch(lowerCase) {
			case "music cd": 
				return new Other(itemName,price,lowerCase.contains("imported"));
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
				// packet of headache pills
			case "packet of headache pills":
				return new Medical(itemName,price,false);
				// imported box of chocolates
			case "box of imported chocolates":
				return new Food(itemName,price,true);
			case "bottle of perfume":
				return new Other(itemName,price,false);
				// imported book
			case "imported book":
				return new Book(itemName,price,true);
			default:
				break;
		}
		return null;
	}
	
	private Item lookup(String itemName, Double price, boolean isImported) {

//		String className = lookUpTable.get(item);
//		Item i = null;
//		try {
//			 i = (Item) Class.forName(className).newInstance();
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return i;
		
		if(lookUpTable.get("Medical").contains(itemName))
				return new Medical(itemName,price,false);
		
		if(lookUpTable.get("Food").contains(itemName))
			return new Food(itemName,price,false);
		
		if(lookUpTable.get("Book").contains(itemName))
			return new Book(itemName,price,false);
		
		if(lookUpTable.get("Other").contains(itemName))
			return new Other(itemName,price,false);
		
		return null;
	}

}
