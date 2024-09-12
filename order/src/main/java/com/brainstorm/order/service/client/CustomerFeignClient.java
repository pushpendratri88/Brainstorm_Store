package com.brainstorm.order.service.client;

import com.brainstorm.order.dto.CustomerDTO;
import com.brainstorm.order.fallback.CustomerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="customer",fallback = CustomerFallback.class)
public interface CustomerFeignClient {

    @GetMapping(value = "/api/customers/fetchCustomerDetails")
    ResponseEntity<CustomerDTO> fetchCustomerDetails(@RequestParam("input") String input);

    @GetMapping(value = "/api/customers/fetchCustomerDetailsWithEmail")
    ResponseEntity<CustomerDTO> fetchCustomerDetailsWithMobileAndEmail(@RequestParam Long mobileNumber, String email);

}
