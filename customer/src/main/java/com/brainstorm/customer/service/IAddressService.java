package com.brainstorm.customer.service;

import com.brainstorm.customer.dto.AddressDTO;

public interface IAddressService {
    AddressDTO fetchAddress(Long input);
    void createNewAddress(AddressDTO addressDTO);
    void updateAddress(AddressDTO addressDTO);
}
