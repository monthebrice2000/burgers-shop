package com.ecommerce.burgers_email.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.burgers_email.config.ApiProperties;
import com.ecommerce.burgers_email.domains.EmailOrder;

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
