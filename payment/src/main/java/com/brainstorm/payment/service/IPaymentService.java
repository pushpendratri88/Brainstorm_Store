package com.brainstorm.payment.service;

import com.brainstorm.payment.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public interface IPaymentService {
    void createPayment(OrderDTO order);
}
