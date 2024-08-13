package com.ecommerce.burgers_shop.messaging.rabbitmq.services.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.messaging.rabbitmq.services.OrderMessagingService;

// @Profile({"rabbitmq-template"})
@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    @Autowired
    private RabbitTemplate rabbit;

    @Override
    public void sendOrder(BurgerOrder order) {
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties props = new MessageProperties();
        props.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = converter.toMessage(order, props);
        rabbit.send("tacocloud.order", message);
    }

}
