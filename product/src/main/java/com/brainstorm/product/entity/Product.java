package com.brainstorm.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "product_id", strategy = "com.brainstorm.product.generator.ProductIdGenerator")
//    @GeneratedValue(generator = "product_id")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private Double price;
}
