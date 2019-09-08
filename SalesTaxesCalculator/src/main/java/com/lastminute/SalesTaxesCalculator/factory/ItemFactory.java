package com.lastminute.SalesTaxesCalculator.factory;

import com.lastminute.SalesTaxesCalculator.ILookUpTable;
import com.lastminute.SalesTaxesCalculator.LookUpTable;
import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.decorator.ImportedItemDecorator;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;
import com.lastminute.SalesTaxesCalculator.item.Book;
import com.lastminute.SalesTaxesCalculator.item.Food;
import com.lastminute.SalesTaxesCalculator.item.Item;
import com.lastminute.SalesTaxesCalculator.item.Medical;
import com.lastminute.SalesTaxesCalculator.item.Other;

public class ItemFactory extends AbstractItemFactory {
	
	// HashMap<String, HashSet<String>> lookUpTable = new HashMap<String,HashSet<String>>();
	private ILookUpTable lookUpTable;
	
	public ItemFactory() {
		this.lookUpTable = new LookUpTable();
		// lookUpTable.initializeLookUpTable();
	}


//	private void initializeLookUpTable() {
//		
//		HashSet<String> medicalHS = new HashSet<String>();
//		medicalHS.add("packet of headache pills");
//		lookUpTable.put("Medical", medicalHS);
//		
//		HashSet<String> foodHS = new HashSet<String>();
//		foodHS.add("chocolate bar");
//		foodHS.add("box of chocolates");
//		lookUpTable.put("Food", medicalHS);
//		
//		HashSet<String> bookHS = new HashSet<String>();
//		bookHS.add("book");
//		lookUpTable.put("Book", medicalHS);
//		
//		HashSet<String> otherHS = new HashSet<String>();
//		otherHS.add("bottle of perfume");
//		lookUpTable.put("Other", medicalHS);
//	}


	@Override
	public IItem createItem(String itemName, double price) {

		String lowerCase = itemName.toLowerCase();
		switch(lowerCase) {
			case "music cd": 
				return new TaxIncludedItemDecorator(new ConcreteItem(itemName, price));
			case "book": 
				return new ConcreteItem(itemName,price);
			case "chocolate bar": 
				return new ConcreteItem(itemName,price);
				// imported box of chocolates
			case "imported box of chocolates": 
				return new ImportedItemDecorator(new ConcreteItem(itemName, price));
				// imported bottle of perfume
			case "imported bottle of perfume": 
				return new TaxIncludedItemDecorator(new ImportedItemDecorator(new ConcreteItem(itemName, price)));
				// packet of headache pills
			case "packet of headache pills":
				return new ConcreteItem(itemName,price);
				// imported box of chocolates
			case "box of imported chocolates":
				return new ImportedItemDecorator(new ConcreteItem(itemName, price));
			case "bottle of perfume":
				return new TaxIncludedItemDecorator(new ConcreteItem(itemName, price));
				// imported book
			case "imported book":
				return new ImportedItemDecorator(new ConcreteItem(itemName, price));
			default:
				break;
		}
		return null;
	}

	@Deprecated
	public Item createItem2(String itemName, double price) {

		String lowerCase = itemName.toLowerCase();
		switch(lowerCase) {
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
	
	@Deprecated
	private Item lookup(String itemName, Double price, boolean isImported) {

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
