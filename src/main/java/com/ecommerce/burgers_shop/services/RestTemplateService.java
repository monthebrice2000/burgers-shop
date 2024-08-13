package com.ecommerce.burgers_shop.services;

import java.util.List;

import com.ecommerce.burgers_shop.models.Ingredient;

public interface RestTemplateService {

    public Ingredient getIngredientById(String ingredientId);
    public List<Ingredient> getAllIngredients();
    public void updateIngredient(Ingredient ingredient);
    public void deleteIngredient(Ingredient ingredient);
    public Ingredient createIngredient(Ingredient ingredient);
    // public Ingredient addIngredient(Ingredient ingredient);

}
