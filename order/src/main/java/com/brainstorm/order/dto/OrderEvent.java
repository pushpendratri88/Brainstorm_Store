package com.brainstorm.order.dto;

import com.brainstorm.order.entity.EcomOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEvent {
    private String type;
    private OrderDTO order;
}
