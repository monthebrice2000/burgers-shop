package com.ecommerce.burgers_shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_shop.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
