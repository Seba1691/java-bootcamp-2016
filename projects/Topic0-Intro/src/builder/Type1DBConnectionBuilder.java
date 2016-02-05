package builder;

import connection.BuilderDBConnection;

public class Type1DBConnectionBuilder implements DBConnectionBuilder {

	private BuilderDBConnection dbConnection = null;

	public Type1DBConnectionBuilder() {
		dbConnection = new BuilderDBConnection();
	}

	@Override
	public BuilderDBConnection getDBConnection() {
		return dbConnection;
	}

	@Override
	public void buildTypeConnection() {
		dbConnection.setType("Type1");
	}

}
