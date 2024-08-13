package com.ecommerce.burgers_shop.messaging.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.burgers_shop.models.Burger;
import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.messaging.rabbitmq.services.OrderReceiverService;

import lombok.RequiredArgsConstructor;

// @Profile({"jms-template", "rabbitmq-template"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiveController {

    @Autowired
    private final OrderReceiverService orderReceiver;

    @GetMapping("/receive")
    public String receiveOrder(Model model) {
        BurgerOrder order = orderReceiver.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }

}
