package com.brainstorm.customer.service;

import com.brainstorm.customer.dto.CustomerDTO;

public interface ICustomerService {
    CustomerDTO fetchCustomerDetails(String input);

    CustomerDTO fetchCustomerDetailsWithEmail(Long mobileNumber, String email);

    void createNewCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void  removeCustomer(Long mobileNumber);

}
