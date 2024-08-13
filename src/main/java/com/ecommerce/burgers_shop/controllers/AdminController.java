package com.ecommerce.burgers_shop.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.ecommerce.burgers_shop.dtos.BurgerOrdersDto;
import com.ecommerce.burgers_shop.helpers.OrderProps;
import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.models.User;
import com.ecommerce.burgers_shop.repository.BurgerOrderRepository;
import com.ecommerce.burgers_shop.repository.impl.BurgerOrderRepositoryImpl;
import com.ecommerce.burgers_shop.services.OrderAdminService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private OrderAdminService adminService;

    @Autowired
    private BurgerOrderRepository burgerOrderRepository;

    @Autowired
    private OrderProps props;

    @GetMapping
    public String home(@AuthenticationPrincipal User user, Model model) {
        PageRequest pageable = PageRequest.of(0, props.getPageSize());
        List<BurgerOrder> orders = new ArrayList<>(); 
        orders = this.burgerOrderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
        model.addAttribute("orders", orders);
        return "orders";

    }

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
