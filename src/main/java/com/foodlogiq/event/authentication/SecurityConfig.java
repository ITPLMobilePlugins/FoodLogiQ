package com.foodlogiq.event.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.foodlogiq.event.exception.UnauthorizedEntryPoint;
import com.foodlogiq.event.util.FoodLogiQConstants;

/**
 * Provides a convenient base class for creating a WebSecurityConfigurer
 * instance
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UnauthorizedEntryPoint authenticationEntryPoint;

	@Autowired
	private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(FoodLogiQConstants.PARAM_AUTH_URL);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()

				.antMatchers(FoodLogiQConstants.PARAM_AUTH_URL).permitAll()

				// All other request need to be authenticated
				.anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

		http.addFilterBefore(authenticationTokenProcessingFilter, BasicAuthenticationFilter.class);
	}
}