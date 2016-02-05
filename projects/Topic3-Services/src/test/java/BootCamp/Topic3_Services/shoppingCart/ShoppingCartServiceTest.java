package BootCamp.Topic3_Services.shoppingCart;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BootCamp.Topic3_Services.users.User;

/**
 * Unit test for simple App.
 */
public class ShoppingCartServiceTest {

	private ShoppingCartService service;

	@Before
	public void setUp() {
		service = new ShoppingCartServiceImp();
	}

	@After
	public void tearDown() {
		service = null;
	}

	@Test
	public void whenCartIsInitializedItIsEmpty() {
		service.initialize(new User());
		Assert.assertTrue(service.getContents().isEmpty());
	}

	@Test
	public void whenItemIsAddedItIsAddedToTheCart() {
		service.initialize(new User());
		service.addItem("Item1");
		Assert.assertTrue(service.getContents().contains("Item1"));
	}

	@Test
	public void whenItemIsRemovedItIsDeletedFromTheCart() throws ShoppingCartException {
		service.initialize(new User());
		service.addItem("Item1");
		service.removeItem("Item1");
		Assert.assertTrue(!service.getContents().contains("Item1"));
	}

	@Test(expected = ShoppingCartException.class)
	public void whenItemIsRemovedAndItDoesNotExistsExceptionIsThrown() throws ShoppingCartException {
		service.initialize(new User());
		service.removeItem("Item1");
	}

	@Test
	public void whenCartsContentIsAskedItIsObtained() {
		service.initialize(new User());
		List<String> expected = new LinkedList<String>();
		service.addItem("Item1");
		service.addItem("Item2");
		service.addItem("Item3");
		expected.add("Item1");
		expected.add("Item2");
		expected.add("Item3");

		Assert.assertEquals(expected, service.getContents());
	}

}
