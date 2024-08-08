package com.ecommerce.burgers_shop.services;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface OrderAdminService {

    public void deleteAllOrders();

    public void deleteOrder(BurgerOrder order);

}
