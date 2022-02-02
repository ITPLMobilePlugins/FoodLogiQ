package com.foodlogiq.event.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.foodlogiq.event.model.FoodLogiQResponse;
import com.foodlogiq.event.model.User;

/**
 * Provides FoodLogiQ utility methods
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public class FoodLogiQUtil {

	// RegEx pattern to find number
	private static Pattern numericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	/**
	 * Get UserModel from SecurityContextHolder
	 * 
	 * @return UserModel
	 */
	public static User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null ? (User) auth.getCredentials() : null;
	}

	/**
	 * Check the given string is numeric or not
	 * 
	 * @param value
	 * @return {@code true} if given string is number else return {@code false}
	 */
	public static boolean isNumeric(String value) {
		if (value == null) {
			return false;
		}
		return numericPattern.matcher(value).matches();
	}

	/**
	 * Formats a {@link Date} into a date-time string.
	 *
	 * @param date the time value to be formatted into a date-time string.
	 * @return the formatted date-time string.
	 */
	public static String convertDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(FoodLogiQConstants.FOODLOGIQ_DATE_FORMAT);
		return dateFormat.format(date);
	}

	/**
	 * Return ResponseEntity with given values
	 * 
	 * @param message exception message
	 * @param status HttpStatus
	 * @param value response value
	 * @return ResponseEntity with given values
	 */
	public static ResponseEntity<?> getResponseEntity(String message, HttpStatus status, Map<String, Object> value) {
		return new ResponseEntity<>(new FoodLogiQResponse(new Date(), message, status.value(), value), status);
	}

}
