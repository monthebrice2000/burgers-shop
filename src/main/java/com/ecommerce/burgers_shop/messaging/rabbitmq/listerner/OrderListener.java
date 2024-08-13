package com.ecommerce.burgers_shop.messaging.rabbitmq.listerner;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ecommerce.burgers_shop.messaging.utils.KitchenUI;
import com.ecommerce.burgers_shop.models.BurgerOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Profile("rabbitmq-listener")
@Component
@Data
@NoArgsConstructor
public class OrderListener {

    private KitchenUI ui;

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(BurgerOrder order) {
        ui.displayOrder(order);
    }

}
