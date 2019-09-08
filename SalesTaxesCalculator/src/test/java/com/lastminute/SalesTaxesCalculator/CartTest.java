package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;

public class CartTest {

	@Test
	public void shuld_return_1_item() {
		ICart cart = new Cart();
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator cd_decorator = new TaxIncludedItemDecorator(cd);
		cart.add(cd_decorator);
		assertThat(cart.size(),equalTo(1));
	}
	
	@Test
	public void shuld_return_2_items() {
		ICart cart = new Cart();
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator cd_decorator = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		cart.add(cd_decorator);
		cart.add(chocolate_bar);
		assertThat(cart.size(),equalTo(2));
	}

}
