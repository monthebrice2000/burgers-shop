package com.ecommerce.burgers_shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_shop.models.BurgerOrder;

public interface BurgerOrderRepository extends CrudRepository<BurgerOrder, Long> {

}
