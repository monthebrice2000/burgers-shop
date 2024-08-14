package com.ecommerce.burgers_messaging.jms.listerner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ecommerce.burgers_messaging.utils.KitchenUI;
import com.ecommerce.burgers_models.models.BurgerOrder;

@Profile("jms-listener")
@Component
public class JmsOrderListener {

    @Autowired
    private KitchenUI ui;

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(BurgerOrder order) {
        ui.displayOrder(order);
    }

}
