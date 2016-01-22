package proxy;

import connection.DBConnection;
import connection.Type1DBConnection;

public class Proxy {

	DBConnection dbConnection = null;

	public void connect() {
		// Control the access to the ProxyDBConnection class

		// In this example..
		// Delay connection instance creation until it is necessary
		if (dbConnection == null) {
			System.out.println("Proxy has created DBConnection");
			dbConnection = new Type1DBConnection();
		}

		dbConnection.connect();
	}

	public void executeQuery(String query) {
		// Queries are executed through the Proxy
		System.out.println("Query is being executed through the proxy");
		dbConnection.executeQuery(query);

	}

}
