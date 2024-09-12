//package com.brainstorm.order.service.impl;
//
//import com.brainstorm.order.dto.CustomerDTO;
//import com.brainstorm.order.service.CustomerService;
//import com.brainstorm.order.service.client.CustomerFeignClient;
//import com.brainstorm.order.service.client.ProductFeignClient;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
//import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//@Service
//public class CustomerServiceImpl implements CustomerService {
//    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
//    @Autowired
//    CustomerFeignClient customerFeignClient;
//
//    @Override
//    public CustomerDTO getCustomer(String customerId) {
//        ResponseEntity<CustomerDTO> customerDTOResponseEntity = customerFeignClient.fetchCustomerDetails(customerId);
//        if(customerDTOResponseEntity != null){
//          return  customerFeignClient.fetchCustomerDetails(customerId).getBody();
//        }
//        return null;
//    }
//}
