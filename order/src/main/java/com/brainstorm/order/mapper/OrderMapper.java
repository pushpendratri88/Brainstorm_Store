package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        ArrayList<OrderEntryDTO>  OrderEntryDTOList = new ArrayList<>();
        orderDTO.setOrderId(ecomOrder.getOrderId());
        orderDTO.setOrderStatus(ecomOrder.getOrderStatus());
        ecomOrder.getOrderEntryList().forEach(orderEntry -> {
            OrderEntryDTO orderEntryDTO =  OrderEntryMapper.mapToOrderEntryDTO(orderEntry,new OrderEntryDTO() );
            OrderEntryDTOList.add(orderEntryDTO);
        });
        return orderDTO;
    }
}
