package com.foodlogiq.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foodlogiq.event.model.User;

/**
 * UserRepository to expose User CRUD
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	// Fetch user detail by authorization token
	@Query(value = "select * from USER where token = ?1", nativeQuery = true)
	User fetchUserByToken(String token);

}
