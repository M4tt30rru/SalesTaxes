package com.lastminute.SalesTaxesCalculator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;

import org.junit.Test;

import com.lastminute.SalesTaxesCalculator.decorator.ConcreteItem;
import com.lastminute.SalesTaxesCalculator.decorator.IItem;
import com.lastminute.SalesTaxesCalculator.decorator.TaxIncludedItemDecorator;

public class CartTest {
	

	private ICart cart;

	@Before
	public void setup() {
		cart = new Cart();
	}
	

	@Test
	public void should_return_1_item() {
		ICart cart = new Cart();
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator cd_decorator = new TaxIncludedItemDecorator(cd);
		cart.add(cd_decorator);
		assertThat(cart.size(),equalTo(1));
	}
	
	@Test
	public void should_return_2_items() {
		ICart cart = new Cart();
		IItem cd = new ConcreteItem("cd",14.99);
		TaxIncludedItemDecorator cd_decorator = new TaxIncludedItemDecorator(cd);
		IItem chocolate_bar = new ConcreteItem("chocolate bar",0.85);
		cart.add(cd_decorator);
		cart.add(chocolate_bar);
		assertThat(cart.size(),equalTo(2));
	}
	
	@Test
	public void should_return_list_of_1_item() {
		IItem cd = new ConcreteItem("music CD",14.99);
		TaxIncludedItemDecorator cd_decorator = new TaxIncludedItemDecorator(cd);
		cart.add(cd_decorator);
		assertThat(cart.toString(),equalTo("1 music CD at 14.99"));
	}
	
	@Test
	public void should_return_list_of_2_items() {
		IItem book = new ConcreteItem("book",12.49);
		IItem cd = new ConcreteItem("music CD",14.99);
		TaxIncludedItemDecorator cd_decorator = new TaxIncludedItemDecorator(cd);
		cart.add(book);
		cart.add(cd_decorator);
		assertThat(cart.toString(),equalTo("1 book at 12.49\n1 music CD at 14.99"));
	}

}
