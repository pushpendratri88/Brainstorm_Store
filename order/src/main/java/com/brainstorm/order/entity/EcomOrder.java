package com.brainstorm.order.entity;

import com.brainstorm.order.dto.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ECOM_ORDER")
public class EcomOrder extends BaseEntity{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "order_id", strategy = "com.brainstorm.order.generator.OrderIdGenerator")
    @GeneratedValue(generator = "order_id")
    @Column(name = "order_id")
    private String orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "customer_id")
    private String customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntry> orderEntryList = new ArrayList<>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderEntry> getOrderEntryList() {
        return orderEntryList;
    }

    public void setOrderEntryList(List<OrderEntry> orderEntryList) {
        this.orderEntryList.clear();
        if (orderEntryList != null) {
            this.orderEntryList.addAll(orderEntryList);
        }
    }

    public void addOrderEntry(OrderEntry orderEntry) {
        orderEntryList.add(orderEntry);
        orderEntry.setOrder(this);
    }

    public void removeOrderEntry(OrderEntry orderEntry) {
        orderEntryList.remove(orderEntry);
        orderEntry.setOrder(null);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
