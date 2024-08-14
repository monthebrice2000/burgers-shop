package com.ecommerce.burgers_web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_models.models.BurgerOrder;
import com.ecommerce.burgers_repository.repository.BurgerOrderRepository;
import com.ecommerce.burgers_web.services.OrderAdminService;

@Service
public class OrderAdminServiceImpl implements OrderAdminService{

    // @Autowired
    private BurgerOrderRepository burgerOrderRepo;
    

    // @Autowired
    // public OrderAdminServiceImpl(BurgerOrderRepository burgerOrderRepo) {
    //     this.burgerOrderRepo = burgerOrderRepo;
    // }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        this.burgerOrderRepo.deleteAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(BurgerOrder order) {
        this.burgerOrderRepo.delete(order);
    }

}
