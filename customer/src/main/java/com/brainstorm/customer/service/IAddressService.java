package com.brainstorm.customer.service;

import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.entity.Address;

public interface IAddressService {
    AddressDTO fetchAddress(Long input);
    Address createNewAddress(AddressDTO addressDTO);
    void updateAddress(AddressDTO addressDTO);
}
