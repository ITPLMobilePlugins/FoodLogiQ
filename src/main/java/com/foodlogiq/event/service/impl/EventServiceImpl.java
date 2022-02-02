/**
 * 
 */
package com.foodlogiq.event.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodlogiq.event.config.FoodLogiQMessageConfiguration;
import com.foodlogiq.event.model.Content;
import com.foodlogiq.event.model.Event;
import com.foodlogiq.event.model.User;
import com.foodlogiq.event.repository.EventRepository;
import com.foodlogiq.event.service.ContentService;
import com.foodlogiq.event.service.EventService;
import com.foodlogiq.event.util.FoodLogiQConstants;
import com.foodlogiq.event.util.FoodLogiQUtil;
import com.foodlogiq.event.validation.FoodLogiQValidationUtil;

/**
 * Define the Event CRUD methods
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ContentService contentService;

	@Autowired
	private FoodLogiQValidationUtil foodLogiQValidationUtil;

	@Autowired
	private FoodLogiQMessageConfiguration foodLogiQMessageConfiguration;

	/**
	 * Validate the event details if pass store in the persistence storage
	 * 
	 * @param event detail from request
	 * @return ResponseEntity with event creation details
	 */
	@Override
	public ResponseEntity<?> saveEvent(Event event) {
		Map<String, Object> requestObject = foodLogiQValidationUtil.isValidRequest(FoodLogiQConstants.REQUEST_CREATE,
				event, null);
		if (requestObject.containsKey(FoodLogiQConstants.KEY_EXCEPTION)) {
			return (ResponseEntity<?>) requestObject.get(FoodLogiQConstants.KEY_EXCEPTION);
		}

		User user = (User) requestObject.get(FoodLogiQConstants.KEY_USER);
		try {
			event.setCreatedAt(new Timestamp(new Date().getTime()));
			event.setDeleted(false);
			event.setCreatedBy(user.getUserid());
			Event _event = eventRepository.save(event);
			for (Content content : event.getContents()) {
				content.setEvent(_event);
				contentService.saveContent(content);
			}

			return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.eventCreated, HttpStatus.OK, null);
		} catch (Exception e) {
			return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.somethingWentWrong,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/**
	 * Fetch the event from persistence storage if provided event id, user id is
	 * valid
	 * 
	 * @param id event id
	 * @return ResponseEntity with event deletion details
	 */
	@Override
	public ResponseEntity<?> fetchEventById(Long id) {
		Map<String, Object> requestObject = foodLogiQValidationUtil.isValidRequest(FoodLogiQConstants.REQUEST_FETCH,
				null, id);
		if (requestObject.containsKey(FoodLogiQConstants.KEY_EXCEPTION)) {
			return (ResponseEntity<?>) requestObject.get(FoodLogiQConstants.KEY_EXCEPTION);
		}

		User user = (User) requestObject.get(FoodLogiQConstants.KEY_USER);

		try {
			Event eventData = eventRepository.fetchEventById(id, user.getUserid());

			if (eventData != null) {
				Map<String, Object> eventMap = new HashMap<>();
				eventMap.put("event", eventData);
				return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.success, HttpStatus.OK, eventMap);
			} else {
				return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.eventNotAvailable,
						HttpStatus.BAD_REQUEST, null);
			}
		} catch (Exception e) {
			return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.somethingWentWrong,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/**
	 * Fetch all the events for login user
	 * 
	 * @return ResponseEntity with all the events
	 */
	@Override
	public ResponseEntity<?> fetchEvents() {
		Map<String, Object> requestObject = foodLogiQValidationUtil.isValidRequest(FoodLogiQConstants.REQUEST_FETCH,
				null, null);
		if (requestObject.containsKey(FoodLogiQConstants.KEY_EXCEPTION)) {
			return (ResponseEntity<?>) requestObject.get(FoodLogiQConstants.KEY_EXCEPTION);
		}

		User user = (User) requestObject.get(FoodLogiQConstants.KEY_USER);
		try {
			Map<String, Object> event = new HashMap<>();
			List<Event> events = eventRepository.fetchEvents(user.getUserid());

			if (events.isEmpty()) {
				return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.emptyEvent, HttpStatus.OK, event);
			}

			event.put("event", events);
			return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.success, HttpStatus.OK, event);
		} catch (Exception e) {
			return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.somethingWentWrong,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/**
	 * Delete the event from persistence storage if provided event id, user id is
	 * valid
	 * 
	 * @param id event id
	 * @return ResponseEntity with event deletion details
	 */
	@Override
	public ResponseEntity<?> markDeleted(Long id) {
		Map<String, Object> requestObject = foodLogiQValidationUtil.isValidRequest(FoodLogiQConstants.REQUEST_DELETE,
				null, id);
		if (requestObject.containsKey(FoodLogiQConstants.KEY_EXCEPTION)) {
			return (ResponseEntity<?>) requestObject.get(FoodLogiQConstants.KEY_EXCEPTION);
		}

		User user = (User) requestObject.get(FoodLogiQConstants.KEY_USER);
		try {

			int count = eventRepository.markDeleted(true, id, user.getUserid());
			if (count == 1) {
				return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.eventDeleted, HttpStatus.OK, null);
			} else {
				return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.eventNotAvailable,
						HttpStatus.BAD_REQUEST, null);
			}
		} catch (Exception e) {
			return FoodLogiQUtil.getResponseEntity(foodLogiQMessageConfiguration.somethingWentWrong,
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
}