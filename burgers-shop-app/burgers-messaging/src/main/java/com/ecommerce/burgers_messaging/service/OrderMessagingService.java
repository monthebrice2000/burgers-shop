package com.ecommerce.burgers_messaging.service;

import com.ecommerce.burgers_models.models.BurgerOrder;

public interface OrderMessagingService {
    public void sendOrder(BurgerOrder order);

}
