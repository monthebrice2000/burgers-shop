package com.ecommerce.burgers_shop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.repository.impl.BurgerOrderRepositoryImpl;
import com.ecommerce.burgers_shop.services.OrderAdminService;

@Service
public class OrderAdminServiceImpl implements OrderAdminService{

    @Autowired
    private BurgerOrderRepositoryImpl burgerOrderRepoImpl;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        this.burgerOrderRepoImpl.deleteAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(BurgerOrder order) {
        this.burgerOrderRepoImpl.delete(order);
    }

}
