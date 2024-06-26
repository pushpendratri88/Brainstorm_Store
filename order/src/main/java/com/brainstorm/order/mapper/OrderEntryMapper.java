package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.OrderEntry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderEntryMapper {
    public static List<OrderEntry> mapToOrderEntry(List<OrderEntryDTO> orderEntryListDTO){
        List<OrderEntry> orderEntryList = new ArrayList<>();
        orderEntryListDTO.forEach(orderEntryDTO -> {
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setPrice(orderEntryDTO.getPrice());
            orderEntry.setCreatedAt(LocalDateTime.now());
            orderEntry.setProduct(ProductMapper.mapToProduct(orderEntryDTO.getProductDTO()));
            orderEntryList.add(orderEntry);
        });
        return orderEntryList;
    }


    public static OrderEntryDTO mapToOrderEntryDTO(OrderEntry orderEntry){
        OrderEntryDTO orderEntryDTO = new OrderEntryDTO();
        orderEntryDTO.setId(orderEntry.getId());
        orderEntryDTO.setProductDTO(ProductMapper.mapToProductDTO(orderEntry.getProduct()));
        orderEntryDTO.setPrice(orderEntry.getPrice());
        return orderEntryDTO;
    }
}
