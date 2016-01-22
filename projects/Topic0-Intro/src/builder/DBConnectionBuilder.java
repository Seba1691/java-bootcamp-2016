package builder;

import connection.BuilderDBConnection;

public interface DBConnectionBuilder {

	public BuilderDBConnection getDBConnection();

	public void buildTypeConnection();

}
