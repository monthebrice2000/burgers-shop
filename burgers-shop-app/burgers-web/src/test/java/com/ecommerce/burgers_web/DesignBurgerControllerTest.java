package com.ecommerce.burgers_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.burgers_web.controllers.DesignBurgerController;
import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
import com.ecommerce.burgers_repository.repository.BurgerRepository;
import com.ecommerce.burgers_repository.repository.IngredientRepository;
import com.ecommerce.burgers_repository.repository.UserRepository;
import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_models.models.Ingredient.Type;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@WebMvcTest(DesignBurgerController.class)
public class DesignBurgerControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private IngredientRepository ingredientRepository;

        @MockBean
        private BurgerRepository burgerRepo;

        @MockBean
        private BurgerOrderRepository orderRepository;

        @MockBean
        private UserRepository userRepository;

        @Test
        @WithMockUser(username = "habuma", password = "password", roles = "USER")
        public void testShowDesignForm() throws Exception {
                mockMvc.perform(get("/design"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("design"))
                                .andExpect(content().string(containsString("Design your taco!")));
        }

        @Test
        @WithMockUser(username = "habuma", password = "password", roles = "USER")
        public void showIngredientTest() throws Exception {

                // Prepare test data
                Ingredient flourTortilla = new Ingredient(
                                "FLTO", "Flour Tortilla", Type.WRAP);
                Ingredient groundBeef = new Ingredient(
                                "GRBF", "Ground Beef", Type.PROTEIN);
                Ingredient tomatoes = new Ingredient(
                                "TMTO", "Diced Tomatoes", Type.VEGGIES);
                List<Ingredient> ingredients = Arrays.asList(flourTortilla, groundBeef, tomatoes);

                // Mock the service to return test data
                Mockito.when(ingredientRepository.findAll()).thenReturn(ingredients);

                // Perform the request and verify the results
                mockMvc.perform(get("/design"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("design"))
                                .andExpect(model().attribute("wrap", hasItem(
                                                allOf(
                                                                hasProperty("id", is("FLTO")),
                                                                hasProperty("name", is("Flour Tortilla")),
                                                                hasProperty("type", is(Type.WRAP))))))
                                .andExpect(model().attribute("protein", hasItem(
                                                allOf(
                                                                hasProperty("id", is("GRBF")),
                                                                hasProperty("name", is("Ground Beef")),
                                                                hasProperty("type", is(Type.PROTEIN))))));

        }

}
