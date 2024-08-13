package com.ecommerce.burgers_shop.messaging.rabbitmq.services.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.messaging.rabbitmq.services.OrderReceiverService;

// @Profile("rabbitmq-template")
@Component
public class RabbitOrderReceiver implements OrderReceiverService {

    @Autowired
    private RabbitTemplate rabbit;

    @Override
    public BurgerOrder receiveOrder() {
        return rabbit.receiveAndConvert("tacocloud.order.queue",
                new ParameterizedTypeReference<BurgerOrder>() {
                });
    }

}
