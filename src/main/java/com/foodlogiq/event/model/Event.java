/**
 *
 */
package com.foodlogiq.event.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Event class representing event data that can be persisted
 * to the database. An entity represents a table stored in a database. Every
 * instance of an entity represents a row in the table.
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Entity
@Table(name = "events")
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5248889777258425123L;

	// unique event id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;

	// Event created date time
	@Column(name = "created_at")
	private Timestamp createdAt;

	// Event created by
	@Column(name = "created_by")
	private String createdBy;

	// identify event deleted or not 
	@Column(name = "is_deleted")
	private boolean isDeleted;

	// event type
	@Column(name = "type")
	private String type;

	// contents of an event
	@OneToMany(targetEntity = Content.class, mappedBy = "event", fetch = FetchType.EAGER)
	private List<Content> contents;

	public Event() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return eventId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.eventId = id;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the contents
	 */
	public List<Content> getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Event [id=" + eventId + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", isDeleted="
				+ isDeleted + ", type=" + type + ", contents=" + contents + "]";
	}

}