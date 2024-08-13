package com.ecommerce.burgers_shop.messaging.kafka.services;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface OrderMessagingService {

    public void sendOrder(BurgerOrder order);

}
