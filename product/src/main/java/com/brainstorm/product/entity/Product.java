package com.brainstorm.product.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="PRODUCT")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
