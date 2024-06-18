package com.brainstorm.order.entity;

import jakarta.persistence.*;

@Entity
@Table (name="ORDER_ENTRY")
public class OrderEntry extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private Long Id;
    
    @Column(name = "entry_price")
    private Double Price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code")
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order", referencedColumnName = "order_id")
    private Order order;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
