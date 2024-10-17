package com.brainstorm.payment.service.impl;

import com.brainstorm.payment.dto.*;
import com.brainstorm.payment.entity.Payment;
import com.brainstorm.payment.kafka.producer.MassageProducer;
import com.brainstorm.payment.repository.PaymentRepository;
import com.brainstorm.payment.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    MassageProducer massageProducer;

    @Value("${saga.enabled}")
    private String sagaPatternEnabled;

    @Override
    public void createPayment(OrderDTO order) {
        Payment payment = setPaymentData(order);
        try{
            paymentRepository.save(payment);
            if(sagaPatternEnabled.equals("true")){
                PaymentEvent paymentEvent = new PaymentEvent();
                paymentEvent.setOrder(order);
                paymentEvent.setType("payment_Created");
                logger.info("Sending PaymentEvent to Kafka Topic payment, Will be Consumed by Stock Service");
                massageProducer.sendMessage("payment",paymentEvent);
            }
        }
        catch (Exception e){
            payment.setOrderId(order.getOrderId());
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            if(sagaPatternEnabled.equals("true")) {
                OrderEvent orderEvent = new OrderEvent();
                orderEvent.setOrder(order);
                orderEvent.setType("Payment_Failed");
                logger.info("Sending OrderEvent to Kafka Topic, Will be Consumed by Order Service");
                massageProducer.sendMessage("reverse-order", orderEvent);
            }
        }

    }

    private Payment setPaymentData(OrderDTO order) {
        Payment payment = new Payment();
        payment.setAmount(order.getAmount());
        payment.setMode(order.getPaymentMode());
        payment.setStatus(PaymentStatus.CREATED);
        payment.setOrderId(order.getOrderId());
        return payment;
    }
}
