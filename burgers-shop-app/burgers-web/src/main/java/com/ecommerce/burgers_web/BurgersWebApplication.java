package com.ecommerce.burgers_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_restclient.services.impl.RestIngredientServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ecommerce.burgers_web.*",
		"com.ecommerce.burgers_repository", "com.ecommerce.burgers_restclient",
		"com.ecommerce.burgers_models", "com.ecommerce.burgers_security" })
public class BurgersWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgersWebApplication.class, args);
	}

}
