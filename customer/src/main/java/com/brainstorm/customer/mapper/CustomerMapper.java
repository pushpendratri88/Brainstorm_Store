package com.brainstorm.customer.mapper;

import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.entity.Customer;

import java.time.LocalDateTime;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer ,CustomerDTO customerDTO){
        customerDTO.setId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
