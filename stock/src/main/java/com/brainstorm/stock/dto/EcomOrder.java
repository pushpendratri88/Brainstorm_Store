package com.brainstorm.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcomOrder {

    private String item;

    private Long id;

    private OrderStatus status;

    private int quantity;

    private String customerId;

    private double amount;

    private String paymentMode;
}
