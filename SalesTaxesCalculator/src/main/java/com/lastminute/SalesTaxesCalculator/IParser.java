package com.lastminute.SalesTaxesCalculator;

public interface IParser {

	String parse(String string) throws ItemNotMatchingException;

	String getItemName(String string);

	String getItemNameRemoveImported(String[] v);

}
