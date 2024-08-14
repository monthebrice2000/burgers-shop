package com.ecommerce.burgers_messaging.service;

import org.springframework.jms.support.converter.MessageConversionException;

import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.jms.JMSException;

public interface OrderReveiverService {

    public BurgerOrder receiveOrder() throws MessageConversionException, JMSException;

}
