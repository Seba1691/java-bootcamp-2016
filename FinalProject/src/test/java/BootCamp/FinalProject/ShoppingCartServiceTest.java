package BootCamp.FinalProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import BootCamp.FinalProject.app.ShoppingCartApplication;
import BootCamp.FinalProject.model.CartItem;
import BootCamp.FinalProject.services.cart.ShoppingCartService;
import BootCamp.FinalProject.services.cart.ShoppingCartServiceFactory;
import BootCamp.FinalProject.services.cart.ShoppingCartServiceFactory.ShoppingCartServiceTypes;
import BootCamp.FinalProject.services.product.ProductServiceFactory;
import BootCamp.FinalProject.services.product.ProductServiceFactory.ProductServiceTypes;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShoppingCartApplication.class)
@WebAppConfiguration
public class ShoppingCartServiceTest {

	private ShoppingCartService service;
	private MockMvc mockMvc;
	private String userTest = "test";
	private String skuProductTest = "12ab";

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
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userTest, "1234"));
		mockMvc = webAppContextSetup(webApplicationContext).build();
		service = ShoppingCartServiceFactory.getShoppingCartService(ShoppingCartServiceTypes.DATABASEIMP);
		service.clearCart(userTest);
	}

	@After
	public void tearDown() {
		service = null;
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenNonExistingProductIsAddedItIsAddedToCart() throws Exception {
		mockMvc.perform(post("/cart/add/" + skuProductTest)).andExpect(status().isNoContent());
		Assert.assertFalse(service.getContents(userTest).isEmpty());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenExistingProductIsAddedItsQuantityIsIncreased() throws Exception {
		service.addItem(skuProductTest, userTest);
		mockMvc.perform(post("/cart/add/" + skuProductTest)).andExpect(status().isNoContent());
		Assert.assertTrue(service.getContents(userTest).get(0).getQuantity() == 2);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenItemIsDeletedItIsDeletedFromCart() throws Exception {
		service.addItem(skuProductTest, userTest);
		mockMvc.perform(delete("/cart/remove/" + skuProductTest)).andExpect(status().isNoContent());
		Assert.assertTrue(service.getContents(userTest).isEmpty());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenItemIsDeletedAndItDoesNotExistExceptionIsThrown() throws Exception {
		mockMvc.perform(delete("/cart/remove/" + skuProductTest)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void whenCartContainIsAskedItIsObtained() throws Exception {
		for (int i = 0; i < 10; i++) {
			service.addItem(skuProductTest, userTest);
		}
		List<CartItem> content = new ArrayList<CartItem>();
		content.add(new CartItem(ProductServiceFactory.getProdcutService(ProductServiceTypes.DATABASEIMP).getbySKU(skuProductTest), 10));
		mockMvc.perform(get("/cart/getcontents/")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(content().string(json(content)));
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
