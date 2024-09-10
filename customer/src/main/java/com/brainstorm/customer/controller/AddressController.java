package com.brainstorm.customer.controller;

import com.brainstorm.customer.AddressConstants;
import com.brainstorm.customer.CustomerConstants;
import com.brainstorm.customer.dto.AddressDTO;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.dto.ResponseDTO;
import com.brainstorm.customer.service.IAddressService;
import com.brainstorm.customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AddressController {
    @Autowired
    IAddressService addressService;

    @PostMapping(value = "/createAddress")
    public ResponseEntity<ResponseDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        addressService.createNewAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(AddressConstants.STATUS_201, AddressConstants.MESSAGE_201));
    }

    @GetMapping(value = "/fetchAddress")
    public ResponseEntity<AddressDTO> fetchCustomerDetails(@RequestParam Long addressId){
        AddressDTO addressDTO = addressService.fetchAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
    }

    @PostMapping(value = "/updateAddress")
    public ResponseEntity<ResponseDTO> updateCustomer(@Valid @RequestBody AddressDTO addressDTO){
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(CustomerConstants.STATUS_201, "Address is updated successfully"));
    }
}
