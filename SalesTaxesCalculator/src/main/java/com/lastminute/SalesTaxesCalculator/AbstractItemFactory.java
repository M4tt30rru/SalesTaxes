package com.lastminute.SalesTaxesCalculator;

import com.lastminute.SalesTaxesCalculator.item.Item;

public abstract class AbstractItemFactory {

	public abstract Item createItem(int i, String string, double d);

	public abstract Item createItem(String string, double d);


	

}
