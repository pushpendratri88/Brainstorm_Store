package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.entity.Order;


public class OrderMapper {
    public static Order mapToOrder(OrderDTO orderDTO, Order order){
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setPlacedBy(orderDTO.getPlacedBy());
        return order;
    }


    public static OrderDTO mapToOrderDTO(Order order, OrderDTO orderDTO ){
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPlacedBy(order.getPlacedBy());
        return orderDTO;
    }
}
