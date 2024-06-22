package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.OrderDTO;

import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.entity.OrderEntry;
import com.brainstorm.order.entity.Product;
import com.brainstorm.order.exception.ResourceNotFoundException;
import com.brainstorm.order.mapper.OrderEntryMapper;
import com.brainstorm.order.mapper.OrderMapper;
import com.brainstorm.order.mapper.ProductMapper;
import com.brainstorm.order.repository.OrderEntryRepository;
import com.brainstorm.order.repository.OrderRepository;
import com.brainstorm.order.repository.ProductRepository;
import com.brainstorm.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderEntryRepository orderEntryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void createOrder(OrderDTO orderDTO) {
        EcomOrder ecomOrder =  OrderMapper.mapToOrder(orderDTO,new EcomOrder());
        List<OrderEntry> orderEntryList = OrderEntryMapper.mapToOrderEntry(orderDTO.getOrderEntriesDTO(), ecomOrder.getOrderEntryList() );
        List<OrderEntry> orderEntryListWithProductData = new ArrayList<>();
        orderEntryList.forEach(orderEntry -> {
            orderDTO.getOrderEntriesDTO().forEach(orderEntryDTO -> {
                if(orderEntry.getPrice().equals(orderEntryDTO.getPrice())){
                Product product =  ProductMapper.mapToProduct(orderEntryDTO.getProductDTO());
                Product productTr = productRepository.saveAndFlush(product);
                    orderEntry.setProduct(productTr);
                    orderEntryRepository.saveAndFlush(orderEntry);
                    orderEntryListWithProductData.add(orderEntry);
                }
            });
        });
        ecomOrder.setOrderEntryList(orderEntryListWithProductData);
        orderRepository.saveAndFlush(ecomOrder);
        ecomOrder.getOrderEntryList().forEach(orderEntryTr -> {
            orderEntryTr.setOrder(ecomOrder);
            orderEntryRepository.saveAndFlush(orderEntryTr);
        });
    }

    @Override
    public OrderDTO fetchOrder(Long orderId) {
        EcomOrder ecomOrder =  orderRepository.findByOrderId(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        return OrderMapper.mapToOrderDTO(ecomOrder,new OrderDTO());
    }

    @Override
    public void deleteOrder(Long orderId) {
        EcomOrder ecomOrder =  orderRepository.findByOrderId(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        orderRepository.delete(ecomOrder);
    }
}
