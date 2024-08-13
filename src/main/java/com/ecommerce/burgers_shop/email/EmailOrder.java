package com.ecommerce.burgers_shop.email;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EmailOrder {
    private final String email;
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        this.burgers.add(burger);
    }
}
