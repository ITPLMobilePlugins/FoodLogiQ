package com.foodlogiq.event.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.http.ResponseEntity;

import com.foodlogiq.event.config.FoodLogiQMessageConfiguration;
import com.foodlogiq.event.model.Content;
import com.foodlogiq.event.model.Event;
import com.foodlogiq.event.util.EventResponse;
import com.foodlogiq.event.util.FoodLogiQTestUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Test the event create, delete, fetch, fetch all request
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventControllerTest {

	// Bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private FoodLogiQMessageConfiguration foodLogiQMessageConfiguration;

	// Event id to delete the event
	private Long eventId = 1l;
	private Long eventIdNotExist = 100000l;

	/**
	 * Test the authentication with invalid authentication token
	 * 
	 */
	@Test
	void testAuthendication() {

		ResponseEntity<?> response = getResponse(new Event(), FoodLogiQTestUtil.INVALID_AUTH_TOKEN);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

	/**
	 * Test the create event without event type request
	 * 
	 */
	@Test
	void testCreateEventWithoutType() {

		Event event = new Event();
		List<Content> contentList = new ArrayList<>();
		Content content = new Content("12345678901234", "123ABC", event);
		contentList.add(content);
		event.setContents(contentList);

		ResponseEntity<?> response = getResponse(event, FoodLogiQTestUtil.AUTH_TOKEN);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the create without content
	 * 
	 */
	@Test
	void testCreateEventWithoutContent() throws Exception {

		Event event = new Event();
		event.setType("shipping");

		ResponseEntity<?> response = getResponse(event, FoodLogiQTestUtil.AUTH_TOKEN);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the create event with invalid GTIN request
	 * 
	 */
	@Test
	void testCreateEventWithInvalidGTIN() throws Exception {

		Event event = new Event();
		event.setType("shipping");
		List<Content> contentList = new ArrayList<>();
		Content content = new Content("12345678901234a", "123ABC", event);
		contentList.add(content);
		event.setContents(contentList);

		ResponseEntity<?> response = getResponse(event, FoodLogiQTestUtil.AUTH_TOKEN);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the create without GTIN lot
	 * 
	 */
	@Test
	void testCreateEventWithoutGTINLot() throws Exception {

		Event event = new Event();
		event.setType("shipping");
		List<Content> contentList = new ArrayList<>();
		Content content = new Content("12345678901234", "", event);
		contentList.add(content);
		event.setContents(contentList);

		ResponseEntity<?> response = getResponse(event, FoodLogiQTestUtil.AUTH_TOKEN);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the create event request
	 * 
	 */
	@Test
	void testCreateEvent() throws Exception {

		Event event = new Event();
		event.setType("shipping");
		List<Content> contentList = new ArrayList<>();
		Content content = new Content("12345678901234", "123ABC", event);
		contentList.add(content);
		event.setContents(contentList);

		ResponseEntity<?> response = getResponse(event, FoodLogiQTestUtil.AUTH_TOKEN);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Make rest call using restTemplate and return ResponseEntity
	 * 
	 * @return ResponseEntity
	 */
	private ResponseEntity<?> getResponse(Event event, String accessToken) {
		HttpHeaders headers = FoodLogiQTestUtil.createHttpHeaders(accessToken);
		HttpEntity<?> entity = new HttpEntity<>(event, headers);
		return restTemplate.exchange(FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_CREATE, HttpMethod.POST,
				entity, String.class);

	}

	/**
	 * Test the fetch event request with event id which does not exist in
	 * persistence storage
	 * 
	 */
	@Test
	void testFetchEventWithIdNotExist() {

		ResponseEntity<?> response = getResponse(FoodLogiQTestUtil.AUTH_TOKEN, eventIdNotExist, HttpMethod.GET);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the supplied event ID does exist, but not accessible by the user
	 * 
	 */
	@Test
	void testFetchEventWithUnaccessibleUser() {

		// Pass the access token that was not used for event creation
		ResponseEntity<?> response = getResponse(FoodLogiQTestUtil.AUTH_TOKEN1, eventId, HttpMethod.GET);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the delete event request with event id which does not exist in
	 * persistence storage
	 * 
	 */
	@Test
	void testDeleteEventWithIdNotExist() {

		ResponseEntity<?> response = getResponse(FoodLogiQTestUtil.AUTH_TOKEN, eventIdNotExist, HttpMethod.DELETE);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the supplied event ID does exist, but not accessible by the user
	 * 
	 */
	@Test
	void testDeleteEventWithUnaccessibleUser() {

		// Pass the access token that was not used for event creation
		ResponseEntity<?> response = getResponse(FoodLogiQTestUtil.AUTH_TOKEN1, eventId, HttpMethod.DELETE);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	/**
	 * Test the delete event request
	 * 
	 */
	@Test
	void testDeleteEvent() {

		ResponseEntity<?> response = getResponse(FoodLogiQTestUtil.AUTH_TOKEN, eventId, HttpMethod.DELETE);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Test the user has no events, return an empty list
	 * 
	 */
	@Test
	void testEmptyResponseUserHasNoEvent() {

		ResponseEntity<?> response = getResponse(FoodLogiQTestUtil.AUTH_TOKEN1, -1l, HttpMethod.GET);
		Gson gson = new GsonBuilder().create();
        EventResponse data= gson.fromJson(response.getBody().toString(), EventResponse.class);
		assertEquals(foodLogiQMessageConfiguration.emptyEvent, data.getMessage());
	}

	/**
	 * Make rest call using restTemplate and return ResponseEntity
	 * 
	 * @return ResponseEntity
	 */
	private ResponseEntity<?> getResponse(String accessToken, Long eventId, HttpMethod method) {
		String url = eventId < 0 ? FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_FETCH_ALL
				: FoodLogiQTestUtil.URL_BASE + port + FoodLogiQTestUtil.URL_DELETE_FETCH + eventId;
		HttpHeaders headers = FoodLogiQTestUtil.createHttpHeaders(accessToken);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return restTemplate.exchange(url, method, entity, String.class);
	}
}
