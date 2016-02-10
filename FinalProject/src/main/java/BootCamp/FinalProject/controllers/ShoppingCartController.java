package BootCamp.FinalProject.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BootCamp.FinalProject.controllers.exceptions.NotEnoughStockException;
import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.CartItem;
import BootCamp.FinalProject.services.cart.ShoppingCartService;
import BootCamp.FinalProject.services.cart.ShoppingCartServiceFactory;
import BootCamp.FinalProject.services.cart.ShoppingCartServiceFactory.ShoppingCartServiceTypes;

/**
 * This controller provide a REST api to perform Shopping Cart operations.
 */
@Controller
@RequestMapping(value = "/cart")
public class ShoppingCartController {

	private ShoppingCartService service = ShoppingCartServiceFactory.getShoppingCartService(ShoppingCartServiceTypes.MEMORYIMP);

	@RequestMapping(value = "/add/{sku}", method = RequestMethod.POST)
	public ResponseEntity<Void> addItem(@PathVariable("sku") String sku) throws NotFoundException {
		service.addItem(sku, getCurrentUser());
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return response;
	}

	@RequestMapping(value = "/remove/{sku}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable("sku") String sku) throws NotFoundException {
		service.removeItem(sku, getCurrentUser());
		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return response;
	}

	@RequestMapping(value = "/getcontents", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CartItem>> getContents() {
		List<CartItem> cartContents = service.getContents(getCurrentUser());
		return new ResponseEntity<List<CartItem>>(cartContents, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.PUT)
	public ResponseEntity<Void> checkout() throws NotEnoughStockException {
		service.checkout(getCurrentUser());
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	private String getCurrentUser() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
