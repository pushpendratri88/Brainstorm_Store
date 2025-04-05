package com.brainstorm.order.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    @NotEmpty
    private String category;
    @NotEmpty
    private Double price;
    @NotEmpty
    public LocalDateTime createdAt;
}
