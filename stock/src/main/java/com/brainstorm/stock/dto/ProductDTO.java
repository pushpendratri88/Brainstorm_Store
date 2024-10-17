package com.brainstorm.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String code;
    private String name;
    private String category;
    private Double price;
    public LocalDateTime createdAt;
}
