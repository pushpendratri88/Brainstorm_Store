package com.brainstorm.customer.service.impl;

import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;
import com.brainstorm.customer.exception.CustomerAlreadyExistsException;
import com.brainstorm.customer.exception.ResourceNotFoundException;
import com.brainstorm.customer.kafka.producer.MessageProducer;
import com.brainstorm.customer.mapper.CustomerMapper;
import com.brainstorm.customer.repository.AddressRepository;
import com.brainstorm.customer.repository.CustomerRepository;
import com.brainstorm.customer.service.IAddressService;
import com.brainstorm.customer.service.ICustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements ICustomerService {
    final int MOBILE_NO = 10;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    IAddressService addressService;

    @Autowired
    private S3Service s3Service;

    @Value("${cloud.aws.s3.access.enable}")
    private String cloudS3AccessEnabled;

    @Value("${spring.kafka.producer.enabled}")
    private String kafkaEnabled;

    @Autowired
    MessageProducer producer;


    @Override
    @Cacheable(value = "customers", key = "#input")
    public CustomerDTO fetchCustomerDetails(String input) {
        Customer customer = null;
        if(String.valueOf(input).length() == MOBILE_NO){
            Long mobileNumber = Long.parseLong(input);
             customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", String.valueOf(input)));
        }
        else{
            String  customerId = input;
             customer = customerRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customer_id", String.valueOf(input)));
        }
        return CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
    }

    @Override
    @Cacheable(value = "customers", key = "#mobileNumber + '-' + #email")
    public CustomerDTO fetchCustomerDetailsWithEmail(Long mobileNumber, String email) {
        Customer customer = customerRepository.findByMobileNumberAndEmail(mobileNumber,email).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber & Email ", mobileNumber +"&" +email));
        return CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
    }

    @Override
    @Transactional
    public void createNewCustomer(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDTO.getMobileNumber());
        }else {
            Customer customerEntity = CustomerMapper.mapToCustomer(customerDTO, new Customer());
            if(!customerDTO.getAddressIds().isEmpty()){
                Optional<Set<Address>> uniqueAddress = addressRepository.findByAddressIds(customerDTO.getAddressIds());
                uniqueAddress.ifPresent(customerEntity::setAddresses);
            }
            else if(!customerDTO.getCustomerAddress().isEmpty()){
                Set<Address> newAddressList = new HashSet<>();
                customerDTO.getCustomerAddress().forEach(newAddDTO -> {
                    Address address = addressService.createNewAddress(newAddDTO);
                    newAddressList.add(address);
                });
                customerEntity.setAddresses(newAddressList);
            }
            if(cloudS3AccessEnabled.equals("true")){
                String fileUrl = "";
                if(customerDTO.getFile() != null){
                    MultipartFile file = customerDTO.getFile();
                    String key = file.getOriginalFilename();
                    try {
                        fileUrl =  s3Service.uploadFile(key, file.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                customerEntity.setPhoto(fileUrl);
            }
            customerRepository.save(customerEntity);
            if(kafkaEnabled.equals("true")){
                producer.sendMessage("customer", "Customer has been created");
            }
        }
    }

    @Override
    @CachePut(value = "customers", key = "#customerDTO.mobileNumber")
    @Transactional
    public void updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("Customer not registered", "CustomerEntity", String.valueOf(customerDTO.getMobileNumber()));
        }
        CustomerMapper.mapToCustomer(customerDTO,optionalCustomer.get());
        customerRepository.save(optionalCustomer.get());
    }

    @Override
    @CacheEvict(value = "customers", key = "#mobileNumber")
    @Transactional
    public void removeCustomer(Long mobileNumber) {
        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(mobileNumber);
        optionalCustomer.ifPresent(customer -> customerRepository.delete(customer));
    }
}
