package com.brainstorm.payment.service.impl;

import com.brainstorm.payment.config.ObjectMapperUtil;
import com.brainstorm.payment.dto.OrderDTO;
import com.brainstorm.payment.dto.OrderEvent;
import com.brainstorm.payment.dto.PaymentEvent;
import com.brainstorm.payment.dto.PaymentStatus;
import com.brainstorm.payment.entity.Payment;
import com.brainstorm.payment.kafka.producer.MassageProducer;
import com.brainstorm.payment.repository.PaymentRepository;
import com.brainstorm.payment.service.IReversePaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class ReversePaymentServiceImpl implements IReversePaymentService {

    private static final Logger logger = LoggerFactory.getLogger(ReversePaymentServiceImpl.class);
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    MassageProducer massageProducer;

    @Override
    @KafkaListener(topics = "reverse-payment",groupId = "reverse_payments_group")
    public void reversePayment(String event) {
        System.out.println("Inside reverse payment for order "+event);

        try {
            PaymentEvent paymentEvent = ObjectMapperUtil.getMapper().readValue(event, PaymentEvent.class);

            OrderDTO order = paymentEvent.getOrder();

            Optional<Payment> payment = this.paymentRepository.findByOrderId(order.getOrderId());
            payment.ifPresent(p ->
            {
                p.setStatus(PaymentStatus.FAILED);
                paymentRepository.save(p);
            });

            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder(paymentEvent.getOrder());
            orderEvent.setType("ORDER_REVERSED");
            logger.info("Consuming OrderEvent to Reverse Order :: Payment --> Order");
            massageProducer.sendMessage("reverse-order", orderEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
