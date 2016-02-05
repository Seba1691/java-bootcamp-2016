package BootCamp.Topic6_REST.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import BootCamp.Topic6_REST.model.User;
import BootCamp.Topic6_REST.service.UserServiceFactory;
import BootCamp.Topic6_REST.service.UserServiceFactory.UserServiceTypes;

/**
 * This controller provide a REST api to perform CRUD operation over users.
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User body) throws NotFoundException {
		UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP).addUser(body);
		ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(value = "/getbyusername/{username}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username) throws NotFoundException {
		User user = UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP).getUserByUserName(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/getbyfirstname/{username}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUserByFirstName(@PathVariable("firstname") String firstname) throws NotFoundException {
		List<User> user = UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP).getUserByFirstName(firstname);
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{username}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@PathVariable("username") String username, @RequestBody User body) throws NotFoundException {
		UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP).updateUser(username, body);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) throws NotFoundException {
		UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP).deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
