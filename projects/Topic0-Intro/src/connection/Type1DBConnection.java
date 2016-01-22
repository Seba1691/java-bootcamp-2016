package connection;

public class Type1DBConnection implements DBConnection {

	@Override
	public void connect() {
		System.out.println("Type 1 DB connection established");
	}

	@Override
	public void executeQuery(String query) {
		System.out.println("Type 1 DB query executed");
	}

}
