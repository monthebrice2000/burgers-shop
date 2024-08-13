package com.ecommerce.burgers_shop.messaging.utils;

import org.springframework.stereotype.Component;

import com.ecommerce.burgers_shop.models.BurgerOrder;

import lombok.extern.slf4j.Slf4j;

// @Component
@Slf4j
public class KitchenUI {

    public void displayOrder(BurgerOrder order) {
        log.info("RECEIVED ORDER:  " + order);
    }

}
