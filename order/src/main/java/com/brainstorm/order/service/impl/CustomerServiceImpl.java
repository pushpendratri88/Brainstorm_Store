package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.CustomerDTO;
import com.brainstorm.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    public CustomerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CustomerDTO getCustomer(String customerId) {
        String url = customerServiceUrl + "/fetchCustomerDetails?input=" + customerId;
        logger.info("Requesting customer details from URL: ", url);
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("customerService");
        return circuitBreaker.run(() -> restTemplate.getForObject(url, CustomerDTO.class), throwable -> {
            logger.error("Customer service is down, returning fallback response", throwable);
            return getDefaultCustomer();
        });
    }

//    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackGetCustomer")
//    @Override
//    public CustomerDTO getCustomer(String customerId) {
//        String url = customerServiceUrl + "/fetchCustomerDetails?input=" + customerId;
//        logger.info("Requesting customer details from URL: " + url);
//        return restTemplate.getForObject(url, CustomerDTO.class);
//    }

    private CustomerDTO getDefaultCustomer() {
        logger.info("Calling fallback method for if customer service is down");
        CustomerDTO fallbackCustomer = new CustomerDTO();
        fallbackCustomer.setId("Cust_Default");
        fallbackCustomer.setName("Default Customer");
        fallbackCustomer.setEmail("default@example.com");
        return fallbackCustomer;
    }
}
