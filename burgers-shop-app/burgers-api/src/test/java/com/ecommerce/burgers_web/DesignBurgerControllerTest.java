// package com.ecommerce.burgers_web;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;

// import com.ecommerce.burgers_web.controllers.DesignBurgerController;
// import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
// import com.ecommerce.burgers_repository.repository.BurgerRepository;
// import com.ecommerce.burgers_repository.repository.IngredientRepository;

// import static org.hamcrest.Matchers.containsString;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


// import org.junit.jupiter.api.Test;

// @WebMvcTest(DesignBurgerController.class)
// public class DesignBurgerControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private IngredientRepository ingredientRepository;

//     @MockBean
//     private BurgerRepository burgerRepo;

//     @Test
//     @WithMockUser(username = "habuma", password = "password", roles = "USER" )
//     public void testShowDesignForm () throws Exception{
//         mockMvc.perform(get("/design"))
//             .andExpect(status().isOk())
//             .andExpect(view().name("design"))
//             .andExpect(content().string(containsString("Design your taco!")));
//     }

// }
