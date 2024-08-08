package com.ecommerce.burgers_shop.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ecommerce.burgers_shop.models.Ingredient;
import com.ecommerce.burgers_shop.models.Burger;
import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.models.Ingredient.Type;
import com.ecommerce.burgers_shop.repository.BurgerRepository;
import com.ecommerce.burgers_shop.repository.IngredientRepository;

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
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        this.ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processBurger(@Valid Burger burger, Errors errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "design";
        }

        this.burgerRepository.save(burger);
        status.setComplete();
        log.info("Processing burger : " + burger);
        return "redirect:/orders/current";
    }

}
