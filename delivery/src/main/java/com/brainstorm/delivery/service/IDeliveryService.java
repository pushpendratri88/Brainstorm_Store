package com.brainstorm.delivery.service;

import com.brainstorm.delivery.dto.DeliveryEvent;

public interface IDeliveryService {
    void createDelivery(DeliveryEvent deliveryEvent);
}
