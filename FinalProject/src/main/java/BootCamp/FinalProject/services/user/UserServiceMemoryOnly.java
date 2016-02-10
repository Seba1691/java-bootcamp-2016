package BootCamp.FinalProject.services.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.User;

/**
 * This class is responsible for implementing the services provided for the
 * UserService interface. This class uses a hash list to store the users. *
 */
public class UserServiceMemoryOnly implements UserService {

	/**
	 * List which represents the users stored.
	 */
	private HashMap<String, User> data;

	private static UserServiceMemoryOnly instance = null;

	public UserServiceMemoryOnly() {
		data = new HashMap<String, User>();
	}

	public static UserServiceMemoryOnly getInstance() {
		if (instance == null) {
			instance = new UserServiceMemoryOnly();
		}
		return instance;
	}

	public HashMap<String, User> getData() {
		return data;
	}

	/**
	 * Inserts the new user into the hash list. Where the key will be its
	 * UserName. *
	 * 
	 * @param user
	 *            indicates the user that will be added
	 */
	public void addUser(User user) {
		data.put(user.getUsername(), user);
	}

	/**
	 * Finds and updates the information of existent user.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find the updated
	 *            user
	 * @param userUpdate
	 *            new user's information
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public void updateUser(String userName, User userUpdate) throws NotFoundException {
		User currentUser = data.get(userName);
		if (currentUser != null) {
			currentUser.setPassword(userUpdate.getPassword());
			currentUser.setFirstName(userUpdate.getFirstName());
			currentUser.setLastName(userUpdate.getLastName());
			currentUser.setEmail(userUpdate.getEmail());
		} else
			throw new NotFoundException("User " + userName + " not found");
	}

	/**
	 * Removes an existent user from the hash table.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find user to
	 *            delete
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public void deleteUser(String userName) throws NotFoundException {
		if (data.get(userName) != null) {
			data.remove(userName);
		} else {
			throw new NotFoundException("User " + userName + " not found");
		}
	}

	/**
	 * Returns the user’s information of an existent user by its username.
	 * 
	 * @param userName
	 *            indicates the user name that will be used to find user
	 * @return user by its user name
	 * @throws NotFoundException
	 *             when user is not found
	 */
	public User getUserByUserName(String userName) throws NotFoundException {

		User user = data.get(userName);
		if (user != null) {
			return user;
		} else {
			throw new NotFoundException("User " + userName + " not found");
		}
	}

	/**
	 * Returns all users that has a specific first name.
	 * 
	 * @param firstName
	 *            indicates the first name that will be used to find user
	 * @return users list with that first name
	 */
	public List<User> getUserByFirstName(String firstName) {
		List<User> usersByFirstName = new ArrayList<User>();
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getFirstName().equals(firstName)) {
				usersByFirstName.add(data.get(i));
			}
		}
		return usersByFirstName;
	}

	/**
	 * Deletes all users from the data container
	 * 
	 */
	public void cleanData() {
		data = new HashMap<String, User>();
	}

}
