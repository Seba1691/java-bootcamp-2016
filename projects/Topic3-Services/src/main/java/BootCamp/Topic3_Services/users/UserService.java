package BootCamp.Topic3_Services.users;

public interface UserService {

	public void addUser(User user);

	public void updateUser(String userName, User userUpdate) throws UserNotFoundException;

	public void deleteUser(String userName) throws UserNotFoundException;;

	public User getUserByUserName(String userName) throws UserNotFoundException;
}
