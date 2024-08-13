package com.ecommerce.burgers_shop.messaging.rabbitmq.services;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface OrderReceiverService {

    public BurgerOrder receiveOrder();

}
