package com.brainstorm.order.entity;

import com.brainstorm.order.dto.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ECOM_ORDER")
public class EcomOrder extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

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
