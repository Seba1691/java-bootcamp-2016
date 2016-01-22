package connection;

public class Type4DBConnection implements DBConnection {

	@Override
	public void connect() {
		System.out.println("Type 4 DB connection established");
	}

	@Override
	public void executeQuery(String query) {
		System.out.println("Type 4 DB query executed");
	}

}
