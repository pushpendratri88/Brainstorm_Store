package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.CustomerDTO;
import com.brainstorm.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final RestTemplate restTemplate;

    public CustomerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerDTO getCustomer(String customerId) {
        String url = "http://localhost:8080/api/fetchCustomerDetails?input=" + customerId;
        return restTemplate.getForObject(url, CustomerDTO.class);
    }
}
