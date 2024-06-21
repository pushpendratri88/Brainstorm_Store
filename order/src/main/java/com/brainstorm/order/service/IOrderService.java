package com.brainstorm.order.service;

import com.brainstorm.order.dto.OrderDTO;

public interface IOrderService {
    public void createOrder(OrderDTO orderDTO);

    public OrderDTO fetchOrder(Long orderId);

    public void deleteOrder(Long orderId);

}

