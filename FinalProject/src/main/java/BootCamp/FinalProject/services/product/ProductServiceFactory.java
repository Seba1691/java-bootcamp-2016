package BootCamp.FinalProject.services.product;

/**
 * This class is responsible for creating the appropriate product service object
 * that will be used.
 */
public class ProductServiceFactory {

	/**
	 * This enumerator enumerates the different implementations of the
	 * ProductService interface in order to be created by the factory
	 */
	public enum ProductServiceTypes {
		MEMORYIMP
	}

	/**
	 * This method returns the right ProductService implementation based on the
	 * ProductServiceType that it gets by parameter.
	 * 
	 * @param type
	 *            indicates the type of service that will be created
	 * @return a ProductService interface implementation
	 */
	public static ProductService getProdcutService(ProductServiceTypes type) {
		if (type.equals(ProductServiceTypes.MEMORYIMP)) {
			return new ProductServiceImp();
		}
		return null;
	}
}
