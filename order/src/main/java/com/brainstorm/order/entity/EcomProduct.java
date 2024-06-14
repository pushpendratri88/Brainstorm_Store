package com.brainstorm.order.entity;

import jakarta.persistence.*;

@Entity
public class EcomProduct extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "product_name")
    private String ProductName;
    @Column(name = "order_entry")
    private OrderEntry orderEntry;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public OrderEntry getOrderEntry() {
        return orderEntry;
    }

    public void setOrderEntry(OrderEntry orderEntry) {
        this.orderEntry = orderEntry;
    }
}
