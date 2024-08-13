package com.ecommerce.burgers_shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.models.User;

public interface BurgerOrderRepository extends CrudRepository<BurgerOrder, Long> {
    List<BurgerOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
