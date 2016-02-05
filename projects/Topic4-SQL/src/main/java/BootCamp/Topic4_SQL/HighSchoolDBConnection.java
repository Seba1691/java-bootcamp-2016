package BootCamp.Topic4_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HighSchoolDBConnection {

	private String db = "high_school";
	private String url = "jdbc:mysql://localhost/" + db;
	private String user = "root";
	private String pass = "andreste";

	private Connection dbConnection;

	private static HighSchoolDBConnection instance = null;

	private HighSchoolDBConnection() {
		dbConnection = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			dbConnection = DriverManager.getConnection(this.url, this.user, this.pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HighSchoolDBConnection getInstance() {
		if (instance == null) {
			instance = new HighSchoolDBConnection();
		}
		return instance;
	}

	public Connection getDBConnection() {
		return dbConnection;
	}
}
