/**
 * 
 */
package com.foodlogiq.event.service;

import org.springframework.http.ResponseEntity;

import com.foodlogiq.event.model.Event;

/**
 * Declare the Event CRUD methods
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public interface EventService {

	// Save the event
	ResponseEntity<?> saveEvent(Event event);

	// Fetch event by event id, created user id
	ResponseEntity<?> fetchEventById(Long id);

	// Delete the event by event id, user id
	ResponseEntity<?> markDeleted(Long id);

	// Fetch all the events for given user id
	ResponseEntity<?> fetchEvents();
}