package com.brainstorm.stock.service.impl;

import com.brainstorm.stock.dto.DeliveryEvent;
import com.brainstorm.stock.dto.OrderDTO;
import com.brainstorm.stock.dto.PaymentEvent;
import com.brainstorm.stock.entity.WareHouse;
import com.brainstorm.stock.kafka.producer.MessageProducer;
import com.brainstorm.stock.repository.StockRepository;
import com.brainstorm.stock.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl implements IStockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    StockRepository stockRepository;

    @Autowired
    MessageProducer messageProducer;
    @Override
    public void updateStock(PaymentEvent event) {
        OrderDTO order = event.getOrder();
        DeliveryEvent deliveryEvent = new DeliveryEvent();
        try {
            Iterable<WareHouse> inventories = stockRepository.findByItem(order.getOrderId());

            boolean exists = inventories.iterator().hasNext();

            if (!exists) {
                System.out.println("Stock not exist so reverting the order");
                throw new Exception("Stock not available");
            }

            inventories.forEach(wareHouse -> {
                wareHouse.setQuantity(wareHouse.getQuantity() - order.getQuantity());

                stockRepository.save(wareHouse);
            });

            event.setType("STOCK_UPDATED");
            event.setOrder(order);
            logger.info("Sending DeliveryEvent to Kafka Topic, Will be Consumed by Delivery Service");
            messageProducer.sendMessage("new-stock", deliveryEvent);

        } catch (Exception e) {
            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrder(order);
            paymentEvent.setType("PAYMENT_REVERSED");
            logger.info("Sending paymentEvent to Kafka Topic, Will be Consumed by Payment Service");
            messageProducer.sendMessage("reverse-payment", paymentEvent);
        }
    }
}
