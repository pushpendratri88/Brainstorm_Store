package com.brainstorm.stock.service.impl;

import com.brainstorm.stock.dto.DeliveryEvent;
import com.brainstorm.stock.dto.PaymentEvent;
import com.brainstorm.stock.entity.WareHouse;
import com.brainstorm.stock.kafka.producer.MessageProducer;
import com.brainstorm.stock.repository.StockRepository;
import com.brainstorm.stock.service.IReverseStockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReverseStockService implements IReverseStockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    MessageProducer messageProducer;
    @Override
    @KafkaListener(topics = "delivery",groupId = "delivery_group")
    public void reverseStock(String event) {
        try{
            DeliveryEvent deliveryEvent = new ObjectMapper().readValue(event, DeliveryEvent.class);

            Iterable<WareHouse> inv = this.stockRepository.findByItem(deliveryEvent.getOrder().getItem());

            inv.forEach(i -> {
                i.setQuantity(i.getQuantity() + deliveryEvent.getOrder().getQuantity());
                stockRepository.save(i);
            });

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrder(deliveryEvent.getOrder());
            paymentEvent.setType("PAYMENT_REVERSED");
            messageProducer.sendMessage("reversed-payments", paymentEvent);
        }catch (JsonProcessingException e){

        }
    }
}
