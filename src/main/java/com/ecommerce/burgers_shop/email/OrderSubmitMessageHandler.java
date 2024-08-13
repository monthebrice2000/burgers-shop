package com.ecommerce.burgers_shop.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {

    @Autowired
    private RestTemplate rest;

    private ApiProperties apiProps;

    @Override
    public Object handle(EmailOrder order, MessageHeaders headers) {
        rest.postForObject(this.apiProps.getUrl(), order, String.class);
        return null;
    }

}
