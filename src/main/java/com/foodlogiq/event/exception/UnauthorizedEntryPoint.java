package com.foodlogiq.event.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * ExceptionTranslationFilter will populate the HttpSession attribute named
 * AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY with
 * the requested target URL before calling this method.
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().println("{ \"error\": \"" + authenticationException.getMessage() + "\" }");

	}
}
