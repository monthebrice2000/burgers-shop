package com.ecommerce.burgers_repository.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.burgers_models.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

}
