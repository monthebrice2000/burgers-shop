package com.ecommerce.burgers_restclient.services;

import java.net.URI;
import java.util.List;

import com.ecommerce.burgers_models.models.Ingredient;

public interface RestTemplateService {

    public Ingredient getIngredientById(String ingredientId);
    public List<Ingredient> getAllIngredients();
    public void updateIngredient(Ingredient ingredient);
    public void deleteIngredient(Ingredient ingredient);
    public Ingredient createIngredient(Ingredient ingredient);
    public URI createIngredientFromURI(Ingredient ingredient);
    public Ingredient createIngredientByResponse(Ingredient ingredient);
    // public Ingredient addIngredient(Ingredient ingredient);

}
