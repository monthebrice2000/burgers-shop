package com.ecommerce.burgers_repository.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_models.models.Burger;

public interface BurgerRepository extends CrudRepository<Burger, Long>{
    List<Burger> findAll(Pageable pageable);

}
