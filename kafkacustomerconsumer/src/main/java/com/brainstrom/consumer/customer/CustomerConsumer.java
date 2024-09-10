package com.brainstrom.consumer.customer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerConsumer {
    @KafkaListener(topics = "customer", groupId = "consumer-group-1")
    public void listen(String message) {
        System.out.println("Received   message: " + message);
    }
}
