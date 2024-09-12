package com.brainstorm.order.fallback;

import com.brainstorm.order.dto.CustomerDTO;
import com.brainstorm.order.service.client.CustomerFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerFallback implements CustomerFeignClient {
    @Override
    public ResponseEntity<CustomerDTO> fetchCustomerDetails(String input) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDTO> fetchCustomerDetailsWithMobileAndEmail(Long mobileNumber, String email) {
        return null;
    }
}
