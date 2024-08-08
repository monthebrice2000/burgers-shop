package com.ecommerce.burgers_shop.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.burgers_shop.repository.BurgerOrderRepository;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface BurgerOrderRepositoryImpl extends BurgerOrderRepository {
    List<BurgerOrder> findByDeliveryZip(String deliveryZip);

    // List<BurgerOrder> findByDeliveryCityOrderByDeliveryTo(String city);

    // @Query("Order o where o.deliveryCity='Seattle'")
    // List<BurgerOrder> readOrdersDeliveredInSeattle();

}
