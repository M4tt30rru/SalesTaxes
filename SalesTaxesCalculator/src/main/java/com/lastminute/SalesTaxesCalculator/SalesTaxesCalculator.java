package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.List;

public class SalesTaxesCalculator {

	private List<Item> list = new ArrayList<Item>();

	public Double getTaxFromItem(Item item) {
		return item.getTax();
	}

	public Double getPrice(Item item) {
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
