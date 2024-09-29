package com.brainstorm.payment.service;

import com.brainstorm.payment.dto.EcomOrder;
import org.springframework.stereotype.Service;

@Service
public interface IPaymentService {
    void createPayment(EcomOrder order);
}
