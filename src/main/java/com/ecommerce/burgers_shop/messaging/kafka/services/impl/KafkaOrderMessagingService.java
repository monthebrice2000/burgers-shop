package com.ecommerce.burgers_shop.messaging.kafka.services.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_shop.models.BurgerOrder;
import com.ecommerce.burgers_shop.messaging.rabbitmq.services.OrderMessagingService;

@Profile({"kafka-template"})
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    @Autowired
    private KafkaTemplate<String, BurgerOrder> kafkaTemplate;

    @Override
    public void sendOrder(BurgerOrder order) {
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }

}
