package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;

public class LookUpTableTest {

	@Test
	public void test() {
		
		LookUpTable lut = new LookUpTable();
		// ConcreteItem("music cd",10.0);
		ConcreteItem item = new ConcreteItem("music cd", 10.0);
		// assertThat(lut.lookup("music cd",10.0,false),equalTo(new TaxIncludedItemDecorator(item)));
	}

}
