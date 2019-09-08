package com.lastminute.SalesTaxesCalculator;

import java.util.HashSet;

public interface ILookUpTable {

	void put(String string, HashSet<String> medicalHS);

	HashSet<String> get(String string);

}
