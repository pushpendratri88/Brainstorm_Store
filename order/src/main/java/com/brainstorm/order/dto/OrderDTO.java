package com.brainstorm.order.dto;

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
    private List<OrderEntryDTO> orderEntriesDTO;
}
