package com.brainstorm.customer.controller;

import com.brainstorm.customer.CustomerConstants;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.dto.ResponseDTO;
import com.brainstorm.customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.createNewCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @GetMapping(value = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDTO> fetchCustomerDetails(@RequestParam String mobileNumber){
        CustomerDTO customerDTO = customerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @GetMapping(value = "/fetchCustomerDetailsWithEmail")
    public ResponseEntity<CustomerDTO> fetchCustomerDetailsWithMobileAndEmail(@PathVariable String mobileNumber, String email){
        CustomerDTO customerDTO = customerService.fetchCustomerDetailsWithEmail(mobileNumber,email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(CustomerConstants.STATUS_201, "Customer is updated successfully"));
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<ResponseDTO> removeCustomer(@RequestParam String mobileNumber){
        customerService.removeCustomer(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(CustomerConstants.STATUS_201, "Customer is removed successfully"));
    }
}
