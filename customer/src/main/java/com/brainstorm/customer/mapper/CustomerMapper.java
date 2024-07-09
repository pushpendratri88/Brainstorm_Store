package com.brainstorm.customer.mapper;

import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;

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
//        customer.setAddresses(getAddressForCustomer(customerDTO.getCustomerAddress()));
        return customer;
    }

//    private static Set<Address> getAddressForCustomer(Set<AddressDTO> customerAddress) {
//        Set<Address> addressList = new HashSet<>();
//        customerAddress.forEach(addressDTO -> {
//            Address address = AddressMapper.mapToAddress(addressDTO);
//            addressList.add(address);
//        });
//        return addressList;
//    }

    private static Set<AddressDTO> getAddressDTOForCustomer(Set<Address> addresses) {
        Set<AddressDTO> addressDTOList = new HashSet<>();
        addresses.forEach(address -> {
            AddressDTO addressDTO = AddressMapper.mapToAddressDTO(address);
            addressDTOList.add(addressDTO);
        });
        return addressDTOList;
    }
}
