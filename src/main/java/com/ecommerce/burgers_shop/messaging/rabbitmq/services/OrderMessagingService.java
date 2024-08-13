package com.ecommerce.burgers_shop.messaging.rabbitmq.services;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface OrderMessagingService {

    public void sendOrder(BurgerOrder order);

}
