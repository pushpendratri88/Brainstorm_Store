package com.brainstorm.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntryDTO {
    private Long id;
    private Integer quantity;
    private Double price;
    private ProductDTO productDTO;
    private String productId;

}

