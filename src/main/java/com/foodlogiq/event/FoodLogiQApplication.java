package com.foodlogiq.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provides a convenient base class for auto-configuration, component scan and
 * be able to define extra configuration
 * 
 * @author Thulasiraman Elangovan
 * @version 1.0
 */
@SpringBootApplication
public class FoodLogiQApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodLogiQApplication.class, args);
	}

}
