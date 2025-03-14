package com.brainstorm.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private OrderStatus orderStatus;
    private String customerId;
    private double amount;
    private String PaymentMode;
    private int quantity;
    private List<OrderEntryDTO> orderEntriesDTO;
}
