package com.ecommerce.burgers_messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @ComponentScan(basePackages = { "com.ecommerce.burgers_messaging.*",
// "com.ecommerce.burgers_repository", "com.ecommerce.burgers_restclient",
// "com.ecommerce.burgers_models", "com.ecommerce.burgers_security" })
@ComponentScan(basePackages = { "com.ecommerce.burgers_messaging.*", "com.ecommerce.burgers_models" })
public class BurgersMessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgersMessagingApplication.class, args);
	}

}
