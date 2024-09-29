package com.brainstorm.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentData {
    private Long id;

    private String mode;

    private Long orderId;

    private double amount;

    private OrderStatus status;
}
