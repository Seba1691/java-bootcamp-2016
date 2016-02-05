package connection;

public class Type2DBConnection implements DBConnection {

	@Override
	public void connect() {
		System.out.println("Type 2 DB connection established");
	}

	@Override
	public void executeQuery(String query) {
		System.out.println("Type 2 DB query executed");
	}

}
