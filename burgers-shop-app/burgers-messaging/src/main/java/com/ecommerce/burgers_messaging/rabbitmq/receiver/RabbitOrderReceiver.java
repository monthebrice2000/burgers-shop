package com.ecommerce.burgers_messaging.rabbitmq.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_messaging.service.OrderReveiverService;
import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.jms.JMSException;


@Profile("rabbitmq-template")
@Component
public class RabbitOrderReceiver implements OrderReveiverService {

    @Autowired
    private RabbitTemplate rabbit;

    @Override
    public BurgerOrder receiveOrder() throws MessageConversionException, JMSException{
        return rabbit.receiveAndConvert("tacocloud.order.queue",
                new ParameterizedTypeReference<BurgerOrder>() {
                });
    }

}
