package com.brainstorm.customer.service.impl;

import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.entity.Address;
import com.brainstorm.customer.entity.Customer;
import com.brainstorm.customer.exception.CustomerAlreadyExistsException;
import com.brainstorm.customer.exception.ResourceNotFoundException;
import com.brainstorm.customer.kafka.producer.MessageProducer;
import com.brainstorm.customer.mapper.AddressMapper;
import com.brainstorm.customer.mapper.CustomerMapper;
import com.brainstorm.customer.repository.AddressRepository;
import com.brainstorm.customer.repository.CustomerRepository;
import com.brainstorm.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import com.brainstorm.customer.config.S3Config;

@Service
public class CustomerServiceImpl implements ICustomerService {
    final int MOBILE_NO = 10;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private S3Service s3Service;

    @Value("${cloud.aws.s3.access.enable}")
    private String cloudS3AccessEnabled;

    @Value("${spring.kafka.producer.enabled}")
    private String kafkaEnabled;

    @Autowired
    MessageProducer producer;


    @Override
    public CustomerDTO fetchCustomerDetails(String input) {
        Customer customer = null;
        if(input.length() == MOBILE_NO){
            String mobileNumber = input;
             customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", input));
        }
        else{
            String  customerId = input;
             customer = customerRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customer_id", input));
        }
        return CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
    }

    @Override
    public CustomerDTO fetchCustomerDetailsWithEmail(String mobileNumber, String email) {
        Customer customer = customerRepository.findByMobileNumberAndEmail(mobileNumber,email).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber & Email ", mobileNumber +"&" +email));
        return CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
    }

    @Override
    public void createNewCustomer(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        Set<Address> customerAddress = new HashSet<>();
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDTO.getMobileNumber());
        }else {
            Customer customerEntity = CustomerMapper.mapToCustomer(customerDTO, new Customer());
            if(customerDTO.getCustomerAddress() != null){
                customerEntity.setAddresses(getAddressForCustomer(customerDTO.getCustomerAddress()));

                if(!customerEntity.getAddresses().isEmpty()){
                    customerEntity.getAddresses().forEach(address -> {
                        Optional<Address> optionalAddress = addressRepository.findByAddressId(address.getAddressId());
                        if(optionalAddress.isPresent()){
                            customerAddress.add(address);
                        }
                        else {
                            addressRepository.save(address);
                            customerAddress.add(address);
                        }
                    });
                    customerEntity.setAddresses(customerAddress);
                }
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
    public void updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isEmpty()){
            throw new ResourceNotFoundException("Customer not registered", "CustomerEntity", customerDTO.getMobileNumber() );
        }
        CustomerMapper.mapToCustomer(customerDTO,optionalCustomer.get());
        customerRepository.save(optionalCustomer.get());
    }

    @Override
    public void removeCustomer(String mobileNumber) {
        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(mobileNumber);
        optionalCustomer.ifPresent(customer -> customerRepository.delete(customer));
    }

    private  Set<Address> getAddressForCustomer(Set<AddressDTO> customerAddress) {
        Set<Address> addressList = new HashSet<>();
        customerAddress.forEach(addressDTO -> {
            //check if address exist
            Optional<Address> optionalAddress = addressRepository.findByAddressId(addressDTO.getAddressId());
            if(optionalAddress.isPresent()){
                addressList.add(optionalAddress.get());
            }
            else{
                Address address = AddressMapper.mapToAddress(addressDTO);
                addressList.add(address);
            }
        });
        return addressList;
    }
}
