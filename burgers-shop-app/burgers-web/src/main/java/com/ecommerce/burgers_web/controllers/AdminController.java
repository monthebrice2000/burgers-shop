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

import com.ecommerce.burgers_models.dtos.BurgerOrdersDto;
import com.ecommerce.burgers_web.services.OrderAdminService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private OrderAdminService adminService;

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        this.adminService.deleteAllOrders();
        return "redirect:/admin";

    }

    @PostMapping("/deleteOrder")
    public String deleteOrderById(@Valid BurgerOrdersDto ordersDto, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Errors in processing ...");
            return "redirect:/admin";
        }

        log.info("Procession orders : " + ordersDto);
        this.adminService.deleteOrder(ordersDto.getOrders().get(0));
        return "redirect:/admin";

    }

}
