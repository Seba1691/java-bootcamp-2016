package BootCamp.Topic3_Services.shoppingCart;

import java.util.List;

import BootCamp.Topic3_Services.users.User;

public interface ShoppingCartService {
	public void initialize(User user);

	public void addItem(String itemName);

	public List<String> getContents();

	public void removeItem(String itemName) throws ShoppingCartException;
	
}
