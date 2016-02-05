package BootCamp.Topic3_Services.users;

import java.util.HashMap;

public class UserServiceMemoryOnly implements UserService {

	private HashMap<String, User> data;

	public HashMap<String, User> getData() {
		return data;
	}

	public UserServiceMemoryOnly() {
		data = new HashMap<String, User>();
	}

	public void addUser(User user) {
		data.put(user.getUserName(), user);
	}

	public void updateUser(String userName, User userUpdate) throws UserNotFoundException {
		User currentUser = data.get(userName);
		if (currentUser != null) {
			currentUser.setPassword(userUpdate.getPassword());
			currentUser.setFirstName(userUpdate.getFirstName());
			currentUser.setLastName(userUpdate.getLastName());
			currentUser.setEmail(userUpdate.getEmail());
		} else
			throw new UserNotFoundException("User " + userName + " not found");
	}

	public void deleteUser(String userName) throws UserNotFoundException {
		if (data.get(userName) != null) {
			data.remove(userName);
		} else {
			throw new UserNotFoundException("User " + userName + " not found");
		}
	}

	public User getUserByUserName(String userName) throws UserNotFoundException {
		User user = data.get(userName);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User " + userName + " not found");
		}
	}

}
