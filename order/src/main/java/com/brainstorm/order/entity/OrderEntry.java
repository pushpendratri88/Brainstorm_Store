package com.brainstorm.order.entity;

import jakarta.persistence.*;

@Entity
public class OrderEntry extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private Long orderEntryId;
    @Column(name = "entry_price")
    private Double orderEntryPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code")
    private EcomProduct ecomProduct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ecomOrder" , referencedColumnName = "order_id" )
    private EcomOrder ecomOrder;


    public Long getOrderEntryId() {
        return orderEntryId;
    }

    public void setOrderEntryId(Long orderEntryId) {
        this.orderEntryId = orderEntryId;
    }

    public Double getOrderEntryPrice() {
        return orderEntryPrice;
    }

    public void setOrderEntryPrice(Double orderEntryPrice) {
        this.orderEntryPrice = orderEntryPrice;
    }

    public EcomProduct getEcomProduct() {
        return ecomProduct;
    }

    public void setEcomProduct(EcomProduct ecomProduct) {
        this.ecomProduct = ecomProduct;
    }

    public EcomOrder getEcomOrder() {
        return ecomOrder;
    }

    public void setEcomOrder(EcomOrder ecomOrder) {
        this.ecomOrder = ecomOrder;
    }
}
