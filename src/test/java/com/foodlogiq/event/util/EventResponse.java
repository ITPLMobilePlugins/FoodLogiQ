package com.foodlogiq.event.util;

/**
 * EventResponse to parse the event resposne
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public class EventResponse {

	private String timestamp;

	private String message;

	private Integer statusCode;

	private Data data;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
