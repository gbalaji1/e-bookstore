package com.ebook.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EBookAdminApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EBookAdminApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("Main Method Start");
		SpringApplication.run(EBookAdminApplication.class, args);
		LOGGER.debug("Main Method End");
	}
	
	

}
