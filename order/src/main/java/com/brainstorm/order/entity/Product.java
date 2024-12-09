package com.brainstorm.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{

    private String id;
    private String name;
    private String category;
    private Double price;
}
