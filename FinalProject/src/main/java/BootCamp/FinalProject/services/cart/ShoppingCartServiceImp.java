package BootCamp.FinalProject.services.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BootCamp.FinalProject.connection.ShoppingDBConnection;
import BootCamp.FinalProject.controllers.exceptions.NotEnoughStockException;
import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.CartItem;
import BootCamp.FinalProject.model.Product;

public class ShoppingCartServiceImp implements ShoppingCartService {

	/**
	 * Link with the Data Base which stores products’s information.
	 */
	private Connection dbConnection = ShoppingDBConnection.getInstance().getDBConnection();
	/**
	 * Statement which perform the queries over the Data Base.
	 */
	private PreparedStatement pStmt;

	/**
	 * Adds an item to the Cart. If it is not in the cart yet, it is added,
	 * while if it is already in the cart quantity is increaced in one unit.
	 * 
	 * @param sku
	 *            product that will be added into the Cart
	 * @param userName
	 *            Cart owner
	 * @throws NotFoundException
	 *             when there is not any product with the sku specified
	 */
	@Override
	public void addItem(String sku, String userName) throws NotFoundException {
		String selectQuery = "SELECT C.SKU, C.quantity FROM CARTS as C WHERE username = ? AND SKU = ?";
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, userName);
			pStmt.setString(2, sku);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.next()) {
				String insertQuery = "INSERT INTO CARTS (username, SKU, quantity) VALUES ( ?, ?, ?)";
				pStmt = dbConnection.prepareStatement(insertQuery);
				pStmt.setString(1, userName);
				pStmt.setString(2, sku);
				pStmt.setInt(3, 1);
				pStmt.executeUpdate();
			} else {
				String updateQuery = "UPDATE CARTS SET quantity = quantity+1 WHERE username = ? AND SKU = ?";
				pStmt = dbConnection.prepareStatement(updateQuery);
				pStmt.setString(1, userName);
				pStmt.setString(2, sku);
				pStmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

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
	@Override
	public void removeItem(String sku, String userName) throws NotFoundException {
		String selectQuery = "DELETE FROM CARTS WHERE username = ? AND SKU = ?";
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, userName);
			pStmt.setString(2, sku);
			int rowCount = pStmt.executeUpdate();
			if (rowCount == 0) {
				throw new NotFoundException("Product " + sku + " not in cart");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Returns the current content from the Cart for a given user.
	 * 
	 * @param userName
	 *            Cart owner
	 * @return content of the cart
	 */
	@Override
	public List<CartItem> getContents(String userName) {
		String selectQuery = "SELECT C.SKU, C.quantity, P.price, P.description, P.category  FROM CARTS as C JOIN PRODUCTS AS P ON (C.SKU=P.SKU) WHERE username = ?";
		List<CartItem> content = new ArrayList<CartItem>();
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, userName);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				CartItem item = new CartItem(new Product(rs.getString("SKU"), rs.getString("description"), rs.getString("category"), rs.getDouble("Price")), rs.getInt("quantity"));
				content.add(item);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return content;
	}

	/**
	 * Perform the checkout and substract correct quantity of every product from
	 * their stock.
	 * 
	 * @param userName
	 *            Cart owner
	 * @throws NotEnoughStockException
	 *             when there is not enough stock to perform the checkout
	 */
	@Override
	public void checkout(String userName) throws NotEnoughStockException {
		if (isStockEnough(userName)) {
			List<CartItem> content = getContents(userName);
			try {
				for (CartItem item : content) {
					String updateQuery = "UPDATE PRODUCTS SET stock = stock - ? WHERE SKU = ?";
					pStmt = dbConnection.prepareStatement(updateQuery);
					pStmt.setInt(1, item.getQuantity());
					pStmt.setString(2, item.getProduct().getSku());
					pStmt.addBatch();
				}
				pStmt.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			throw new NotEnoughStockException();
	}

	/**
	 * Validates if there is enough stock to perform the checkout a cart of a
	 * given user.
	 * 
	 * @param userName
	 *            Cart owner
	 * @return true: when there is enough stock false: when there is not enough
	 *         stock
	 */
	public boolean isStockEnough(String userName) {
		String selectQuery = "SELECT COUNT(1) as countInvalid FROM CARTS as C JOIN PRODUCTS AS P ON (C.SKU=P.SKU) WHERE username = ? AND C.quantity > P.Stock";
		int countInvalid = 0;
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, userName);
			ResultSet rs = pStmt.executeQuery();
			rs.first();
			countInvalid = rs.getInt("countInvalid");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return countInvalid == 0;
	}

	/**
	 * Removes all items from a Cart.
	 * 
	 * @param userName
	 *            Cart owner
	 */
	public void clearCart(String userName) {
		String selectQuery = "DELETE FROM CARTS WHERE username = ?";
		try {
			pStmt = dbConnection.prepareStatement(selectQuery);
			pStmt.setString(1, userName);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
