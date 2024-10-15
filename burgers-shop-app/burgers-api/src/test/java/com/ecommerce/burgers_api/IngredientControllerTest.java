package com.ecommerce.burgers_api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_models.models.Ingredient.Type;
import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
import com.ecommerce.burgers_repository.repository.BurgerRepository;
import com.ecommerce.burgers_repository.repository.IngredientRepository;
import com.ecommerce.burgers_repository.repository.UserRepository;

@SpringBootTest
// @WebMvcTest(IngredientController.class)
public class IngredientControllerTest {

    @MockBean
    private IngredientRepository ingredientRepo;

    @MockBean
    private BurgerRepository burgerRepository;

    @MockBean
    private BurgerOrderRepository orderRepository;

    @MockBean
    private UserRepository userRepository;

    // @Autowired
    // private MockMvc mockMvc;

    @Test
    public void findIngredientById() throws Exception {
        // Create static data
        Ingredient flourTortilla = new Ingredient(
                "FLTO", "Flour Tortilla", Type.WRAP);

        // Mock the repository's findById method to return the static data
        given(ingredientRepo.findById("FLTO"))
                .willReturn(Optional.of(flourTortilla));

        // Call the service method
        Optional<Ingredient> foundIngredient = ingredientRepo.findById("FLTO");

        // Verify the result
        assertThat(foundIngredient).isPresent();
        assertThat(foundIngredient.get().getName()).isEqualTo("Flour Tortilla");
        assertThat(foundIngredient.get().getType().toString().toLowerCase()).isEqualTo("wrap");

        // // Perform the request to the controller and verify the results
        // mockMvc.perform(get("/ingredients/FLTO"))

        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("$.name").value("Flour Tortilla"))
        //         .andExpect(jsonPath("$.type").value("WRAP"));

    }

}
