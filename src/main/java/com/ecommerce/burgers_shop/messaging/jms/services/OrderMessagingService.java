package com.ecommerce.burgers_shop.messaging.jms.services;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface OrderMessagingService {
    public void sendOrder(BurgerOrder order);

}
