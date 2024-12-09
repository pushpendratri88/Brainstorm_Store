package com.brainstorm.customer.mapper;

import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;
import com.brainstorm.customer.model.CustomerForm;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer ,CustomerDTO customerDTO){
        customerDTO.setId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        customerDTO.setCustomerAddress(getAddressDTOForCustomer(customer.getAddresses()));
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setCreatedBy(customerDTO.getCreatedBy());

        return customer;
    }

    private static Set<AddressDTO> getAddressDTOForCustomer(Set<Address> addresses) {
        Set<AddressDTO> addressDTOList = new HashSet<>();
        addresses.forEach(address -> {
            AddressDTO addressDTO = AddressMapper.mapToAddressDTO(address);
            addressDTOList.add(addressDTO);
        });
        return addressDTOList;
    }

    public static CustomerDTO customerFormToCustomerDTO(CustomerForm customerForm ){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerForm.getName());
        customerDTO.setEmail(customerForm.getEmail());
        customerDTO.setCreatedAt(LocalDateTime.now());
        customerDTO.setMobileNumber(customerForm.getMobileNumber());
        customerDTO.setFile(customerForm.getFile());
        return customerDTO;
    }
}
