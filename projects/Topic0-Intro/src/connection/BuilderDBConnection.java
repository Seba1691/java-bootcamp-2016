package connection;

public class BuilderDBConnection implements DBConnection {

	private String Type = "";

	public void setType(String Type) {
		this.Type = Type;
	}

	@Override
	public void connect() {
		System.out.println("Builder " + Type + " DB connection established");
	}

	@Override
	public void executeQuery(String query) {
		System.out.println("Builder " + Type + " DB query executed");
	}

}
