package com.lastminute.SalesTaxesCalculator;

public interface IParser {

	String parse(String string);

	String getItemName(String string);

	String getItemNameRemoveImported(String[] v);

}
