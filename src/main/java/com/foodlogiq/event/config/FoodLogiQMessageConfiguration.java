package com.foodlogiq.event.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configure the FoodLogiQ messages
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */

@PropertySource(ignoreResourceNotFound = true, value = "classpath:messages.properties")
@Configuration
public class FoodLogiQMessageConfiguration {
	
	@Value("${expecting.http.request}")
	public String expectingHttpRequest;
	
	@Value("${event.created}")
	public String eventCreated;
	
	@Value("${event.deleted}")
	public String eventDeleted;
	
	@Value("${event.not.available}")
	public String eventNotAvailable;
	
	@Value("${success}")
	public String success;
	
	@Value("${empty.event}")
	public String emptyEvent;
	
	@Value("${something.went.wrong}")
	public String somethingWentWrong;
	
	@Value("${invalid.date.format}")
	public String invalidDateFormat;
	
	@Value("${authorization.error}")
	public String unAuthorized;
	
	@Value("${invalid.event.type}")
	public String invalidEventType;
	
	@Value("${empty.content}")
	public String emptyContent;
	
	@Value("${gtin.required}")
	public String invalidGTIN;
	
	@Value("${gtin.lot.required}")
	public String invalidGTINLot;
}