package com.ecommerce.burgers_shop.messaging.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ecommerce.burgers_shop.messaging.utils.KitchenUI;
import com.ecommerce.burgers_shop.models.BurgerOrder;

@Profile("jms-listener")
@Component
public class OrderListener {

    @Autowired
    private KitchenUI ui;

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(BurgerOrder order) {
        ui.displayOrder(order);
    }

}
