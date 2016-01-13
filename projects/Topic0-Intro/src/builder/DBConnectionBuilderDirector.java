package builder;

import connection.BuilderDBConnection;

public class DBConnectionBuilderDirector {
	
	private DBConnectionBuilder dbConnectionBuilder;
	
	public DBConnectionBuilderDirector(DBConnectionBuilder dbConnectionBuilder){
		this.dbConnectionBuilder = dbConnectionBuilder;		
	}
	
	public void contructDBConnection(){
		dbConnectionBuilder.buildTypeConnection();
	}
	
	public BuilderDBConnection getDBConnection(){
		return dbConnectionBuilder.getDBConnection();
	}

}
