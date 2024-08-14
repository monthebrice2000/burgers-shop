package com.ecommerce.burgers_web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ecommerce.burgers_models.models.Burger;
import com.ecommerce.burgers_models.models.Ingredient;
import com.ecommerce.burgers_models.models.Ingredient.Type;
import com.ecommerce.burgers_repository.repository.IngredientRepository;
import com.ecommerce.burgers_restclient.services.impl.RestIngredientServiceImpl;
import com.ecommerce.burgers_repository.repository.BurgerRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/design")
// @SessionAttributes("order")
public class DesignBurgerController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignBurgerController.class);

    @Autowired
    private IngredientRepository ingredientRepo;

    @Autowired
    private BurgerRepository burgerRepository;

    @Autowired
    private RestIngredientServiceImpl restClient;


    // @Autowired
    // public DesignBurgerController(RestIngredientServiceImpl restClient){
    //     this.restClient = restClient;
    // }

    @ModelAttribute(name = "burger")
    public Burger design() {
        return new Burger();
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        this.ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        // ingredients = this.restClient.getAllIngredients(); 

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processBurger(Burger burger, Errors errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "design";
        }

        this.burgerRepository.save(burger);
        // this.restClient.createBurger(burger);
        status.setComplete();
        log.info("Processing burger : " + burger);
        return "redirect:/orders/current";
    }

}
