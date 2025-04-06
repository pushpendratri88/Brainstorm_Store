package com.brainstorm.customer.mapper;

import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;
import com.brainstorm.customer.model.CustomerForm;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

    public static List<CustomerDTO> mapToCustomerDTOList(List<Customer> customerList , List<CustomerDTO> customerDTOList){
        customerList.forEach(custList -> customerDTOList.add(mapToCustomerDTO(custList,new CustomerDTO())));
        return customerDTOList;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setCreatedBy(customerDTO.getCreatedBy());
        if(!CollectionUtils.isEmpty(customerDTO.getCustomerAddress())){
            Set<Address> addressSet = new HashSet<>();
            customerDTO.getCustomerAddress().forEach(add -> addressSet.add(AddressMapper.mapToAddress(add)));
            customer.setAddresses(addressSet);
        }
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
