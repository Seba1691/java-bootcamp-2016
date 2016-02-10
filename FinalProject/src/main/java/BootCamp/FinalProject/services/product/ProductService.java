package BootCamp.FinalProject.services.product;

import java.util.List;

import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.Product;

/**
 * 
 * This interface defines the operations over products
 *
 */
public interface ProductService {

	/**
	 * Returns a specific Product by its SKU
	 * 
	 * @param sku
	 *            used to find the product
	 * @return Product with this sku
	 * @throws NotFoundException
	 *             when there is not any product with that sku
	 */
	public Product getbySKU(String sku) throws NotFoundException;

	/**
	 * Returns a list of Product with a name
	 * 
	 * @param name
	 *            used to find the products
	 * @return list of products
	 */
	public List<Product> getByName(String name);

	/**
	 * Returns a list of Product in a category
	 * 
	 * @param category
	 *            used to find the products
	 * @return list of products
	 */
	public List<Product> getByCategory(String category);

}
