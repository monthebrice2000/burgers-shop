package com.ecommerce.burgers_shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.ecommerce.burgers_shop.controllers.OrderController;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOrderForm() throws Exception {
        mockMvc.perform(get("/orders/current"))
            .andExpect(status().isOk())
            .andExpect(view().name("orderForm"))
            .andExpect(content().string(containsString("Order your taco creations!")));
    }

}
