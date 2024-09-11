package com.brainstorm.order.service.impl;

import com.brainstorm.order.dto.CustomerDTO;
import com.brainstorm.order.service.CustomerService;
import com.brainstorm.order.service.client.CustomerFeignClient;
import com.brainstorm.order.service.client.ProductFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;
    CustomerFeignClient customerFeignClient;


    @Value("${customer.service.url}")
    private String customerServiceUrl;

    @Override
    public CustomerDTO getCustomer(String customerId) {
        String url = customerServiceUrl + "/fetchCustomerDetails?input=" + customerId;
        logger.info("Requesting customer details from URL: ", url);
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("customerService");
        return circuitBreaker.run(() -> customerFeignClient.fetchCustomerDetails(customerId).getBody(),Throwable -> {
            logger.error("Customer service is down, returning fallback response",Throwable);
            return getDefaultCustomer(customerId);
        });
    }

    private CustomerDTO getDefaultCustomer(String customerId) {
          return new CustomerDTO(customerId,"Unknown");
    }
}
