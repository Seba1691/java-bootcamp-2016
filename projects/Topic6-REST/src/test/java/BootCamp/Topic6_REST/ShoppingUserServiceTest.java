package BootCamp.Topic6_REST;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import BootCamp.Topic6_REST.app.UserServiceApplication;
import BootCamp.Topic6_REST.model.User;
import BootCamp.Topic6_REST.service.UserServiceFactory;
import BootCamp.Topic6_REST.service.UserServiceFactory.UserServiceTypes;
import BootCamp.Topic6_REST.service.UserServiceMemoryOnly;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserServiceApplication.class)
@WebAppConfiguration
public class ShoppingUserServiceTest {

	private UserServiceMemoryOnly service;

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		service = (UserServiceMemoryOnly) UserServiceFactory.getUserService(UserServiceTypes.MEMORYIMP);
		service.cleanData();
	}

	@After
	public void tearDown() {
		service = null;
	}

	@Test
	public void whenUserIsCreatedItIsAddedToDB() throws Exception {
		User newUser = new User("John12", "123456", "John", "Smith", "jsmith@mail.com");
		mockMvc.perform(post("/users/add").content(this.json(newUser)).contentType(contentType)).andExpect(status().isCreated());
		Assert.assertNotNull(service.getData().get("John12"));
	}

	@Test
	public void whenUserIsUpdatedItIsUpdatedInTheDB() throws Exception {
		String userName = "John12";
		service.getData().put(userName, new User(userName, "123456", "John", "Smith", "jsmith@mail.com"));
		User updateUser = new User(userName, "123456", "John", "Jackson", "jsmith@mail.com");
		mockMvc.perform(put("/users/update/" + userName).content(this.json(updateUser)).contentType(contentType)).andExpect(status().isOk());
		Assert.assertEquals(updateUser, service.getData().get(userName));
	}

	@Test
	public void whenUserIsUpdatedAndItDoesNotExistExceptionIsThrown() throws IOException, Exception {
		User updateUser = new User("John12", "123456", "John", "Jackson", "jsmith@mail.com");
		mockMvc.perform(put("/users/update/John12").content(this.json(updateUser)).contentType(contentType)).andExpect(status().isNotFound());
	}

	@Test
	public void whenUserIsDeletedItIsDeletedFromDB() throws Exception {
		String userName = "John12";
		service.getData().put(userName, new User(userName, "123456", "John", "Smith", "jsmith@mail.com"));
		mockMvc.perform(delete("/users/delete/John12")).andExpect(status().isOk());
		Assert.assertNull(service.getData().get(userName));
	}

	@Test
	public void whenUserIsDeletedAndItDoesNotExistExceptionIsThrown() throws Exception {
		mockMvc.perform(delete("/users/delete/John12")).andExpect(status().isNotFound());
	}

	@Test
	public void whenUserIsAskedItIsObtained() throws Exception {
		String userName = "John12";
		service.getData().put(userName, new User(userName, "123456", "John", "Smith", "jsmith@mail.com"));
		mockMvc.perform(get("/users/getbyusername/" + userName)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(content().string(json(service.getUserByUserName("John12"))));
	}

	@Test
	public void whenUserIsAskedAndItDoesNotExistExceptionIsThrown() throws Exception {
		mockMvc.perform(get("/users/getbyusername/John12")).andExpect(status().isNotFound());
	}

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
