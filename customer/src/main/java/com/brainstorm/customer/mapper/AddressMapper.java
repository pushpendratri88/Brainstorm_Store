package com.brainstorm.customer.mapper;

import com.brainstorm.customer.dto.AddressDTO;

import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;

import java.util.HashSet;
import java.util.Set;


public class AddressMapper {
    public static Address mapToAddress(AddressDTO addDTO){
        Address address = new Address();
        address.setStreet(addDTO.getStreet());
        address.setCity(addDTO.getCity());
        address.setState(addDTO.getState());
        address.setCountry(addDTO.getCountry());
        address.setZipCode(addDTO.getZipCode());
//        address.setCustomers(getCustomers(addDTO));
        return address;
    }

//    private static Set<Customer> getCustomers(AddressDTO addDTO) {
//        Set<Customer> customers =  new HashSet<>();
//        if(addDTO.getCustomers() != null){
//            addDTO.getCustomers().forEach(customer -> {
//                // check if customer exist
//
//                Customer customerEntity  =  CustomerMapper.mapToCustomer(customer , new Customer());
//                customers.add(customerEntity);
//            });
//        }
//        return  customers;
//    }

    public static AddressDTO mapToAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setState(address.getState());
        addressDTO.setZipCode(address.getZipCode());
        return addressDTO;
    }
}
