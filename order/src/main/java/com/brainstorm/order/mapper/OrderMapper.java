package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.entity.EcomOrder;


public class OrderMapper {
    public static EcomOrder mapToOrder(OrderDTO orderDTO, EcomOrder order){
        order.setOrderId(orderDTO.getOrderId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderstatus(orderDTO.getOrderstatus());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setPlacedBy(orderDTO.getPlacedBy());
        order.setOrderEntryList(orderDTO.getOrderEntries());
        return order;
    }
    public static OrderDTO mapToOrderDTO(EcomOrder order, OrderDTO orderDTO ){
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderstatus(order.getOrderstatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPlacedBy(order.getPlacedBy());
        orderDTO.setOrderEntries(order.getOrderEntryList());
        return orderDTO;
    }
}
