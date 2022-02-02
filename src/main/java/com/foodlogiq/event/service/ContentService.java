/**
 * 
 */
package com.foodlogiq.event.service;

import com.foodlogiq.event.model.Content;

/**
 * Declare the Content CRUD methods
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
public interface ContentService {
	
	// Save the content for event
	Content saveContent(Content content);
}