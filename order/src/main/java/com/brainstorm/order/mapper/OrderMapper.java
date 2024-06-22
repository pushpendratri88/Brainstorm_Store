package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderMapper {
    public static EcomOrder mapToOrder(OrderDTO orderDTO, EcomOrder ecomOrder){
        ecomOrder.setOrderStatus(orderDTO.getOrderStatus());
        ecomOrder.setCreatedAt(LocalDateTime.now());
        ecomOrder.setCreatedBy("Pushpendra");
        // for order Entry
        ecomOrder.setOrderEntryList(new ArrayList<OrderEntry>());
        return ecomOrder;
    }

//    public static EcomOrder mapToOrderEntry(EcomOrder ecomOrderTr, OrderDTO orderDTO){
//        List<OrderEntry> entry = new ArrayList<>();
//        orderDTO.getOrderEntriesDTO().forEach(ent -> {
//            OrderEntry enry = new OrderEntry();
//            enry.setPrice(ent.getPrice());
//            entry.add(enry);
//        });
////        ecomOrderTr.getOrderEntryList().forEach(ent -> {
////
////        });
//        ecomOrderTr.setOrderEntryList(entry);
//        // for order Entry
////        ecomOrderTr.setOrderEntryList(OrderEntryMapper.mapToOrderEntry(orderDTO.getOrderEntriesDTO(), ecomOrderTr.getOrderEntryList()));
//        return ecomOrderTr;
//    }


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
