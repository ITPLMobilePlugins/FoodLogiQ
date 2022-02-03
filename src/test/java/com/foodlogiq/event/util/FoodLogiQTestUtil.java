package com.foodlogiq.event.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Provides utility methods and constants for FoodLogiQ testing
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public class FoodLogiQTestUtil {
	public static final String URL_BASE = "http://localhost:";
	public static final String URL_CREATE = "/food-logiq/event/create";
	public static final String URL_DELETE_FETCH = "/food-logiq/event/";
	public static final String URL_FETCH_ALL = "/food-logiq/events";

	public static final String AUTH_TOKEN = "74edf612f393b4eb01fbc2c29dd96671"; 
	public static final String AUTH_TOKEN1 = "d88b4b1e77c70ba780b56032db1c259b";
	public static final String INVALID_AUTH_TOKEN = "74edf612f393b4eb01fbc2c29dd96671c34c2v23c";
	
	public static final String PARAM_ENTITY = "parameter";

	/**
	 * Add authorization token in HttpHeaders
	 * 
	 * @return HttpHeaders
	 */
	public static HttpHeaders createHttpHeaders(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", FoodLogiQConstants.PARAM_BEARER + accessToken);
		return headers;
	}
}
