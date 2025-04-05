package com.brainstorm.customer.service;

import com.brainstorm.customer.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    CustomerDTO getCustomer(String input);

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerByEmail(Long mobileNumber, String email);

    void createNewCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void  removeCustomer(Long mobileNumber);

}
