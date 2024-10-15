package com.brainstorm.stock.controller;

import com.brainstorm.stock.config.ObjectMapperUtil;
import com.brainstorm.stock.dto.PaymentEvent;
import com.brainstorm.stock.service.IStockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class StockController {

    @Autowired
    IStockService stockService;
    @KafkaListener(topics = "payment", groupId = "stock_group")
 public void createStock(String event) throws JsonProcessingException {
    PaymentEvent paymentEvent = ObjectMapperUtil.getMapper().readValue(event,PaymentEvent.class);
    stockService.updateStock(paymentEvent);


 }
}
