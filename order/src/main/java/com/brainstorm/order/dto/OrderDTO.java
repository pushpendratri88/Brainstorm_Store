package com.brainstorm.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotNull
    @Positive
    private Long orderId;
    @NotNull
    private OrderStatus orderStatus;
    @NotEmpty
    @Pattern(regexp = "\\d{10}")
    private String customerId;
    @NotNull
    @Positive
    private double amount;
    @NotEmpty
    private String PaymentMode;
    @Positive
    private int quantity;
    @NotEmpty
    private List<OrderEntryDTO> orderEntriesDTO;
}
