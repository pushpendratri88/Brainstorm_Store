package com.brainstorm.stock.service;

import com.brainstorm.stock.dto.PaymentEvent;

public interface IStockService {
    void updateStock(PaymentEvent event);
}
