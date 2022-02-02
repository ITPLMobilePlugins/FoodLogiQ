package com.foodlogiq.event.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Content class representing event content data that can be persisted
 * to the database. An entity represents a table stored in a database. Every
 * instance of an entity represents a row in the table.
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Entity
@Table(name = "content")
public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8722687629437208046L;

	// content unique id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long contentId;

	// Global Trade Item Number
	@Column(name = "gtin")
	private String gtin;

	// event id for table relation
	@ManyToOne
	@JoinColumn(name = "eventId")
	@JsonIgnore
	private Event event;

	// Global Trade Item Number lot
	@Column(name = "lot")
	private String lot;

	// bestByDate with yyyy-MM-dd format
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "best_by_date")
	private Date bestByDate;

	// expirationDate with yyyy-MM-dd format
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "expiration_date")
	private Date expirationDate;

	public Content() {
	}

	public Content(String gtin, String lot, Date bestByDate, Date expirationDate, Event event) {
		this.gtin = gtin;
		this.lot = lot;
		this.bestByDate = bestByDate;
		this.expirationDate = expirationDate;
		this.event = event;
	}
	
	public Content(String gtin, String lot, Event event) {
		this.gtin = gtin;
		this.lot = lot;
		this.event = event;
	}

	/**
	 * @return the gtin
	 */
	public String getGtin() {
		return gtin;
	}

	/**
	 * @param gtin the gtin to set
	 */
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	/**
	 * @return the lot
	 */
	public String getLot() {
		return lot;
	}

	/**
	 * @param lot the lot to set
	 */
	public void setLot(String lot) {
		this.lot = lot;
	}

	/**
	 * @return the bestByDate
	 */
	public Date getBestByDate() {
		return bestByDate;
	}

	/**
	 * @param bestByDate the bestByDate to set
	 */
	public void setBestByDate(Date bestByDate) {
		this.bestByDate = bestByDate;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Content [content_id=" + contentId + ", gtin=" + gtin + ", event=" + event + ", lot=" + lot
				+ ", bestByDate=" + bestByDate + ", expirationDate=" + expirationDate + "]";
	}
}