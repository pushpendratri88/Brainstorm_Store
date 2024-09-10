package com.brainstrom.consumer.order;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    @KafkaListener(topics = "order", groupId = "order-group-1")
    public void listen(String message) {
        System.out.println("Received   message: " + message);
    }
}
