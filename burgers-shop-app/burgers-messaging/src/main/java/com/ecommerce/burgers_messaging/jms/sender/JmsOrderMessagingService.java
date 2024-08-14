package com.ecommerce.burgers_messaging.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_messaging.service.OrderMessagingService;
import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

@Profile({"jms-template"})
@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    @Autowired
    private JmsTemplate jms;

    @Autowired
    private Destination orderQueue;

    @Override
    public void sendOrder(BurgerOrder order) {
        // this.jms.send(orderQueue, session -> session.createObjectMessage(order));
        jms.convertAndSend("tacocloud.order.queue", order,
                this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

}
