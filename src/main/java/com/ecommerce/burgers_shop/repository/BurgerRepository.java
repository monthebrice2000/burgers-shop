package com.ecommerce.burgers_shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_shop.models.Burger;

public interface BurgerRepository extends CrudRepository<Burger, String>{

}
