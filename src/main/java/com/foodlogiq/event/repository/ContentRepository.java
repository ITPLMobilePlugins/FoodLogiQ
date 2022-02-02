package com.foodlogiq.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodlogiq.event.model.Content;

/**
 * ContentRepository to expose Content CRUD
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}