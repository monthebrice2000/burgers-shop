package com.ecommerce.burgers_shop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_shop.models.Ingredient;
import com.ecommerce.burgers_shop.services.RestTemplateService;

public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate rest;

    @Override
    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
    return rest.exchange("http://localhost:8080/ingredients",
            HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {})
        .getBody();
  }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }

    // @Override
    // public Ingredient addIngredient(Ingredient ingredient) {
    //     String ingredientsUrl = traverson
    //         .follow("ingredients")
    //         .asLink()
    //         .getHref();
    
    //     return rest.postForObject(ingredientsUrl,
    //                               ingredient,
    //                               Ingredient.class);
    //   }

}
