package com.brainstorm.delivery.service.impl;

import com.brainstorm.delivery.dto.DeliveryEvent;
import com.brainstorm.delivery.dto.EcomOrder;
import com.brainstorm.delivery.entity.Delivery;
import com.brainstorm.delivery.kafka.producer.MassageProducer;
import com.brainstorm.delivery.repository.DeliveryRepository;
import com.brainstorm.delivery.service.IDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    MassageProducer massageProducer;

    @Override
    public void createDelivery(DeliveryEvent deliveryEvent) {
        try{
            Delivery  delivery = setDelivery(deliveryEvent.getOrder());
            deliveryRepository.save(delivery);
            logger.info("Saving Delivery to the Database");
        }catch (Exception e){
            logger.info("Sending DeliveryEvent to Kafka Topic, Will be Consumed by Stock Service");
            massageProducer.sendMessage("reverse-stock",deliveryEvent);
        }
    }
    private Delivery setDelivery(EcomOrder order) {
        Delivery delivery = new Delivery();
        delivery.setStatus("delivery_Success");
        delivery.setAddress("Bangalore");
        delivery.setOrderId(order.getId());
        return delivery;
    }
}
