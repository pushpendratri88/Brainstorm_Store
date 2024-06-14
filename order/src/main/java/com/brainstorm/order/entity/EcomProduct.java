package com.brainstorm.order.entity;

import jakarta.persistence.*;

@Entity
@Table (name="ECOM_PRODUCT")
public class EcomProduct extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Long productCode;
    
    @Column(name = "product_name")
    private String ProductName;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entry_id")
    private OrderEntry orderEntry;

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
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
