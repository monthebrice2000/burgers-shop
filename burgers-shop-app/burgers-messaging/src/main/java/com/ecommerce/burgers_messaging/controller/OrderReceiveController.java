package com.ecommerce.burgers_messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.burgers_messaging.service.OrderReveiverService;
import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.jms.JMSException;

import lombok.RequiredArgsConstructor;

// @Profile({"jms-template", "rabbitmq-template"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiveController {

    @Autowired
    private OrderReveiverService orderReceiver;

    @GetMapping("/receive")
    public String receiveOrder(Model model) throws MessageConversionException, JMSException {
        BurgerOrder order = this.orderReceiver.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }

}
