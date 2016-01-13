package singleton;

import connection.DBConnection;
import connection.Type1DBConnection;

public class SingletonDBConnection {

	private DBConnection dbConnection;

	private static SingletonDBConnection instance = null;

	private SingletonDBConnection() {
		this.dbConnection = new Type1DBConnection();
		System.out.println("SingletonDBConnection instance created 	");
	}

	public static SingletonDBConnection getInstance() {
		if (instance == null) {
			instance = new SingletonDBConnection();
		}
		return instance;
	}

	public DBConnection getDBConnection() {
		return dbConnection;
	}
}
