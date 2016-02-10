package BootCamp.FinalProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import BootCamp.FinalProject.app.ShoppingCartApplication;
import BootCamp.FinalProject.controllers.exceptions.NotFoundException;
import BootCamp.FinalProject.model.User;
import BootCamp.FinalProject.services.user.UserService;
import BootCamp.FinalProject.services.user.UserServiceFactory;
import BootCamp.FinalProject.services.user.UserServiceFactory.UserServiceTypes;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShoppingCartApplication.class)
@WebAppConfiguration
@Transactional
public class UserServiceTest {

	private UserService service;

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@SuppressWarnings("rawtypes")
	@Autowired
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		service = UserServiceFactory.getUserService(UserServiceTypes.DATABASEIMP);
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {

		service = null;
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenUserIsCreatedItIsAddedToDB() throws Exception {
		User newUser = new User("John12", "123456", "John", "Smith", "jsmith@mail.com");
		mockMvc.perform(post("/users/add").content(this.json(newUser)).contentType(contentType)).andExpect(status().isCreated());
		Assert.assertNotNull(service.getUserByUserName("John12"));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenUserIsUpdatedItIsUpdatedInTheDB() throws Exception {
		String userName = "John12";
		service.addUser(new User(userName, "123456", "John", "Smith", "jsmith@mail.com"));
		User updateUser = new User(userName, "123456", "John", "Jackson", "jsmith@mail.com");
		mockMvc.perform(put("/users/update/" + userName).content(this.json(updateUser)).contentType(contentType)).andExpect(status().isNoContent());
		Assert.assertEquals(updateUser, service.getUserByUserName(userName));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenUserIsUpdatedAndItDoesNotExistExceptionIsThrown() throws IOException, Exception {
		User updateUser = new User("Peter12", "123456", "Peter", "Harrison", "pharrison@mail.com");
		mockMvc.perform(put("/users/update/Peter12").content(this.json(updateUser)).contentType(contentType)).andExpect(status().isNotFound());
	}

	@Test(expected = NotFoundException.class)
	@Transactional
	@Rollback(true)
	public void whenUserIsDeletedItIsDeletedFromDB() throws Exception {
		String userName = "John12";
		service.addUser(new User(userName, "123456", "John", "Smith", "jsmith@mail.com"));
		mockMvc.perform(delete("/users/delete/John12")).andExpect(status().isNoContent());
		service.getUserByUserName(userName);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenUserIsDeletedAndItDoesNotExistExceptionIsThrown() throws Exception {
		mockMvc.perform(delete("/users/delete/Peter12")).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenUserIsAskedItIsObtained() throws Exception {
		String userName = "John12";
		service.addUser(new User(userName, "123456", "John", "Smith", "jsmith@mail.com"));
		mockMvc.perform(get("/users/getbyusername/" + userName)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(content().string(json(service.getUserByUserName("John12"))));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenUserIsAskedAndItDoesNotExistExceptionIsThrown() throws Exception {
		mockMvc.perform(get("/users/getbyusername/Peter12")).andExpect(status().isNotFound());
	}

	@SuppressWarnings("unchecked")
	protected String json(Object o) {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		try {
			this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		} catch (HttpMessageNotWritableException | IOException e) {
			e.printStackTrace();
		}
		return mockHttpOutputMessage.getBodyAsString();
	}

}
