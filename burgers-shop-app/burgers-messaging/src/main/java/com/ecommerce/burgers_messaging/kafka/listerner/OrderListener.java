package com.ecommerce.burgers_messaging.kafka.listerner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ecommerce.burgers_messaging.utils.KitchenUI;
import com.ecommerce.burgers_models.models.BurgerOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile({"kafka-template"})
@Component
@Data
@NoArgsConstructor
@Slf4j
public class OrderListener {

    private KitchenUI ui;

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void receiveOrder(BurgerOrder order) {
        ui.displayOrder(order);
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(BurgerOrder order, ConsumerRecord<String, BurgerOrder> record) {
        log.info("Received from partition {} with timestamp {}",
                record.partition(), record.timestamp());
        ui.displayOrder(order);
    }

}
