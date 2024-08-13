package com.ecommerce.burgers_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_shop.integration_flow.FileWriterGateway;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer()
public class BurgersShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgersShopApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner writeData(FileWriterGateway gateway, Environment env) {
		return args -> {
			String[] activeProfiles = env.getActiveProfiles();
			if (activeProfiles.length > 0) {
				String profile = activeProfiles[0];
				gateway.writeToFile("simple.txt", "Hello, Spring Integration! (" + profile + ")");
			} else {
				System.out.println(
						"No active profile set. Should set active profile to one of xmlconfig, javaconfig, or javadsl.");
			}
		};
	}

}
