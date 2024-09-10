//package com.brainstorm.order.kafka.consumer;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderConsumer {
//    @KafkaListener(topics = "order", groupId = "orderGroup1")
//    public void listen(String message) {
//        System.out.println("Received   message: " + message);
//    }
//}
