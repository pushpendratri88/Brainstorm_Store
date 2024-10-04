package com.brainstorm.payment.dto;


import com.brainstorm.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String type;
    private OrderDTO order;
}
