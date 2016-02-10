package BootCamp.FinalProject.services.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BootCamp.FinalProject.connection.ShoppingDBConnection;
import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.Product;

public class ProductServiceImp implements ProductService {

	/**
	 * Link with the Data Base which stores products’s information.
	 */
	private Connection dbConnection = ShoppingDBConnection.getInstance().getDBConnection();
	/**
	 * Statement which perform the queries over the Data Base.
	 */
	private PreparedStatement pStmt;

	/**
	 * Gets a specific Product looking for it in the DB by its SKU
	 * 
	 * @param sku
	 *            used to find the product
	 * @return Product with this sku
	 * @throws NotFoundException
	 *             when there is not any product with that sku
	 */
	public Product getbySKU(String sku) throws NotFoundException {
		String selectQuery = "SELECT SKU, description, category, price FROM PRODUCTS WHERE SKU = ?";
		Product product = null;
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, sku);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.next()) {
				throw new NotFoundException("Product " + sku + " not found");
			}
			product = new Product(rs.getString("SKU"), rs.getString("description"), rs.getString("category"), rs.getDouble("Price"));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return product;
	}

	/**
	 * Gets list of Product which has a given name
	 * 
	 * @param name
	 *            used to find the products
	 * @return list of products
	 */
	public List<Product> getByName(String name) {
		String selectQuery = "SELECT SKU, description, category, price FROM PRODUCTS WHERE Description = ?";
		List<Product> productsList = new ArrayList<Product>();
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, name);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getString("SKU"), rs.getString("description"), rs.getString("category"), rs.getDouble("Price"));
				productsList.add(product);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return productsList;
	}

	/**
	 * Gets list of Product that velong to a category
	 * 
	 * @param category
	 *            used to find the products in the DB
	 * @return list of products
	 */
	public List<Product> getByCategory(String category) {
		String selectQuery = "SELECT SKU, description, category, price FROM PRODUCTS WHERE category = ?";
		List<Product> productsList = new ArrayList<Product>();
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, category);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getString("SKU"), rs.getString("description"), rs.getString("category"), rs.getDouble("Price"));
				productsList.add(product);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return productsList;
	}
}
