package BootCamp.FinalProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * This class represents an item into a cart. It has a product and its respective quantity.
 *
 */
public class CartItem {

	private Product product;

	private int quantity;

	public CartItem(Product product, int quantity) {
		this.setProduct(product);
		this.setQuantity(quantity);
	}

	@JsonProperty("product")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@JsonProperty("quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
