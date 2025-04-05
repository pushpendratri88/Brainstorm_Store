package com.brainstorm.order.dto;

import com.brainstorm.order.entity.EcomOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEvent {
    @NotBlank
    private String type;
    @NotNull
    private OrderDTO order;
}
