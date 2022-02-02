package com.foodlogiq.event.controller;

/*import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.foodlogiq.event.model.Content;
import com.foodlogiq.event.model.Event;
import com.foodlogiq.event.util.FoodLogiQTestUtil;*/

/*@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventControllerTest {

	// Bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	/**
	 * Test the create event request
	 * 
	 */ 
	/*@Test
	void testCreateEvent() throws Exception {
		
		
		Event event = new Event();
		event.setType("shipping");
		List<Content> contentList = new ArrayList<>();
		Content content = new Content("12345678901234", "123ABC", event);
		contentList.add(content);
		event.setContents(contentList);
		
		try {
			HttpHeaders headers = createHttpHeaders(FoodLogiQTestUtil.AUTH_TOKEN);
			HttpEntity<Event> entity = new HttpEntity<>(event, headers);
			ResponseEntity<String> response = restTemplate.exchange(
					FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_CREATE, HttpMethod.POST,
					entity, String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exception) {
			System.out.println("EventControllerTest:" + exception.getMessage());
		}

	}*/

	/**
	 * Test the deleteEventById request
	 */
	/*@Test
	void testDeleteEvent() {
		long eventId = 1;
		try {
			HttpHeaders headers = createHttpHeaders(FoodLogiQTestUtil.AUTH_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(
					FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_DELETE_FETCH + eventId, HttpMethod.DELETE,
					entity, String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exception) {
			System.out.println("EventControllerTest:" + exception.getMessage());
		}
	}*/

	/**
	 * Test the getEventById request
	 */
	/*@Test
	void testGetEventById() {
		long eventId = 1;
		try {
			HttpHeaders headers = createHttpHeaders(FoodLogiQTestUtil.AUTH_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(
					FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_DELETE_FETCH + eventId, HttpMethod.GET,
					entity, String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exception) {
			System.out.println("EventControllerTest:" + exception.getMessage());
		}
	}*/

	/**
	 * Test the getAllEvents request
	 * 
	 */
	/*@Test
	void testGetAllEvents() throws MalformedURLException {
		try {
			HttpHeaders headers = createHttpHeaders(FoodLogiQTestUtil.AUTH_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(
					FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_FETCH_ALL, HttpMethod.GET, entity,
					String.class);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception exception) {
			System.out.println("EventControllerTest:" + exception.getMessage());
		}

	}*/
	

	/**
	 * Test the authentication with invalid authentication token
	 * 
	 */
	/*@Test
	void testAuthendication() throws MalformedURLException {
		try {
			HttpHeaders headers = createHttpHeaders(FoodLogiQTestUtil.INVALID_AUTH_TOKEN);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(
					FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_FETCH_ALL, HttpMethod.GET, entity,
					String.class);
			assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		} catch (Exception exception) {
			System.out.println("EventControllerTest:" + exception.getMessage());
		}

	}*/

	/**
	 * Add authorization token in HttpHeaders
	 * 
	 * @return HttpHeaders
	 */
	/*private HttpHeaders createHttpHeaders(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", accessToken);
		return headers;
	}

}*/
