package com.ecommerce.burgers_restclient;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_restclient.services.RestBurgerTemplateService;
import com.ecommerce.burgers_restclient.services.impl.RestIngredientServiceImpl;

import lombok.extern.slf4j.Slf4j;

// @SpringBootApplication
@SpringBootConfiguration
@ComponentScan
@Configuration
@Slf4j
public class BurgersRestClientApplication {

	// @Autowired
	// private RestBurgerTemplateService restBurgerTemplateService;

	public static void main(String[] args) {
		SpringApplication.run(BurgersRestClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// @Bean
	// public CommandLineRunner fetchIngredients(RestBurgerTemplateService restBurgerTemplateService) {
	// 	return args -> {
	// 		log.info("----------------------- GET -------------------------");
	// 		log.info("GETTING INGREDIENT BY IDE");
	// 		log.info("Ingredient:  " + restBurgerTemplateService.getIngredientById("CHED"));
	// 		log.info("GETTING ALL INGREDIENTS");
	// 		List<Ingredient> ingredients = restBurgerTemplateService.getAllIngredients();
	// 		log.info("All ingredients:");
	// 		for (Ingredient ingredient : ingredients) {
	// 			log.info("   - " + ingredient);
	// 		}
	// 	};
	// }

	// @Bean
	// public CommandLineRunner putAnIngredient(RestBurgerTemplateService restBurgerTemplateService) {
	// 	return args -> {
	// 		log.info("----------------------- PUT -------------------------");
	// 		Ingredient before = restBurgerTemplateService.getIngredientById("LETC");
	// 		log.info("BEFORE:  " + before);
	// 		restBurgerTemplateService.updateIngredient(new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
	// 		Ingredient after = restBurgerTemplateService.getIngredientById("LETC");
	// 		log.info("AFTER:  " + after);
	// 	};
	// }

	// @Bean
	// public CommandLineRunner addAnIngredient(RestBurgerTemplateService restBurgerTemplateService) {
	// 	return args -> {
	// 		log.info("----------------------- POST -------------------------");
	// 		Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
	// 		Ingredient chixAfter = restBurgerTemplateService.createIngredient(chix);
	// 		log.info("AFTER=1:  " + chixAfter);
	// 		Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
	// 		URI uri = restBurgerTemplateService.createIngredientFromURI(beefFajita);
	// 		log.info("AFTER-2:  " + uri);
	// 		Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
	// 		Ingredient shrimpAfter = restBurgerTemplateService.createIngredientByResponse(shrimp);
	// 		log.info("AFTER-3:  " + shrimpAfter);
	// 	};
	// }

	// @Bean
	// public CommandLineRunner deleteAnIngredient(RestBurgerTemplateService restBurgerTemplateService) {
	// 	return args -> {
	// 		log.info("----------------------- DELETE -------------------------");
	// 		Ingredient before = restBurgerTemplateService.getIngredientById("CHIX");
	// 		log.info("BEFORE:  " + before);
	// 		restBurgerTemplateService.deleteIngredient(before);
	// 		Ingredient after = restBurgerTemplateService.getIngredientById("CHIX");
	// 		log.info("AFTER:  " + after);
	// 		before = restBurgerTemplateService.getIngredientById("BFFJ");
	// 		log.info("BEFORE:  " + before);
	// 		restBurgerTemplateService.deleteIngredient(before);
	// 		after = restBurgerTemplateService.getIngredientById("BFFJ");
	// 		log.info("AFTER:  " + after);
	// 		before = restBurgerTemplateService.getIngredientById("SHMP");
	// 		log.info("BEFORE:  " + before);
	// 		restBurgerTemplateService.deleteIngredient(before);
	// 		after = restBurgerTemplateService.getIngredientById("SHMP");
	// 		log.info("AFTER:  " + after);
	// 	};
	// }

}
