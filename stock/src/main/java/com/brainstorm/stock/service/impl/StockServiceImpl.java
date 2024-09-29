package com.brainstorm.stock.service.impl;

import com.brainstorm.stock.dto.DeliveryEvent;
import com.brainstorm.stock.dto.EcomOrder;
import com.brainstorm.stock.dto.PaymentEvent;
import com.brainstorm.stock.entity.WareHouse;
import com.brainstorm.stock.kafka.producer.MessageProducer;
import com.brainstorm.stock.repository.StockRepository;
import com.brainstorm.stock.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    MessageProducer messageProducer;
    @Override
    public void updateStock(PaymentEvent event) {
        EcomOrder order = event.getOrder();
        DeliveryEvent deliveryEvent = new DeliveryEvent();
        try {
            Iterable<WareHouse> inventories = stockRepository.findByItem(order.getItem());

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
            messageProducer.sendMessage("stock", deliveryEvent);

        } catch (Exception e) {
            PaymentEvent pe = new PaymentEvent();
            pe.setOrder(order);
            pe.setType("PAYMENT_REVERSED");
            messageProducer.sendMessage("reversed-payments", pe);
        }
    }
}
