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
    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "order_id", strategy = "com.brainstorm.order.generator.OrderIdGenerator")
//    @GeneratedValue(generator = "order_id")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "customer_id")
    private String customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntry> orderEntryList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
