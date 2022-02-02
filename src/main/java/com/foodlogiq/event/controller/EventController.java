/**
 *
 */
package com.foodlogiq.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodlogiq.event.model.Event;
import com.foodlogiq.event.service.EventService;
import com.foodlogiq.event.util.FoodLogiQConstants;

/**
 * Rest API Implementation for Create an Event, Delete a specific event Fetch a
 * specific event and to list all events
 * 
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */

@RestController
@RequestMapping(FoodLogiQConstants.BASE_URL)
public class EventController {

	@Autowired
	private EventService eventService;

	/**
	 * Handle event creation HTTP request
	 * 
	 * @param event from request
	 * @return {@code ResponseEntity} with event creation details
	 */
	@PostMapping(value = FoodLogiQConstants.CREATE_EVENT_URL, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}

	/**
	 * Handle event deletion HTTP request
	 * 
	 * @param id event id
	 * @return {@code ResponseEntity} with event deletion details
	 */
	@DeleteMapping(FoodLogiQConstants.DELETE_FETCH_EVENT_URL)
	public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
		return eventService.markDeleted(id);
	}

	/**
	 * Fetch the event with given event id
	 * 
	 * @param id event id
	 * @return {@code ResponseEntity} with event detail for given event id
	 */
	@GetMapping(FoodLogiQConstants.DELETE_FETCH_EVENT_URL)
	public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
		return eventService.fetchEventById(id);
	}

	/**
	 * Fetch all the events
	 * 
	 * @return {@code ResponseEntity} with all event details
	 */
	@GetMapping(FoodLogiQConstants.FETCH_ALL_EVENT_URL)
	public ResponseEntity<?> getAllEvents() {
		return eventService.fetchEvents();
	}

}