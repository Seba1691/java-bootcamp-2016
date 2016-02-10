package BootCamp.FinalProject.services.cart;

import java.util.List;

import BootCamp.FinalProject.controllers.exceptions.NotEnoughStockException;
import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.CartItem;

/**
 * 
 * This interface defines the operations that every ShoppingCartService should
 * implement.
 *
 */
public interface ShoppingCartService {

	/**
	 * Adds an item to the Cart.
	 * 
	 * @param sku
	 *            product that will be added into the Cart
	 * @param userName
	 *            Cart owner
	 * @throws NotFoundException
	 *             when there is not any product with the sku specified
	 */
	public void addItem(String sku, String userName) throws NotFoundException;

	/**
	 * Returns the current content from the Cart.
	 * 
	 * @param userName
	 *            Cart owner
	 * @return content of the cart
	 */
	public List<CartItem> getContents(String userName);

	/**
	 * Removes a specified item from the Cart.
	 * 
	 * @param sku
	 *            product that will be added into the Cart
	 * @param userName
	 *            Cart owner
	 * @throws NotFoundException
	 *             when there is not any product with the sku specified in the
	 *             cart
	 */
	public void removeItem(String sku, String userName) throws NotFoundException;

	/**
	 * Perform the checkout and substract correct quantity of every product from
	 * their stock
	 * 
	 * @param userName
	 *            Cart owner
	 * @throws NotEnoughStockException
	 *             when there is not enough stock to perform the checkout
	 */
	public void checkout(String userName) throws NotEnoughStockException;

	/**
	 * Removes all items from a Cart.
	 * 
	 * @param userName
	 *            Cart owner
	 */
	public void clearCart(String userName);

}
