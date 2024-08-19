package com.brainstorm.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="PRODUCT")
public class Product extends BaseEntity{
    @Id
    @GenericGenerator(name = "product_id", strategy = "com.brainstorm.order.generator.ProductIdGenerator")
    @GeneratedValue(generator = "product_id")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private Double price;

// uncomment if want to enable BiDirectional
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private OrderEntry orderEntry;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
