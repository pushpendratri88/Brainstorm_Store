package com.brainstorm.delivery.service.impl;

import com.brainstorm.delivery.dto.DeliveryEvent;
import com.brainstorm.delivery.dto.EcomOrder;
import com.brainstorm.delivery.entity.Delivery;
import com.brainstorm.delivery.kafka.producer.MassageProducer;
import com.brainstorm.delivery.repository.DeliveryRepository;
import com.brainstorm.delivery.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements IDeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    MassageProducer massageProducer;

    @Override
    public void createDelivery(DeliveryEvent deliveryEvent) {
        try{
            Delivery  delivery = setDelivery(deliveryEvent.getOrder());
            deliveryRepository.save(delivery);
        }catch (Exception e){
            massageProducer.sendMessage("delivery",deliveryEvent);
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
