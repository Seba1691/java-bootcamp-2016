package BootCamp.Topic6_REST.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import BootCamp.Topic6_REST.api.NotFoundException;
import BootCamp.Topic6_REST.model.User;

/**
 * This class is responsible for implementing the services provided for the
 * UserService interface. This class uses JDBC to connect with a data base in
 * order to save user’s data.
 */
public class UserServiceDataBase implements UserService {

	/**
	 * Link with the Data Base which stores user’s information.
	 */
	private Connection dbConnection;
	/**
	 * Statement which perform the queries over the Data Base.
	 */
	private PreparedStatement pStmt;

	public UserServiceDataBase() {
		// dbConnection = DataAccessObject.getConnection();
	}

	/**
	 * Inserts the new user into the data base.
	 * 
	 * @param user
	 *            indicates the user that will be added
	 */
	public void addUser(User user) {
		String insertQuery = "INSERT INTO USERS(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL) VALUES (?,?,?,?,?)";
		try {
			pStmt = dbConnection.prepareStatement(insertQuery);
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getFirstName());
			pStmt.setString(4, user.getLastName());
			pStmt.setString(5, user.getEmail());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Updates the information of existent user.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find the user to
	 *            update
	 * @param userUpdate
	 *            new user's information
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public void updateUser(String userName, User userUpdate) {
		String updateQuery = "UPDATE USERS SET PASSWORD = ?, FISRTNAME = ?, LASTNAME = ?, EMAIL = ? WHERE USERNAME = ?";
		try {
			pStmt = dbConnection.prepareStatement(updateQuery);
			pStmt.setString(5, userName);
			pStmt.setString(1, userUpdate.getPassword());
			pStmt.setString(2, userUpdate.getFirstName());
			pStmt.setString(3, userUpdate.getLastName());
			pStmt.setString(4, userUpdate.getEmail());
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Removes an existent user from the data base.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find the user to
	 *            delete
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public void deleteUser(String userName) {
		String deleteQuery = "DELETE FROM USERS WHERE USERNAME = ?";
		try {
			pStmt = dbConnection.prepareStatement(deleteQuery);
			pStmt.setString(1, userName);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Returns the user’s information of an existent user from the data base.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find user
	 * @return user by its user name
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public User getUserByUserName(String userName) {

		String query = "SELECT * FROM USERS WHERE USERNAME = ?";
		User user = new User();
		try {
			Statement stmt = dbConnection.createStatement();
			pStmt.setString(5, userName);
			ResultSet rs = stmt.executeQuery(query);
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setFirstName(rs.getString("FIRSTNAME"));
			user.setLastName(rs.getString("LASTNAME"));
			user.setEmail(rs.getString("EMAIL"));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return user;
	}

	/**
	 * Returns all users that has a specific first name.
	 * 
	 * @param firstName
	 *            indicates the first name that will be used to find user
	 * @return users list with that first name
	 */
	public List<User> getUserByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}
}
