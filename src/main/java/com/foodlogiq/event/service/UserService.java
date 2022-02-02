package com.foodlogiq.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodlogiq.event.model.User;
import com.foodlogiq.event.repository.UserRepository;

/**
 * Declare the User CRUD methods
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User findUserByAccessToken(final String token) {

		// Fetch the User detail by authorization token
		return userRepository.fetchUserByToken(token);
	}
}
