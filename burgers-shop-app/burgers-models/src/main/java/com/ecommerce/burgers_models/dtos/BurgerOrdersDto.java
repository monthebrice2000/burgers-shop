package com.ecommerce.burgers_models.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BurgerOrdersDto{

    @NotEmpty(message = "Burger Orders cannot be empty.")
    private List<BurgerOrder> orders = new ArrayList<>();

    public void addBurgerOrders(BurgerOrder order) {
        this.orders.add(order);
    }

}
