package BootCamp.FinalProject.services.cart;

/**
 * This class is responsible for creating the appropriate shopping service object that will be used.
 */
public class ShoppingCartServiceFactory {

	/**
	 * This enumerator enumerates the different implementations of the ShoppingCartService interface in order to be created by the factory. 
	 */
	public enum ShoppingCartServiceTypes {
		MEMORYIMP
	}

	/**
	 * This method returns the right ShoppingCartService implementation based on the ShoppingCartServiceType that it gets by parameter.
	 * @param type indicates the type of service that will be created
	 * @return a ShoppingCartService interface implementation
	 */
	public static ShoppingCartService getShoppingCartService(ShoppingCartServiceTypes type) {
		if (type.equals(ShoppingCartServiceTypes.MEMORYIMP)) {
			return new ShoppingCartServiceImp();
		}
		return null;
	}
}
