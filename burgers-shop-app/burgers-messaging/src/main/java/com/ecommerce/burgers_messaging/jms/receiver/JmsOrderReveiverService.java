package com.ecommerce.burgers_messaging.jms.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.ecommerce.burgers_messaging.service.OrderReveiverService;
import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Profile({"jms-template"})
public class JmsOrderReveiverService implements OrderReveiverService {

    @Autowired
    private JmsTemplate jms;

    @Override
    public BurgerOrder receiveOrder() throws MessageConversionException, JMSException {
        return (BurgerOrder) jms.receiveAndConvert("tacocloud.order.queue");

    }

}
