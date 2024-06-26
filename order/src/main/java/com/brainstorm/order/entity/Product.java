package com.brainstorm.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="PRODUCT")
public class Product extends BaseEntity{
    @Id
    @GenericGenerator(name = "product_id", strategy = "com.brainstorm.order.generator.ProductIdGenerator")
    @GeneratedValue(generator = "product_id")
    @Column(name = "product_id")
    private String id;

    @Column(name = "product_name")
    private String productName;

//    @OneToOne(mappedBy = "product")
//    @JoinColumn(name = "entry_fk")
//    private OrderEntry orderEntry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public OrderEntry getOrderEntry() {
//        return orderEntry;
//    }
//
//    public void setOrderEntry(OrderEntry orderEntry) {
//        this.orderEntry = orderEntry;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
