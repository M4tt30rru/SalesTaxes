package com.lastminute.SalesTaxesCalculator;

import java.util.List;

import com.lastminute.SalesTaxesCalculator.decorator.IItem;

public interface ICart {

	void add(IItem cd);

	Integer size();

	List<IItem> getItemsList();

}
