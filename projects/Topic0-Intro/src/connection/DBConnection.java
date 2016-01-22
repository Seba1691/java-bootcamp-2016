package connection;

public interface DBConnection {

	public void connect();

	public void executeQuery(String query);
}
