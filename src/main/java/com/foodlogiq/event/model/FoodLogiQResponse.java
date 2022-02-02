package com.foodlogiq.event.model;

import java.util.Date;
import java.util.Map;

/**
 * FoodLogiQResponse construct the HTTP responses
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public class FoodLogiQResponse {
	// time stamp of response
	private Date timestamp;
	// status message of response
	private String message;
	//status code of response
	private int statusCode;
	// content of response
	private Map<String, Object> data;

	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public FoodLogiQResponse(Date timestamp, String message, int statusCode, Map<String, Object> data) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}