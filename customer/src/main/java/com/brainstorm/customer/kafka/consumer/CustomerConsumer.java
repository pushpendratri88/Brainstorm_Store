package com.brainstorm.customer.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {
    @KafkaListener(topics = "customer", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received   message: " + message);
    }
}
