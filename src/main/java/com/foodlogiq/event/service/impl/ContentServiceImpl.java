/**
 * 
 */
package com.foodlogiq.event.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodlogiq.event.model.Content;
import com.foodlogiq.event.repository.ContentRepository;
import com.foodlogiq.event.service.ContentService;

/**
 * Define the Content CRUD methods
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentRepository contentRepository;

	// Save the content
	@Override
	public Content saveContent(Content content) {
		return contentRepository.save(content);

	}
}
