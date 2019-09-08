package com.lastminute.SalesTaxesCalculator;

import java.util.ArrayList;
import java.util.List;

import com.lastminute.SalesTaxesCalculator.decorator.IItem;

public class Cart implements ICart {
	
	private List<IItem> iiList = new ArrayList<IItem>();

	@Override
	public void add(IItem item) {
		this.iiList.add(item);
	}

	@Override
	public Integer size() {
		return this.iiList.size();
	}

	@Override
	public List<IItem> getItemsList() {
		return this.iiList;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(IItem i: iiList) {
			s += i + "\n";
		}
		s = s.substring(0, s.length()-1); // remove the last space
		return s;
	}

}
