package com.foodlogiq.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foodlogiq.event.model.Event;

/**
 * EventRepository to expose Events CRUD
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

	// Delete the event by event id
	@Modifying
	@Query(value = "update Events e set e.is_deleted = ?1 where e.event_id = ?2 and e.created_by = ?3", nativeQuery = true)
	int markDeleted(boolean isDeleted, Long eventId, String createdBy);

	// Fetch the event by event id
	@Query(value = "select e.* from Events e where e.event_id = ?1 and e.created_by = ?2 and e.is_deleted = false", nativeQuery = true)
	Event fetchEventById(Long eventId, String createdBy);

	// Fetch all the events
	@Query(value = "select e.* from Events e where e.created_by = ?1 and e.is_deleted = false order by created_at desc", nativeQuery = true)
	List<Event> fetchEvents(String createdBy);
}
