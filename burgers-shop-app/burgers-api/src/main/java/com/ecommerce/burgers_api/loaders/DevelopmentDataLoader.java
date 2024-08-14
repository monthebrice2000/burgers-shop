package com.ecommerce.burgers_api.loaders;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.burgers_models.models.Burger;
import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_models.models.User;
import com.ecommerce.burgers_models.models.Ingredient.Type;
import com.ecommerce.burgers_repository.repository.BurgerRepository;
import com.ecommerce.burgers_repository.repository.IngredientRepository;
import com.ecommerce.burgers_repository.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

// @Profile("!prod")
@Configuration
@Slf4j
public class DevelopmentDataLoader {

        // @Bean
        // public CommandLineRunner dataLoader(IngredientRepository repo,
        // UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of
        // testing with a built-in user
        // return new CommandLineRunner() {
        // @Override
        // public void run(String... args) throws Exception {
        // repo.deleteAll();
        // userRepo.deleteAll();

        // repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        // repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        // repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        // repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        // repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        // repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        // repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        // repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        // repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        // repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        // userRepo.save(new User("habuma", encoder.encode("password"),
        // "Craig Walls", "123 North Street", "Cross Roads", "TX",
        // "76227", "123-123-1234"));
        // }
        // };
        // }

        @Bean
        public CommandLineRunner dataLoader(
                        IngredientRepository repo,
                        UserRepository userRepo,
                        PasswordEncoder encoder,
                        BurgerRepository burgerRepo) {
                log.info("----------------------- GET -------------------------");
                log.info("GETTING INGREDIENT BY IDE");
                return args -> {
                        Ingredient flourTortilla = new Ingredient(
                                        "FLTO", "Flour Tortilla", Type.WRAP);
                        Ingredient cornTortilla = new Ingredient(
                                        "COTO", "Corn Tortilla", Type.WRAP);
                        Ingredient groundBeef = new Ingredient(
                                        "GRBF", "Ground Beef", Type.PROTEIN);
                        Ingredient carnitas = new Ingredient(
                                        "CARN", "Carnitas", Type.PROTEIN);
                        Ingredient tomatoes = new Ingredient(
                                        "TMTO", "Diced Tomatoes", Type.VEGGIES);
                        Ingredient lettuce = new Ingredient(
                                        "LETC", "Lettuce", Type.VEGGIES);
                        Ingredient cheddar = new Ingredient(
                                        "CHED", "Cheddar", Type.CHEESE);
                        Ingredient jack = new Ingredient(
                                        "JACK", "Monterrey Jack", Type.CHEESE);
                        Ingredient salsa = new Ingredient(
                                        "SLSA", "Salsa", Type.SAUCE);
                        Ingredient sourCream = new Ingredient(
                                        "SRCR", "Sour Cream", Type.SAUCE);
                        repo.save(flourTortilla);
                        repo.save(cornTortilla);
                        repo.save(groundBeef);
                        repo.save(carnitas);
                        repo.save(tomatoes);

                        repo.save(lettuce);
                        repo.save(cheddar);
                        repo.save(jack);
                        repo.save(salsa);
                        repo.save(sourCream);

                        Burger burger1 = new Burger();
                        burger1.setName("Carnivore");
                        burger1.setIngredients(Arrays.asList(
                                        flourTortilla, groundBeef, carnitas,
                                        sourCream, salsa, cheddar));
                        burgerRepo.save(burger1);
                        Burger burger2 = new Burger();
                        burger2.setName("Bovine Bounty");
                        burger2.setIngredients(Arrays.asList(
                                        cornTortilla, groundBeef, cheddar,
                                        jack, sourCream));
                        burgerRepo.save(burger2);
                        Burger burger3 = new Burger();
                        burger3.setName("Veg-Out");
                        burger3.setIngredients(Arrays.asList(
                                        flourTortilla, cornTortilla, tomatoes,
                                        lettuce, salsa));
                        burgerRepo.save(burger3);

                };
        }

        @Bean
        public ApplicationRunner customDataLoader(
                        UserRepository repo, PasswordEncoder encoder) {
                log.info("----------------------- GET -------------------------");
                log.info("GETTING INGREDIENT BY IDE");
                return args -> {
                        repo.save(new User("habuma", encoder.encode("password"),
                                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                                        "76227", "123-123-1234", "ROLE_USER"));
                        repo.save(new User("tacochef", encoder.encode("password"),
                                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                                        "76227", "123-123-1234", "ROLE_ADMIN"));
                };
        }

}
