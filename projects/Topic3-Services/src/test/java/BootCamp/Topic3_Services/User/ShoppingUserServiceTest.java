package BootCamp.Topic3_Services.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BootCamp.Topic3_Services.users.User;
import BootCamp.Topic3_Services.users.UserNotFoundException;
import BootCamp.Topic3_Services.users.UserServiceFactory;
import BootCamp.Topic3_Services.users.UserServiceFactory.UserServiceTypes;
import BootCamp.Topic3_Services.users.UserServiceMemoryOnly;

/**
 * Unit test for simple App.
 */
public class ShoppingUserServiceTest {

	private UserServiceMemoryOnly service;

	@Before
	public void setUp() {
		service = (UserServiceMemoryOnly) UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP);
	}

	@After
	public void tearDown() {
		service = null;
	}

	public void whenUserIsCreatedItIsAddedToDB(){
		service.addUser(new User("John12", "123456", "John", "Smith", "jsmith@mail.com"));
		Assert.assertNotNull(service.getData().get("John12"));
	}

	@Test
	public void whenUserIsUpdatedItIsUpdatedInTheDB() throws UserNotFoundException {
		service.addUser(new User("John12", "123456", "John", "Smith", "jsmith@mail.com"));
		User updateUser = new User("John12", "123456", "John", "Jackson", "jsmith@mail.com");
		service.updateUser("John12", updateUser);
		Assert.assertEquals(updateUser, service.getData().get("John12"));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void whenUserIsUpdatedAndItDoesNotExistExceptionIsThrown() throws UserNotFoundException {
		User updateUser = new User("John12", "123456", "John", "Jackson", "jsmith@mail.com");
		service.updateUser("John11", updateUser);		
	}

	@Test
	public void whenUserIsDeletedItIsDeletedFromDB() throws UserNotFoundException {
		service.addUser(new User("John12", "123456", "John", "Smith", "jsmith@mail.com"));
		service.deleteUser("John12");
		Assert.assertNull(service.getData().get("John12"));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void whenUserIsDeletedAndItDoesNotExistExceptionIsThrown() throws UserNotFoundException {
		service.deleteUser("John12");	
	}

	@Test
	public void whenUserIsAskedItIsObtained() throws UserNotFoundException {
		User user = new User("John12", "123456", "John", "Smith", "jsmith@mail.com");
		service.addUser(user);
		Assert.assertEquals(user, service.getUserByUserName("John12"));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void whenUserIsAskedAndItDoesNotExistExceptionIsThrown() throws UserNotFoundException {
		service.getUserByUserName("John12");
	}

}
