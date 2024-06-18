package com.brainstorm.order.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long orderId;
    private Double totalPrice;
    private Date orderDate;
    private String placedBy;
    private OrderStatus orderStatus;
    private List<OrderEntryDTO> orderEntriesDTO;

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

    public List<OrderEntryDTO> getOrderEntriesDTO() {
        return orderEntriesDTO;
    }

    public void setOrderEntriesDTO(List<OrderEntryDTO> orderEntriesDTO) {
        this.orderEntriesDTO = orderEntriesDTO;
    }
}
