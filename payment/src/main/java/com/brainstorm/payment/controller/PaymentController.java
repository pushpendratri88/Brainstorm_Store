package com.brainstorm.payment.controller;

import com.brainstorm.payment.config.ObjectMapperUtil;
import com.brainstorm.payment.dto.OrderEvent;
import com.brainstorm.payment.service.IPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    IPaymentService iPaymentService;

    @KafkaListener(topics = "order",groupId ="payments_group")
    public void processPayment(String event) throws JsonProcessingException {
        logger.info("message = " + event);
        OrderEvent orderEvent = ObjectMapperUtil.getMapper().readValue(event, OrderEvent.class);
        iPaymentService.createPayment(orderEvent.getOrder());
    }


}
