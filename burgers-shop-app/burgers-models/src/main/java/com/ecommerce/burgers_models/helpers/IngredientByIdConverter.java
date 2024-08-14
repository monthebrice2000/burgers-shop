// package com.ecommerce.burgers_models.helpers;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.convert.converter.Converter;
// import org.springframework.stereotype.Component;

// import com.ecommerce.burgers_models.models.Ingredient;
// import com.ecommerce.burgers_models.repository.IngredientRepository;

// @Component
// public class IngredientByIdConverter implements Converter<String, Ingredient> {

//         @Autowired
//         private IngredientRepository ingredientRepo;

//         @Override
//         public Ingredient convert(String id) {
//                 Optional<Ingredient> optionalIngredient = this.ingredientRepo.findById(id);
//                 return optionalIngredient.isPresent() ? optionalIngredient.get() : null;
//         }

// }
