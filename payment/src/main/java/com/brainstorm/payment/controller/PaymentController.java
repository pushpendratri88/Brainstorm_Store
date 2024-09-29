package com.brainstorm.payment.controller;

import com.brainstorm.payment.dto.OrderEvent;
import com.brainstorm.payment.service.IPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {
    @Autowired
    IPaymentService iPaymentService;

    @KafkaListener(topics = "order",groupId ="order_group" )
    public void processPayment(String event) throws JsonProcessingException {
        OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
        iPaymentService.createPayment(orderEvent.getOrder());
    }


}
