package com.ecommerce.burgers_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ecommerce.burgers_api.*",
		"com.ecommerce.burgers_repository", "com.ecommerce.burgers_restclient",
		"com.ecommerce.burgers_models", "com.ecommerce.burgers_security"}) //, "com.ecommerce.burgers_messaging" })
public class BurgersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgersApiApplication.class, args);
	}

}
