package com.lastminute.SalesTaxesCalculator;

import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.item.Item;

public class SalesTaxesCalculator implements ISalesTaxesCalculator {

//	@Deprecated
//	private List<Item> list = new ArrayList<Item>();
	
	private ICart cart;
	
	public SalesTaxesCalculator() {
		this.cart = new Cart();
	}
	public SalesTaxesCalculator(ICart cart2) {
		this.cart = new Cart();
	}

	public Double getTaxFromItem(IItem item) {
		return item.getTax();
	}

	public Double getPrice(Item item) {
		return item.getFullPrice();
	}
	
//	@Deprecated
//	public void add(Item item) {
//		list.add(item);
//	}

	public Double getTotalPrice() {
		Double price = 0.0;
		for(IItem i: cart.getItemsList()) {
			price += i.getFullPrice(); // i.getFullPrice();
		}
		return price;
	}

	public double getTotalTaxes() {
		Double taxes = 0.0;
		for(IItem i: cart.getItemsList()) {
			taxes += i.getAllTaxes(); // (price * tax);
		}
		return taxes;
	}

	@Override
	public Double getTaxFromItem(String string) {
		return null;
	}

	public Double getPrice(IItem item) {
		return item.getPrice();
	}

	public void add(IItem item) {
		cart.add(item);
	}
	public void setCart(ICart cart) {
		this.cart = cart;
	}

}
