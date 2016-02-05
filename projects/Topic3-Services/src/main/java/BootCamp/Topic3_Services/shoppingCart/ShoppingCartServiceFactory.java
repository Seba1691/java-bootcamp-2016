package BootCamp.Topic3_Services.shoppingCart;

public class ShoppingCartServiceFactory {

	public enum ShoppingCartServiceTypes {
		MEMORYIMP
	}

	public static ShoppingCartService getShoppingCartService(ShoppingCartServiceTypes type) {
		if (type.equals(ShoppingCartServiceTypes.MEMORYIMP)) {
			return new ShoppingCartServiceImp();
		}
		return null;
	}
}
