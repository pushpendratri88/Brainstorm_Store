package com.brainstorm.order.dto;

import com.brainstorm.order.entity.EcomOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String type;
    private EcomOrder order;
}
