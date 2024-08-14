package com.ecommerce.burgers_repository.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.burgers_models.models.BurgerOrder;
import com.ecommerce.burgers_models.models.User;


public interface BurgerOrderRepository extends JpaRepository<BurgerOrder, Long> {
    List<BurgerOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
