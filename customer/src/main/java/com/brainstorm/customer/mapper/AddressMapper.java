package com.brainstorm.customer.mapper;

import com.brainstorm.customer.dto.AddressDTO;

import com.brainstorm.customer.entity.Address;

public class AddressMapper {
    public static Address mapToAddress(AddressDTO addDTO){
        Address address = new Address();
        address.setStreet(addDTO.getStreet());
        address.setCity(addDTO.getCity());
        address.setState(addDTO.getState());
        address.setCountry(addDTO.getCountry());
        address.setZipCode(addDTO.getZipCode());
        address.setCreatedAt(addDTO.getCreatedAt());
        return address;
    }

    public static AddressDTO mapToAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setState(address.getState());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setCreatedAt(address.getCreatedAt());
        addressDTO.setStreet(address.getStreet());
        return addressDTO;
    }
}
