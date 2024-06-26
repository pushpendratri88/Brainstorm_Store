package com.brainstorm.order.service;

import com.brainstorm.order.dto.CustomerDTO;

public interface CustomerService {
    public CustomerDTO getCustomer(String customerId);
}
