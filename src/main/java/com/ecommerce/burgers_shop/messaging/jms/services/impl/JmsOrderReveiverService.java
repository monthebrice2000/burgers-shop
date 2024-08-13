package com.ecommerce.burgers_shop.messaging.jms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.ecommerce.burgers_shop.messaging.jms.services.OrderReveiverService;
import com.ecommerce.burgers_shop.models.BurgerOrder;

import jakarta.jms.JMSException;
import jakarta.jms.Message;

public class JmsOrderReveiverService implements OrderReveiverService {

    @Autowired
    private JmsTemplate jms;

    @Override
    public BurgerOrder receiveOrder() throws MessageConversionException, JMSException {
        return (BurgerOrder) jms.receiveAndConvert("tacocloud.order.queue");

    }

}
