package BootCamp.Topic3_Services.users;

public class UserServiceFactory {

	public enum UserServiceTypes {
		MEMORYIMP, DATABASEIMP
	}

	public static UserService getUserService(UserServiceTypes type) {
		if (type.equals(UserServiceTypes.MEMORYIMP)) {
			return new UserServiceMemoryOnly();
		}
		if (type.equals(UserServiceTypes.DATABASEIMP)) {
			return new UserServiceDataBase();
		}
		return null;
	}
}
