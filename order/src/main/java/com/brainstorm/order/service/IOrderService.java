package com.brainstorm.order.service;

import com.brainstorm.order.dto.OrderDTO;

public interface IOrderService {
    public void createOrder(OrderDTO orderDTO);
}
