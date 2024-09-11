package com.brainstorm.order.dto;

import java.time.LocalDateTime;

public class ProductDTO {
    private String code;
    private String name;
    private String category;
    private Double price;
    public LocalDateTime createdAt;

    public ProductDTO(String code, String name, LocalDateTime createdAt) {
        this.code = code;
        this.name = name;
        this.createdAt = createdAt;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
