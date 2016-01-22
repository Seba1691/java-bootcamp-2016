package factory;

import connection.DBConnection;

public abstract class AbstractDBConnectionFactory {

	public abstract DBConnection getDBConnection(String ConnectionType);
}
