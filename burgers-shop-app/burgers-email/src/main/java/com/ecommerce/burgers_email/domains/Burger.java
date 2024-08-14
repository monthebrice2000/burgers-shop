package com.ecommerce.burgers_email.domains;

import java.util.List;

import lombok.Data;

@Data
public class Burger {

    private final String name;
    private List<String> ingredients;

}
