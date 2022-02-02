package com.foodlogiq.event.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.foodlogiq.event.config.FoodLogiQMessageConfiguration;
import com.foodlogiq.event.model.User;
import com.foodlogiq.event.util.FoodLogiQConstants;
import com.foodlogiq.event.util.FoodLogiQUtil;

/**
 * Handle exceptions across the whole application in one global handling
 * component. It can be viewed as an interceptor of exceptions thrown by methods
 * annotated with @RequestMapping and similar.
 * 
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@ControllerAdvice
public class FoodLogiQExceptionHandler {

	@Autowired
	private FoodLogiQMessageConfiguration foodLogiQMessageConfiguration;

	// Handling ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
		return FoodLogiQUtil.getResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND, null);
	}

	// Handling global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		return FoodLogiQUtil.getResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	// Handling HttpMessageNotReadableException
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> messageNotReadable(HttpMessageNotReadableException exception, WebRequest request) {
		String message = exception.getLocalizedMessage();

		User user = null;
		if (exception.getCause() instanceof InvalidFormatException
				&& message.contains(FoodLogiQConstants.PARAM_FAILED_PARESE_DATE)) {
			user = FoodLogiQUtil.getUser();
			message = user == null ? foodLogiQMessageConfiguration.unAuthorized
					: foodLogiQMessageConfiguration.invalidDateFormat;
		}

		return FoodLogiQUtil.getResponseEntity(message,
				user == null ? HttpStatus.UNAUTHORIZED : HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	// Handling MethodArgumentNotValidException
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return FoodLogiQUtil.getResponseEntity(errors.get("type"), HttpStatus.BAD_REQUEST, null);
	}

}