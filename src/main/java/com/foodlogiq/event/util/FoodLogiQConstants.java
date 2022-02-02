package com.foodlogiq.event.util;

/**
 * Configure and manage FoodLogiQ contants
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public class FoodLogiQConstants {
	
	// URL
	public static final String BASE_URL = "/food-logiq";
	public static final String CREATE_EVENT_URL = "/event/create";
	public static final String DELETE_FETCH_EVENT_URL = "/event/{id}";
	public static final String FETCH_ALL_EVENT_URL = "/events";
	
	
	// Param values
	public static final String PARAM_AUTH_HEADER_AUTHORIZATION = "Authorization";
	public static final String PARAM_AUTH_HEADER_TOKEN = "token";
	public static final String PARAM_AUTH_URL = "/**";//"/foodLogiq/**";
	public static final String PARAM_BEARER = "Bearer ";
	public static final String PARAM_FAILED_PARESE_DATE = "Failed to parse Date";
	
	// Request type
	public static final String REQUEST_CREATE = "Create";
	public static final String REQUEST_DELETE = "Delete";
	public static final String REQUEST_FETCH = "Fetch";
	public static final String REQUEST_FETCH_ALL = "Fetch_All";
	
	// Request key
	public static final String KEY_USER = "Create";
	public static final String KEY_EXCEPTION = "Delete";
	
	// Date format
	public static final String FOODLOGIQ_DATE_FORMAT = "yyyy-MM-dd";
	
	
}
