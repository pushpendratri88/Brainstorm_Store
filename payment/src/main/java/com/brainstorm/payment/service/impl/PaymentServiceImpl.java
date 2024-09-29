package com.brainstorm.payment.service.impl;

import com.brainstorm.payment.dto.*;
import com.brainstorm.payment.entity.Payment;
import com.brainstorm.payment.kafka.producer.MassageProducer;
import com.brainstorm.payment.repository.PaymentRepository;
import com.brainstorm.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    MassageProducer massageProducer;

    @Value("${saga.enabled}")
    private String sagaPatternEnabled;

    @Override
    public void createPayment(EcomOrder order) {
        Payment payment = setPaymentData(order);
        try{
            paymentRepository.save(payment);
            if(sagaPatternEnabled.equals("true")){
                PaymentEvent paymentEvent = new PaymentEvent();
                paymentEvent.setOrder(order);
                paymentEvent.setType("payment_Created");
                massageProducer.sendMessage("payment",paymentEvent);
            }
        }
        catch (Exception e){
            payment.setOrderId(order.getId());
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            if(sagaPatternEnabled.equals("true")) {
                OrderEvent orderEvent = new OrderEvent();
                orderEvent.setOrder(order);
                orderEvent.setType("Payment_Failed");
                massageProducer.sendMessage("reverse-orders", orderEvent);
            }
        }

    }

    private Payment setPaymentData(EcomOrder order) {
        Payment payment = new Payment();
        payment.setAmount(order.getAmount());
        payment.setMode(order.getPaymentMode());
        payment.setStatus(PaymentStatus.CREATED);
        payment.setOrderId(order.getId());
        return payment;
    }
}
