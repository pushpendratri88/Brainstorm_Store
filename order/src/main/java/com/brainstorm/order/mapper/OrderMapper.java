package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {
    public static EcomOrder mapToOrder(OrderDTO orderDTO, EcomOrder ecomOrder){
        ecomOrder.setOrderStatus(orderDTO.getOrderStatus());
        ecomOrder.setCreatedAt(LocalDateTime.now());
        ecomOrder.setCreatedBy("Pushpendra");
        // for order Entry
        ecomOrder.setOrderEntryList(OrderEntryMapper.mapToOrderEntry(orderDTO.getOrderEntriesDTO()));
        return ecomOrder;
    }


    public static OrderDTO mapToOrderDTO(EcomOrder ecomOrder, OrderDTO orderDTO ){
        orderDTO.setOrderStatus(ecomOrder.getOrderStatus());
        return orderDTO;
    }
}
