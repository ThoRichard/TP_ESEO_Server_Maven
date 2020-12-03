package com;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LOGGER.log(Level.INFO,"Application demarree");
	}
}
