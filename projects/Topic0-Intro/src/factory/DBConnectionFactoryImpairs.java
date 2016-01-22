package factory;

import connection.DBConnection;
import connection.Type1DBConnection;
import connection.Type3DBConnection;

public class DBConnectionFactoryImpairs extends AbstractDBConnectionFactory {

	@Override
	public DBConnection getDBConnection(String ConnectionType) {
		if (ConnectionType.equalsIgnoreCase("TYPE1")) {
			return new Type1DBConnection();

		} else if (ConnectionType.equalsIgnoreCase("TYPE3")) {
			return new Type3DBConnection();
		}

		return null;
	}
}
