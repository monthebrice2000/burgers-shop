package com.ecommerce.burgers_web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.burgers_models.helpers.OrderProps;
import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
import com.ecommerce.burgers_repository.repository.BurgerRepository;
import com.ecommerce.burgers_repository.repository.IngredientRepository;
import com.ecommerce.burgers_repository.repository.UserRepository;
import com.ecommerce.burgers_web.controllers.OrderController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    @WithMockUser(username = "habuma", password = "password", roles = "USER" )
    public void testOrderForm() throws Exception {
        mockMvc.perform(get("/orders/current"))
            .andExpect(status().isOk())
            .andExpect(view().name("orderForm"))
            .andExpect(content().string(containsString("Order your taco creations!")));
    }

}
