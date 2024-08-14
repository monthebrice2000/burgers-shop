package com.ecommerce.burgers_admin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer()
public class BurgersAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgersAdminApplication.class, args);
	}

}
