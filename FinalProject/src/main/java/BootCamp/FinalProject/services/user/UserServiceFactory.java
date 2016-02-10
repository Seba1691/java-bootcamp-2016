package BootCamp.FinalProject.services.user;

/**
 * This class is responsible for creating the appropriate user service object
 * that will be used.
 */
public class UserServiceFactory {
	/**
	 * This enumerator enumerates the different implementations of the
	 * UserService interface in order to be created by the factory
	 */
	public enum UserServiceTypes {
		DATABASEIMP
	}

	/**
	 * This method returns the right UserService implementation based on the
	 * UserServiceType that it gets by parameter.
	 * 
	 * @param type
	 *            indicates the type of service that will be created
	 * @return a UserService interface implementation
	 */
	public static UserService getUserService(UserServiceTypes type) {
		if (type.equals(UserServiceTypes.DATABASEIMP)) {
			return new UserServiceDataBase();
		}
		return null;
	}
}
