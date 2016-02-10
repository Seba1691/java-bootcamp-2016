package BootCamp.FinalProject.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 
 * This class is responsable to establish the connection with the Data Base Shopping where all data is stored
 *
 */
public class ShoppingDBConnection {

	private String db = "Shopping";
	private String url = "jdbc:mysql://localhost/" + db;
	private String user = "root";
	private String pass = "andreste";
	private String driverClass = "org.gjt.mm.mysql.Driver";

	private Connection dbConnection;

	private static ShoppingDBConnection instance = null;

	/**
	 * Estableshes the connection with the DB
	 */
	private ShoppingDBConnection() {
		dbConnection = null;
		try {
			Class.forName(driverClass);
			dbConnection = DriverManager.getConnection(this.url, this.user, this.pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	public static ShoppingDBConnection getInstance() {
		if (instance == null) {
			instance = new ShoppingDBConnection();
		}
		return instance;
	}

	/**
	 * Returns the connection
	 * @return dbConnection
	 */
	public Connection getDBConnection() {
		return dbConnection;
	}
	
	/**
	 * Returns a DataSource from the current DB settings
	 */
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(driverClass);
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(user);
		driverManagerDataSource.setPassword(pass);
		return driverManagerDataSource;
	}
}
