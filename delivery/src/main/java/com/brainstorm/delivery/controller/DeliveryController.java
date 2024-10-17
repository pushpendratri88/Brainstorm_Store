package com.brainstorm.delivery.controller;

import com.brainstorm.delivery.dto.DeliveryEvent;
import com.brainstorm.delivery.dto.EcomOrder;
import com.brainstorm.delivery.entity.Delivery;
import com.brainstorm.delivery.service.IDeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class DeliveryController {
    @Autowired
    IDeliveryService deliveryService;
    @KafkaListener(topics = "new-stock", groupId = "stock-group" )
    void createDelivery(String event) throws JsonProcessingException {
        DeliveryEvent deliveryEvent = new ObjectMapper().readValue(event, DeliveryEvent.class);
        deliveryService.createDelivery(deliveryEvent);
    }


}
