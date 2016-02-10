package BootCamp.FinalProject.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.Product;
import BootCamp.FinalProject.services.product.ProductService;
import BootCamp.FinalProject.services.product.ProductServiceFactory;
import BootCamp.FinalProject.services.product.ProductServiceFactory.ProductServiceTypes;

/**
 * This controller provide a REST api to perform Products operations.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductsController {

	private ProductService service = ProductServiceFactory.getProdcutService(ProductServiceTypes.DATABASEIMP);

	@RequestMapping(value = "/getbysku/{sku}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Product> getbySKU(@PathVariable("sku") String sku) throws NotFoundException {
		Product product = service.getbySKU(sku);
		ResponseEntity<Product> response = new ResponseEntity<Product>(product, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/getbyname/{name}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByName(@PathVariable("name") String name) throws NotFoundException {
		List<Product> product = service.getByName(name);
		ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/getbycategory/{category}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") String category) throws NotFoundException {
		List<Product> product = service.getByCategory(category);
		ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		return response;
	}
}
