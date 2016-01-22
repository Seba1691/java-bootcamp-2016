package factory;

import connection.DBConnection;
import connection.Type2DBConnection;
import connection.Type4DBConnection;

public class DBConnectionFactoryPairs extends AbstractDBConnectionFactory {

	@Override
	public DBConnection getDBConnection(String ConnectionType) {
		if (ConnectionType.equalsIgnoreCase("TYPE2")) {
			return new Type2DBConnection();

		} else if (ConnectionType.equalsIgnoreCase("TYPE4")) {
			return new Type4DBConnection();
		}

		return null;
	}
}
