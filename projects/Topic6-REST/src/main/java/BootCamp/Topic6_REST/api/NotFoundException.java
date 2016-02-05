package BootCamp.Topic6_REST.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {

	public NotFoundException(String msg) {
		super(msg);
	}
}
