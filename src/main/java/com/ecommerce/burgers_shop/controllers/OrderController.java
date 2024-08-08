package com.ecommerce.burgers_shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

import com.ecommerce.burgers_shop.models.Burger;
import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.models.User;
import com.ecommerce.burgers_shop.repository.BurgerOrderRepository;
import com.ecommerce.burgers_shop.repository.BurgerRepository;

import jakarta.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
// @SessionAttributes("order")
public class OrderController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private BurgerOrderRepository burgerOrderRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @GetMapping("/current")
    public String orderForm(@ModelAttribute("order") BurgerOrder order) {
        
        this.burgerRepository.findAll().forEach(burger -> order.addBurger(burger));
        log.info("Processing order : " + order);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") BurgerOrder burgerOrder, Errors errors,
            SessionStatus status) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        
        burgerOrder.setUser(user);
        this.burgerOrderRepository.save(burgerOrder);
        status.setComplete();
        log.info("Order processing : " + burgerOrder + " --- " + user);
        return "redirect:/";

    }

}
