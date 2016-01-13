package connection;

public class Type3DBConnection implements DBConnection {

	@Override
	public void connect() {
		System.out.println("Type 3 DB connection established");
	}

	@Override
	public void executeQuery(String query) {
		System.out.println("Type 3 DB query executed");
	}

}
