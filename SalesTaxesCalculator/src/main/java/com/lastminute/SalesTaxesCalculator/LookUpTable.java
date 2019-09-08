package com.lastminute.SalesTaxesCalculator;

import java.util.HashMap;
import java.util.HashSet;

import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.decorator.ImportedItemDecorator;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;

public class LookUpTable implements ILookUpTable {
	
	HashMap<String, HashSet<String>> lookUpTable;

	public LookUpTable() {
		// super();
		this.lookUpTable = new HashMap<String,HashSet<String>>();
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
	
	public IItem lookup(String itemName, double price, boolean isImported) {
		
		IItem item = new ConcreteItem(itemName,price);
		
		if(isTaxFree(itemName)) {
			if(isImported)
				return new ImportedItemDecorator(item);
			else 
				return item;
		} else if(lookUpTable.get("Other").contains(itemName)) {
			item = new TaxIncludedItemDecorator(item);
			if(isImported)
				return new ImportedItemDecorator(item);
			else 
				return item;
		}
		 
		return null ;
	}
	
	private boolean isTaxFree(String itemName) {
		return lookUpTable.get("Medical").contains(itemName) || lookUpTable.get("Food").contains(itemName)
				|| lookUpTable.get("Book").contains(itemName);
	}

	@Override
	public void put(String string, HashSet<String> medicalHS) {
		lookUpTable.put(string, medicalHS);
	}

	@Override
	public HashSet<String> get(String string) {
		return lookUpTable.get(string);
	}

}
