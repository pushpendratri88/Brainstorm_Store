package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.entity.EcomOrder;


public class OrderMapper {
    public static EcomOrder mapToOrder(OrderDTO orderDTO, EcomOrder ecomOrder){
        ecomOrder.setOrderStatus(orderDTO.getOrderStatus());
        return ecomOrder;
    }


    public static OrderDTO mapToOrderDTO(EcomOrder ecomOrder, OrderDTO orderDTO ){
        orderDTO.setOrderStatus(ecomOrder.getOrderStatus());
        return orderDTO;
    }
}
