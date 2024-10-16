package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.OrderEvent;

import com.brainstorm.order.dto.OrderStatus;
import com.brainstorm.order.entity.EcomOrder;
import com.brainstorm.order.repository.OrderRepository;
import com.brainstorm.order.service.IReverseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ReverseOrderImpl implements IReverseOrder {
    private static final Logger logger = LoggerFactory.getLogger(ReverseOrderImpl.class);
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @KafkaListener(topics = "reverse-order", groupId = "reverse_orders_group")
    public void reverseOrder(String event){
        try {
            OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
            Optional<EcomOrder> opsOrder = orderRepository.findById(orderEvent.getOrder().getOrderId());
            opsOrder.ifPresent(order ->
            {
                order.setStatus(OrderStatus.FAILED);
                orderRepository.save(order);
            });

            logger.info("Consuming OrderEvent to Reverse Order :: Payment --> Order  " );
        } catch (JsonProcessingException e) {
            logger.error("Exception occurred while reverting the order");

        }
    }
}
