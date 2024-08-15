package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.OrderEntry;
import com.brainstorm.order.repository.OrderEntryRepository;
import com.brainstorm.order.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderEntryMapper {
    public static List<OrderEntry> mapToOrderEntry(List<OrderEntryDTO> orderEntryListDTO,   ProductRepository productRepository,  OrderEntryRepository orderEntryRepository){
        List<OrderEntry> orderEntryList = new ArrayList<>();
        orderEntryListDTO.forEach(orderEntryDTO -> {
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setCreatedAt(LocalDateTime.now());
            orderEntry.setQuantity(orderEntryDTO.getQuantity());
            orderEntry.setPrice(orderEntryDTO.getProductDTO().getPrice() * orderEntryDTO.getQuantity());
            orderEntry.setProduct(ProductMapper.mapToProduct(orderEntryDTO.getProductDTO(), productRepository));
            OrderEntry orderEntryTr = orderEntryRepository.saveAndFlush(orderEntry);
            orderEntryList.add(orderEntryTr);
        });
        return orderEntryList;
    }


    public static OrderEntryDTO mapToOrderEntryDTO(OrderEntry orderEntry){
        OrderEntryDTO orderEntryDTO = new OrderEntryDTO();
        orderEntryDTO.setId(orderEntry.getId());
        orderEntryDTO.setQuantity(orderEntry.getQuantity());
        orderEntryDTO.setPrice(orderEntry.getProduct().getPrice() * orderEntry.getQuantity());
        orderEntryDTO.setProductDTO(ProductMapper.mapToProductDTO(orderEntry.getProduct()));
        return orderEntryDTO;
    }
}
