package com.lastminute.SalesTaxesCalculator;

import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.item.Item;

public abstract class AbstractItemFactory {

	// public abstract Item createItem(int i, String string, double d);

	public abstract IItem createItem(String string, double d);


	

}
