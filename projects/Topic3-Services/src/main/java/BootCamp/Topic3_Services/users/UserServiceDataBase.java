package BootCamp.Topic3_Services.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServiceDataBase implements UserService {

	private Connection dbConnection;
	private PreparedStatement pStmt;

	public UserServiceDataBase() {
		//dbConnection = DataAccessObject.getConnection();
	}

	public void addUser(User user) {
		String insertQuery = "INSERT INTO USERS(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL) VALUES (?,?,?,?,?)";
		try {
			pStmt = dbConnection.prepareStatement(insertQuery);
			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getFirstName());
			pStmt.setString(4, user.getLastName());
			pStmt.setString(5, user.getEmail());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

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

	public User getUserByUserName(String userName) {

		String query = "SELECT * FROM USERS WHERE USERNAME = ?";
		User user = new User();
		try {
			Statement stmt = dbConnection.createStatement();
			pStmt.setString(5, userName);
			ResultSet rs = stmt.executeQuery(query);
			user.setUserName(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setFirstName(rs.getString("FIRSTNAME"));
			user.setLastName(rs.getString("LASTNAME"));
			user.setEmail(rs.getString("EMAIL"));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return user;
	}
}
