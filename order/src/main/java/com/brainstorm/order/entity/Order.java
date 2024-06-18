package com.brainstorm.order.entity;

import com.brainstorm.order.dto.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ORDER")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    @Column(name = "order_date")
    private Date orderDate;
    
    @Column(name = "placed_by")
    private String placedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntry> OrderEntryList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(String placedBy) {
        this.placedBy = placedBy;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderEntry> getOrderEntryList() {
        return OrderEntryList;
    }

    public void setOrderEntryList(List<OrderEntry> orderEntryList) {
        OrderEntryList = orderEntryList;
    }
}
