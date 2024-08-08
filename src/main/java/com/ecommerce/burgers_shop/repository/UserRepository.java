package com.ecommerce.burgers_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.burgers_shop.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

}
