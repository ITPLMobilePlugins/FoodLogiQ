package com.foodlogiq.event.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.foodlogiq.event.config.FoodLogiQMessageConfiguration;
import com.foodlogiq.event.model.User;
import com.foodlogiq.event.service.UserService;
import com.foodlogiq.event.util.FoodLogiQConstants;

/**
 * Simple base implementation of Filter which treats its config parameters
 * (init-param entries within the filter tag in web.xml) as bean properties.
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */

@Component
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	@Autowired
	private UserService userService;

	@Autowired
	private FoodLogiQMessageConfiguration foodLogiQMessageConfiguration;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);

		String accessToken = this.extractAccessTokenFromRequest(httpRequest);
		if (accessToken != null && accessToken.startsWith(FoodLogiQConstants.PARAM_BEARER)) {
			User user = this.userService.findUserByAccessToken(accessToken.split(" ")[1]);
			if (null != user) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
						user);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * Convert {@code ServletRequest} into {@code HttpServletRequest}
	 * 
	 * @param request receive {@code ServletRequest}
	 * @return {@code HttpServletRequest}
	 */
	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException(foodLogiQMessageConfiguration.expectingHttpRequest);
		}

		return (HttpServletRequest) request;
	}

	/**
	 * Parse the HTTP request and get the bearer token
	 * 
	 * @param httpRequest
	 * @return bearer token
	 */
	private String extractAccessTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader(FoodLogiQConstants.PARAM_AUTH_HEADER_AUTHORIZATION);

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter(FoodLogiQConstants.PARAM_AUTH_HEADER_TOKEN);
		}

		return authToken;
	}

}