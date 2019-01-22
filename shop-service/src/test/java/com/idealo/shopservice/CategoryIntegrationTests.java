package com.idealo.shopservice;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealo.input.CategoryInput;
import com.idealo.output.APIResponse;
import com.idealo.output.CategoryOutPut;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	
	@Test
	public void testInvalidInputCategory() throws JSONException {
		CategoryInput inp = new CategoryInput();
		
		HttpEntity<CategoryInput> entity = new HttpEntity<CategoryInput>(inp, headers);
		ResponseEntity<APIResponse> response = restTemplate.exchange(createURLWithPort("/category"), HttpMethod.POST, entity,
				APIResponse.class);
		System.out.println(response.getBody().isSuccess());
		assertFalse(response.getBody().isSuccess());
		
	}
	
	@Test
	public void testInvalidMediaType() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>("TEST", headers);
		ResponseEntity<APIResponse> response = restTemplate.exchange(createURLWithPort("/category"), HttpMethod.POST, entity,
				APIResponse.class);
		assert(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		
	}
	
	@Test
	public void testCreateCategory() throws JSONException {
		CategoryInput inp = new CategoryInput();
		inp.setTitle("TEST_TITLE");
		HttpEntity<CategoryInput> entity = new HttpEntity<CategoryInput>(inp, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/category"), HttpMethod.POST, entity,
				String.class);
		String expected = "{success:true,data:{id:1,title:TEST_TITLE}}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		testCreateDuplicateCategory();
		
	}
	
	@Test
	public void testListCategory() throws JSONException {
	
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<APIResponse> response = restTemplate.exchange(
				createURLWithPort("/category"),
				HttpMethod.GET, entity, APIResponse.class);
		assertTrue(response.getBody().isSuccess());
		
	}
	

	public void testCreateDuplicateCategory() throws JSONException {
		
		CategoryInput inp = new CategoryInput();
		inp.setTitle("TEST_TITLE");
		HttpEntity<CategoryInput> entity = new HttpEntity<CategoryInput>(inp, headers);
		ResponseEntity<APIResponse> response = restTemplate.exchange(createURLWithPort("/category"), HttpMethod.POST, entity,
				APIResponse.class);
		assertFalse(response.getBody().isSuccess());
		
	}
	

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
