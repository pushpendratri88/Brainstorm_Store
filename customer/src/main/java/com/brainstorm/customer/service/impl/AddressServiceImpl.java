package com.brainstorm.customer.service.impl;

import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;
import com.brainstorm.customer.exception.AddressAlreadyExistsException;
import com.brainstorm.customer.exception.CustomerAlreadyExistsException;
import com.brainstorm.customer.exception.ResourceNotFoundException;
import com.brainstorm.customer.mapper.AddressMapper;
import com.brainstorm.customer.mapper.CustomerMapper;
import com.brainstorm.customer.repository.AddressRepository;
import com.brainstorm.customer.repository.CustomerRepository;
import com.brainstorm.customer.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public AddressDTO fetchAddress(Long addressId) {
        Address address =  addressRepository.findByAddressId(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId +""));
        AddressDTO addressDTO = AddressMapper.mapToAddressDTO(address);
        return addressDTO;
    }

    @Override
    public Address createNewAddress(AddressDTO addressDTO) {
       Optional<Address> optionalAddress =  addressRepository.findByAddressId(addressDTO.getAddressId());
       if(optionalAddress.isPresent()){
           throw new AddressAlreadyExistsException("Address already registered with given addressId "
                   +addressDTO.getAddressId());
       }
       else {
           Address newAddress = AddressMapper.mapToAddress(addressDTO);
          return  addressRepository.save(newAddress);
       }
    }

    @Override
    public void updateAddress(AddressDTO addressDTO) {
        Optional<Address> optionalAddress =  addressRepository.findByAddressId(addressDTO.getAddressId());
        if(optionalAddress.isPresent()){
           Address address =  setUpdatedAddress(addressDTO);
           addressRepository.save(address);
        }
    }

    private Address setUpdatedAddress(AddressDTO addressDTO) {
       Optional<Address> OptionalUpdatedAddress =  addressRepository.findByAddressId(addressDTO.getAddressId());
        Address addressUpdated = OptionalUpdatedAddress.get();
        addressUpdated.setCreatedAt(LocalDateTime.now());
        addressUpdated.setStreet(addressDTO.getStreet());
        addressUpdated.setCountry(addressDTO.getCountry());
        addressUpdated.setCity(addressDTO.getCity());
        addressUpdated.setZipCode(addressDTO.getZipCode());
       return addressRepository.save(addressUpdated);
    }
}
