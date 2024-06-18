package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.OrderDTO;

import com.brainstorm.order.entity.Order;
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
        Order order =  OrderMapper.mapToOrder(orderDTO,new Order());
        List<OrderEntry> orderEntryList = OrderEntryMapper.mapToOrderEntry(orderDTO.getOrderEntriesDTO());
        orderRepository.save(order);
        orderEntryRepository.saveAll(orderEntryList);
        orderDTO.getOrderEntriesDTO().forEach(orderEntryDTO -> {
            Product product = ProductMapper.mapToProduct(orderEntryDTO.getProductDTO());
            productRepository.save(product);
        });


    }

    @Override
    public OrderDTO fetchOrder(Long orderId) {
        Order order =  orderRepository.findByOrderId(orderId).orElseThrow(() ->new ResourceNotFoundException("Order", "OrderId", String.valueOf(orderId)));
        OrderDTO orderDTO = OrderMapper.mapToOrderDTO(order,new OrderDTO());
        return orderDTO;
    }
}
