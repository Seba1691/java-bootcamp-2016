package builder;

import connection.BuilderDBConnection;

public class Type2DBConnectionBuilder implements DBConnectionBuilder {

	private BuilderDBConnection dbConnection = null;

	public Type2DBConnectionBuilder() {
		dbConnection = new BuilderDBConnection();
	}

	@Override
	public BuilderDBConnection getDBConnection() {
		return dbConnection;
	}

	@Override
	public void buildTypeConnection() {
		dbConnection.setType("Type2");
	}

}
