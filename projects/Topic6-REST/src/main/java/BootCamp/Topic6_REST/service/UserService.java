package BootCamp.Topic6_REST.service;

import java.util.List;

import BootCamp.Topic6_REST.api.NotFoundException;
import BootCamp.Topic6_REST.model.User;

/**
 * 
 * This interface defines the operations that every UserService should
 * implement.
 *
 */
public interface UserService {

	/**
	 * Creates and add a new user into the appropriate data structure.
	 * 
	 * @param user
	 *            indicates the user that will be added
	 */
	public void addUser(User user);

	/**
	 * Updates the user’s information in the data structure.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find the user to
	 *            update
	 * @param userUpdate
	 *            new user's information
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public void updateUser(String userName, User userUpdate) throws NotFoundException;

	/**
	 * Removes a specified user from the data structure
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find the user to
	 *            delete
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public void deleteUser(String userName) throws NotFoundException;;

	/**
	 * Returns the user’s information of an existent user by its username.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find user
	 * @return user by its user name
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public User getUserByUserName(String userName) throws NotFoundException;

	/**
	 * Returns all users that has a specific first name.
	 * 
	 * @param firstName
	 *            indicates the first name that will be used to find user
	 * @return users list with that first name
	 */
	public List<User> getUserByFirstName(String firstName);
}
