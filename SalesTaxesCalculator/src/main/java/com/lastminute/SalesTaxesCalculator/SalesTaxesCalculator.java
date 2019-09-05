package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.List;

public class SalesTaxesCalculator {

	private List<Item> list = new ArrayList<Item>();

	public Double getTaxFromItem(Item item) {
		if(item.getCategory() != "food" && item.getCategory() != "book" && item.getCategory() != "medicine")
			return 10.0;
		return item.getTax();
	}

	public Double getPrice(Item item) {
//		if(item.getCategory() != "food" && item.getCategory() != "book" && item.getCategory() != "medicine")
//			return item.getPrice() + item.getPrice()*0.1;
		return item.getPrice();
	}

	public void add(Item item) {
		list.add(item);
	}

	public Double getTotalPrice() {
		Double price = 0.0;
		for(Item i: list) {
			price += i.getPrice();
		}
		return price;
	}

}
