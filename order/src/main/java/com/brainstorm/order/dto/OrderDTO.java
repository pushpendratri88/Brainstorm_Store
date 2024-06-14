package com.brainstorm.order.dto;

//import com.brainstorm.order.entity.OrderEntry;

import java.util.Date;
import java.util.List;

import com.brainstorm.order.entity.OrderEntry;

public class OrderDTO {
    private Long orderId;
    private Double totalPrice;
    private Date orderDate;
    private String placedBy;
    private String orderstatus;
    private List<OrderEntry> orderEntries;

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

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> OrderEntries) {
        OrderEntries = OrderEntries;
    }
}
