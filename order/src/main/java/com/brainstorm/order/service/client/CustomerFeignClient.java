package com.brainstorm.order.service.client;

import com.brainstorm.order.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("customer")
public interface CustomerFeignClient {

    @GetMapping(value = "/api/customers/fetchCustomerDetails")
    public ResponseEntity<CustomerDTO> fetchCustomerDetails(@RequestParam String input);

    @GetMapping(value = "/api/customers/fetchCustomerDetailsWithEmail")
    public ResponseEntity<CustomerDTO> fetchCustomerDetailsWithMobileAndEmail(@RequestParam Long mobileNumber, String email);

}
