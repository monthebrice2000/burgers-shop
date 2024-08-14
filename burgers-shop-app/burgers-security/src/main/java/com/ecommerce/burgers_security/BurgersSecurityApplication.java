package com.ecommerce.burgers_security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// @ComponentScan(basePackages = {"com.ecommerce.burgers_web.config"})
public class BurgersSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgersSecurityApplication.class, args);
	}


}
