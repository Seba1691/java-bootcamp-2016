package main;

import builder.DBConnectionBuilderDirector;
import builder.Type1DBConnectionBuilder;
import builder.Type2DBConnectionBuilder;
import factory.AbstractDBConnectionFactory;
import factory.DBConnectionFactoryImpairs;
import factory.DBConnectionFactoryPairs;
import proxy.Proxy;
import singleton.SingletonDBConnection;

public class Main {

	public static void main(String[] args) {
		// Singleton
		System.out.println("Singleton Example");
		SingletonDBConnection.getInstance().getDBConnection().connect();

		// Factory
		System.out.println("Factory Example");
		AbstractDBConnectionFactory factory = new DBConnectionFactoryImpairs();
		factory.getDBConnection("TYPE1").connect();
		factory.getDBConnection("TYPE3").connect();
		factory = new DBConnectionFactoryPairs();
		factory.getDBConnection("TYPE2").connect();
		factory.getDBConnection("TYPE4").connect();

		// Proxy
		System.out.println("Proxy Example");
		Proxy proxy = new Proxy();
		proxy.connect();
		proxy.executeQuery("SELECT * FROM USERS");

		// Builder
		System.out.println("Builder Example");
		// Type 1
		DBConnectionBuilderDirector dbDirector = new DBConnectionBuilderDirector(new Type1DBConnectionBuilder());
		dbDirector.contructDBConnection();
		dbDirector.getDBConnection().connect();
		// Type 2
		dbDirector = new DBConnectionBuilderDirector(new Type2DBConnectionBuilder());
		dbDirector.contructDBConnection();
		dbDirector.getDBConnection().connect();

	}

}
