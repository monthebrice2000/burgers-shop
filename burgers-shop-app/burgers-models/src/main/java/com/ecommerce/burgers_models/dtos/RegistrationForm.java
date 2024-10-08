package com.ecommerce.burgers_models.dtos;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.burgers_models.models.User;

import lombok.Data;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String roles;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone, roles);
    }

}
