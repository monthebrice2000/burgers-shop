package com.ecommerce.burgers_web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.ecommerce.burgers_models.helpers.OrderProps;
import com.ecommerce.burgers_models.models.BurgerOrder;
import com.ecommerce.burgers_models.models.User;
import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
import com.ecommerce.burgers_repository.repository.BurgerRepository;

import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
// @SessionAttributes("order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private BurgerOrderRepository burgerOrderRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @Autowired
    private OrderProps props;

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
        return "redirect:/orders";

    }

    @GetMapping
    public String home(@AuthenticationPrincipal User user, Model model) {
        PageRequest pageable = PageRequest.of(0, props.getPageSize());
        List<BurgerOrder> orders = new ArrayList<>(); 
        orders = this.burgerOrderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
        model.addAttribute("orders", orders);
        return "orders";

    }

}
