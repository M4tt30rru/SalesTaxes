package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.List;

import com.lastminute.SalesTaxesCalculator.item.Item;

public class SalesTaxesCalculator implements ISalesTaxesCalculator {

	private List<Item> list = new ArrayList<Item>();

	public Double getTaxFromItem(Item item) {
		return item.getTax();
	}

	public Double getPrice(Item item) {
		return item.getFullPrice();
	}

	public void add(Item item) {
		list.add(item);
	}

	public Double getTotalPrice() {
		Double price = 0.0;
		for(Item i: list) {
			price += i.getFullPrice();
		}
		return price;
	}

	public double getTotalTaxes() {
		Double taxes = 0.0;
		for(Item i: list) {
			double tax = i.getTax()/100;
			double price = i.getPrice();
			taxes = taxes + i.getAllTaxes(); // (price * tax);
		}
		return taxes;
	}

	@Override
	public Double getTaxFromItem(String string) {
		return null;
	}

}
