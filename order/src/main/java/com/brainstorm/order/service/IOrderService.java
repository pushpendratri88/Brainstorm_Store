package com.brainstorm.order.service;

import com.brainstorm.order.dto.OrderDTO;

public interface IOrderService {
    public void createOrder(OrderDTO orderDTO);

    public OrderDTO fetchOrder(String orderId);

    public void deleteOrder(String orderId);

}

