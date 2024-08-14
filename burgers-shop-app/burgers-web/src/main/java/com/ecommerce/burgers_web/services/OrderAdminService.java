package com.ecommerce.burgers_web.services;

import com.ecommerce.burgers_models.models.BurgerOrder;

public interface OrderAdminService {

    public void deleteAllOrders();

    public void deleteOrder(BurgerOrder order);

}
