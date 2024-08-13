package com.ecommerce.burgers_shop.messaging.jms.services;

import org.springframework.jms.support.converter.MessageConversionException;

import com.ecommerce.burgers_shop.models.BurgerOrder;

import jakarta.jms.JMSException;

public interface OrderReveiverService {

    public BurgerOrder receiveOrder() throws MessageConversionException, JMSException;

}
