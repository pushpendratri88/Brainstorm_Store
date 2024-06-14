package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.OrderDTO;

import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.mapper.OrderMapper;
import com.brainstorm.order.repository.OrderEntriesRepository;
import com.brainstorm.order.repository.OrderRepository;
import com.brainstorm.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderEntriesRepository orderEntriesRepository;
    @Override
    public void createOrder(OrderDTO orderDTO) {
        EcomOrder order =  OrderMapper.mapToOrder(orderDTO,new EcomOrder());
//        orderEntriesRepository.saveAll(order.getOrderEntryList());
        orderRepository.save(order);
    }
}
