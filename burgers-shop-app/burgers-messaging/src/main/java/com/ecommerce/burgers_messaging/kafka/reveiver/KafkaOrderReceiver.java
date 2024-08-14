package com.ecommerce.burgers_messaging.kafka.reveiver;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_messaging.service.OrderReveiverService;
import com.ecommerce.burgers_models.models.BurgerOrder;

import jakarta.jms.JMSException;

@Profile({ "kafka-template" })
@Component
public class KafkaOrderReceiver implements OrderReveiverService {

    @Autowired
    private KafkaTemplate restTemplate;

    // private KafkaConsumer<String, BurgerOrder> kafkaConsumer;

    // public KafkaOrderReceiver() {

    //     Properties props = new Properties();
    //     props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    //     props.put(ConsumerConfig.GROUP_ID_CONFIG, "tacocloud-group");
    //     props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    //     props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
    //     props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, BurgerOrder.class.getName());

    //     kafkaConsumer = new KafkaConsumer<>(props);
    //     kafkaConsumer.subscribe(Collections.singletonList("tacocloud.orders.topic"));
    // }

    @Override
    public BurgerOrder receiveOrder() throws MessageConversionException, JMSException {
        return (BurgerOrder) restTemplate.receive("tacocloud.orders.topic", 0, 0,Duration.ofSeconds(10)).value();
        // ConsumerRecords<String, BurgerOrder> records = kafkaConsumer.poll(Duration.ofSeconds(10));
        // for (ConsumerRecord<String, BurgerOrder> record : records) {
        //     return record.value(); // Return the first order received
        // }
        // return null;
    }

}
