package BootCamp.FinalProject.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class NotEnoughStockException extends ApiException {

	public NotEnoughStockException() {
		super("There are not enough stock for some products in the cart");
	}
}
