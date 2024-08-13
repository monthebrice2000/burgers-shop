package com.ecommerce.burgers_shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_shop.models.Burger;
import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.models.User;

public interface BurgerRepository extends CrudRepository<Burger, Long>{
    List<Burger> findAll(Pageable pageable);

}
