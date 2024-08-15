package com.brainstorm.order.mapper;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.OrderEntryDTO;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;
import com.brainstorm.order.repository.OrderEntryRepository;
import com.brainstorm.order.repository.OrderRepository;
import com.brainstorm.order.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderMapper {
    public static EcomOrder mapToOrder(OrderDTO orderDTO, ProductRepository productRepository,  OrderEntryRepository orderEntryRepository){
        EcomOrder ecomOrder = new EcomOrder();
        ecomOrder.setStatus(orderDTO.getOrderStatus());
        ecomOrder.setCreatedAt(LocalDateTime.now());
        ecomOrder.setOrderEntryList(OrderEntryMapper.mapToOrderEntry(orderDTO.getOrderEntriesDTO(),  productRepository,  orderEntryRepository));
        return ecomOrder;
    }

    public static OrderDTO mapToOrderDTO(EcomOrder ecomOrder, OrderDTO orderDTO ){
        ArrayList<OrderEntryDTO>  orderEntryDTOList = new ArrayList<>();
        orderDTO.setOrderId(ecomOrder.getId());
        orderDTO.setOrderStatus(ecomOrder.getStatus());
        orderDTO.setCustomerId(ecomOrder.getCustomerId());
        ecomOrder.getOrderEntryList().forEach(orderEntry -> {
            OrderEntryDTO orderEntryDTO =  OrderEntryMapper.mapToOrderEntryDTO(orderEntry);
            orderEntryDTOList.add(orderEntryDTO);
        });
        orderDTO.setOrderEntriesDTO(orderEntryDTOList);
        return orderDTO;
    }
}
