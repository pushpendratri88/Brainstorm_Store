package com.brainstorm.customer.service;

import com.brainstorm.customer.dto.CustomerDTO;

public interface ICustomerService {
    public CustomerDTO fetchCustomerDetails (String mobileNumber);

    public CustomerDTO fetchCustomerDetailsWithEmail(String mobileNumber, String email);

    public void createNewCustomer(CustomerDTO customerDTO);

    public void updateCustomer(CustomerDTO customerDTO);

    public void  removeCustomer(String mobileNumber);

}
