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
    public void createNewAddress(AddressDTO addressDTO) {
       Optional<Address> optionalAddress =  addressRepository.findByAddressId(addressDTO.getAddressId());
       if(optionalAddress.isPresent()){
           throw new AddressAlreadyExistsException("Address already registered with given addressId "
                   +addressDTO.getAddressId());
       }
       else {
           Address newAddress = AddressMapper.mapToAddress(addressDTO);
           newAddress.setCustomers(getCustomers(addressDTO));
           addressRepository.save(newAddress);
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
       Address updatedAddress =  AddressMapper.mapToAddress(addressDTO);
       return updatedAddress;
    }

    private  Set<Customer> getCustomers(AddressDTO addDTO) {
        Set<Customer> customers =  new HashSet<>();
        if(addDTO.getCustomers() != null){
            addDTO.getCustomers().forEach(customer -> {
                Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customer.getId());
                if(optionalCustomer.isPresent()){
                    customers.add(optionalCustomer.get());
                }
                else{
                    Customer customerEntity  =  CustomerMapper.mapToCustomer(customer , new Customer());
                    Customer newCust = customerRepository.save(customerEntity);
                    customers.add(newCust);
                }
            });
        }
        return  customers;
    }
}
