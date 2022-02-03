package com.foodlogiq.event.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.foodlogiq.event.config.FoodLogiQMessageConfiguration;
import com.foodlogiq.event.model.Content;
import com.foodlogiq.event.model.Event;
import com.foodlogiq.event.model.User;
import com.foodlogiq.event.util.FoodLogiQConstants;
import com.foodlogiq.event.util.FoodLogiQUtil;

/**
 * Provides validation utility methods for FoodLogiQ HTTP request
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Configuration
public class FoodLogiQValidationUtil {

	@Autowired
	private FoodLogiQMessageConfiguration foodLogiQMessageConfiguration;

	/**
	 * Validate event requests
	 * 
	 * @param requestName will classify the action - create, delete, fetch, fetch
	 *                    all
	 * @param event
	 * @return Map<String, Object> with validation details
	 */
	public Map<String, Object> isValidRequest(String requestName, Event event, Long id) {
		Map<String, Object> requestObject = new HashMap<>();
		User user = FoodLogiQUtil.getUser();
		if (user == null) {
			requestObject.put(FoodLogiQConstants.KEY_EXCEPTION, FoodLogiQUtil
					.getResponseEntity(foodLogiQMessageConfiguration.unAuthorized, HttpStatus.UNAUTHORIZED, null));
			return requestObject;
		}

		switch (requestName) {
		case FoodLogiQConstants.REQUEST_CREATE:
			createRequestValidation(requestObject, event);
			if (requestObject.containsKey(FoodLogiQConstants.KEY_EXCEPTION)) {
				return requestObject;
			}
			break;
		}

		requestObject.put(FoodLogiQConstants.KEY_USER, user);
		return requestObject;
	}

	/**
	 * Validate the event create request
	 * 
	 * @param requestObject for to store validation message
	 * @param event         coming from request
	 * @return Map<String, Object> with create request validation details
	 */
	private Map<String, Object> createRequestValidation(Map<String, Object> requestObject, Event event) {
		List<String> typeList = Arrays.asList("shipping", "receiving");
		if (event.getType() == null || event.getType().trim().isEmpty() || !typeList.contains(event.getType())) {
			requestObject.put(FoodLogiQConstants.KEY_EXCEPTION, FoodLogiQUtil
					.getResponseEntity(foodLogiQMessageConfiguration.invalidEventType, HttpStatus.BAD_REQUEST, null));
			return requestObject;
		} else if (event.getContents() == null || event.getContents().isEmpty()) {
			requestObject.put(FoodLogiQConstants.KEY_EXCEPTION, FoodLogiQUtil
					.getResponseEntity(foodLogiQMessageConfiguration.emptyContent, HttpStatus.BAD_REQUEST, null));
			return requestObject;
		} else {
			for (Content content : event.getContents()) {
				if (!FoodLogiQUtil.isNumeric(content.getGtin()) || content.getGtin().length() != 14) {
					requestObject.put(FoodLogiQConstants.KEY_EXCEPTION, FoodLogiQUtil.getResponseEntity(
							foodLogiQMessageConfiguration.invalidGTIN, HttpStatus.BAD_REQUEST, null));
					return requestObject;
				} else if (content.getLot() == null || content.getLot().trim().isEmpty()) {
					requestObject.put(FoodLogiQConstants.KEY_EXCEPTION, FoodLogiQUtil.getResponseEntity(
							foodLogiQMessageConfiguration.invalidGTINLot, HttpStatus.BAD_REQUEST, null));
					return requestObject;
				}
			}
		}
		return requestObject;
	}

}
