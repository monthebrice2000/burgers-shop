package com.ecommerce.burgers_api;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = { "com.ecommerce.burgers_api.*",
		"com.ecommerce.burgers_repository", "com.ecommerce.burgers_restclient",
		"com.ecommerce.burgers_models", "com.ecommerce.burgers_security" })
class BurgersApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
