package com.ecommerce.burgers_shop.email;

import java.util.List;

import lombok.Data;

@Data
public class Burger {

    private final String name;
    private List<String> ingredients;

}
