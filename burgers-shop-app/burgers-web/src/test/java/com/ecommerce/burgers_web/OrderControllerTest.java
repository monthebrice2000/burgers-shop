package com.ecommerce.burgers_web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.burgers_models.helpers.OrderProps;
import com.ecommerce.burgers_models.models.Burger;
import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_models.models.Ingredient.Type;
import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
import com.ecommerce.burgers_repository.repository.BurgerRepository;
import com.ecommerce.burgers_repository.repository.IngredientRepository;
import com.ecommerce.burgers_repository.repository.UserRepository;
import com.ecommerce.burgers_web.controllers.OrderController;

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

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private BurgerOrderRepository burgerOrderRepository;

    @MockBean
    private BurgerRepository burgerRepository;

    @MockBean
    private OrderProps props;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "habuma", password = "password", roles = "USER")
    public void testOrderForm() throws Exception {
        mockMvc.perform(get("/orders/current"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"))
                .andExpect(content().string(containsString("Order your taco creations!")));
    }

    @Test
    @WithMockUser(username = "habuma", password = "password", roles = "USER")
    public void showBurgersTest() throws Exception {

        // Prepare test data
        Ingredient flourTortilla = new Ingredient(
                "FLTO", "Flour Tortilla", Type.WRAP);
        Ingredient groundBeef = new Ingredient(
                "GRBF", "Ground Beef", Type.PROTEIN);
        Ingredient tomatoes = new Ingredient(
                "TMTO", "Diced Tomatoes", Type.VEGGIES);

        Burger burger1 = new Burger();
        burger1.setName("Carnivore");
        burger1.setIngredients(Arrays.asList(flourTortilla, groundBeef));

        List<Burger> burgers = Arrays.asList(burger1);

        // Mock the service to return test data
        Mockito.when(burgerRepository.findAll()).thenReturn(burgers);

        // Perform the request and verify the results
        mockMvc.perform(get("/orders/current"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"))
                .andExpect(model().attribute("order", hasProperty("burgers", hasItem(
                        allOf(
                                hasProperty("name", is("Carnivore")),
                                hasProperty("ingredients", hasItem(
                                        hasProperty("name", is("Flour Tortilla")))))))));

    }

}
