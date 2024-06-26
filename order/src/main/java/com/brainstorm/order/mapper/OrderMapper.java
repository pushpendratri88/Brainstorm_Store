package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderMapper {
    public static EcomOrder mapToOrder(OrderDTO orderDTO){
        EcomOrder ecomOrder = new EcomOrder();
        ecomOrder.setOrderStatus(orderDTO.getOrderStatus());
        ecomOrder.setCreatedAt(LocalDateTime.now());
        ecomOrder.setOrderEntryList(OrderEntryMapper.mapToOrderEntry(orderDTO.getOrderEntriesDTO()));
        return ecomOrder;
    }

    public static OrderDTO mapToOrderDTO(EcomOrder ecomOrder, OrderDTO orderDTO ){
        ArrayList<OrderEntryDTO>  orderEntryDTOList = new ArrayList<>();
        orderDTO.setOrderId(ecomOrder.getOrderId());
        orderDTO.setOrderStatus(ecomOrder.getOrderStatus());
        orderDTO.setCustomerId(ecomOrder.getCustomerId());
        ecomOrder.getOrderEntryList().forEach(orderEntry -> {
            OrderEntryDTO orderEntryDTO =  OrderEntryMapper.mapToOrderEntryDTO(orderEntry);
            orderEntryDTOList.add(orderEntryDTO);
        });
        orderDTO.setOrderEntriesDTO(orderEntryDTOList);
        return orderDTO;
    }
}
