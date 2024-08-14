package com.ecommerce.burgers_repository.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_models.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
