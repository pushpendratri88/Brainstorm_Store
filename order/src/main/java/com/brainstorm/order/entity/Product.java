package com.brainstorm.order.entity;

import jakarta.persistence.*;

@Entity
@Table (name="PRODUCT")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Long productCode;

    @Column(name = "product_name")
    private String productName;

    @OneToOne(mappedBy = "product")
    private OrderEntry orderEntry;

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public OrderEntry getOrderEntry() {
        return orderEntry;
    }

    public void setOrderEntry(OrderEntry orderEntry) {
        this.orderEntry = orderEntry;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
