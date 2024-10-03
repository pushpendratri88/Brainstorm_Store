package com.brainstorm.payment.service.impl;

import com.brainstorm.payment.service.IReversePaymentService;
import org.springframework.kafka.annotation.KafkaListener;

public class ReversePaymentServiceImpl implements IReversePaymentService {
    @Override
//    @KafkaListener(topics = "reverse_Payment" , groupId = "payment_group")
    public void reversePayment(String event) {

    }
}
