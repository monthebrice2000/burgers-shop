package com.ecommerce.burgers_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import com.ecommerce.burgers_shop.controllers.DesignBurgerController;

@WebMvcTest(DesignBurgerController.class)
public class DesignBurgerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowDesignForm () throws Exception{
        mockMvc.perform(get("/design"))
            .andExpect(status().isOk())
            .andExpect(view().name("design"))
            .andExpect(content().string(containsString("Design your taco!")));
    }

}
