package com.brainstorm.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name="ORDER_ENTRY")
public class OrderEntry extends BaseEntity{
    @Id
    @GenericGenerator(name = "entry_id", strategy = "com.brainstorm.order.generator.OrderEntryIdGenerator")
    @GeneratedValue(generator = "entry_id")
    @Column(name = "entry_id")
    private String Id;

    @Column(name = "entry_price")
    private Double Price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_fk")
    private EcomOrder order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_fk", referencedColumnName = "product_id")
    private Product product;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public EcomOrder getOrder() {
        return order;
    }

    public void setOrder(EcomOrder order) {
        this.order = order;
    }
}
