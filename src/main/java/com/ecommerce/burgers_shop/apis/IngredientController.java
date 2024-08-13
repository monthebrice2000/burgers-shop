package com.ecommerce.burgers_shop.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.burgers_shop.controllers.AdminController;
import com.ecommerce.burgers_shop.models.Ingredient;
import com.ecommerce.burgers_shop.models.User;
import com.ecommerce.burgers_shop.repository.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
// @CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {

    @Autowired
    private IngredientRepository repo;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IngredientController.class);

    @GetMapping("/message")
	public String index(@AuthenticationPrincipal Jwt jwt) {
        log.info("Access Tocken --- + " + jwt.getTokenValue());
		return String.format("Hello, %s!", jwt.getSubject());
	}

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Ingredient> allIngredients( @AuthenticationPrincipal User user) {
        log.info("Processing : " + user);
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @PreAuthorize("hasRole('ADMIN')")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // @PreAuthorize("hasRole('USER')") 
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
    }

}
