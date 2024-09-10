package com.brainstorm.order.service;

import com.brainstorm.order.dto.OrderDTO;

public interface IOrderService {
     void createOrder(OrderDTO orderDTO);

     OrderDTO fetchOrder(Long orderId);

     void deleteOrder(Long orderId);

}

