package com.brainstorm.customer.controller;

import com.brainstorm.customer.CustomerConstants;
import com.brainstorm.customer.dto.CustomerContactInfoDto;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.dto.ResponseDTO;
import com.brainstorm.customer.mapper.CustomerMapper;
import com.brainstorm.customer.model.CustomerForm;
import com.brainstorm.customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerContactInfoDto customerContactInfoDto;

    @GetMapping(value = "/newCustomer")
    public String newCustomer(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customer";
    }

    @PostMapping(value = "/newCustomer",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String newCustomer(@ModelAttribute CustomerForm customerForm,  Model model){
        CustomerDTO customerDTO = CustomerMapper.customerFormToCustomerDTO(customerForm);
        customerService.createNewCustomer(customerDTO);
        model.addAttribute("response", new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
        return "success"; // Return a success view
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/customerRegistration")
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.createNewCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/getCustomer")
    public ResponseEntity<CustomerDTO> getCustomer(@Valid @RequestParam String input){
        CustomerDTO customerDTO = customerService.getCustomer(input);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/getCustomers")
    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        List<CustomerDTO> customerDTOList = customerService.getCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customerDTOList);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/getCustomerByEmail")
    public ResponseEntity<CustomerDTO> getCustomerByMobileAndEmail(@Valid @RequestParam Long mobileNumber, String email){
        CustomerDTO customerDTO = customerService.getCustomerByEmail(mobileNumber,email);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(value = "/updateCustomerDetails")
    public ResponseEntity<ResponseDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(CustomerConstants.STATUS_201, "Customer is updated successfully"));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "/removeCustomer")
    public ResponseEntity<ResponseDTO> removeCustomer(@Valid @RequestParam Long mobileNumber){
        customerService.removeCustomer(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(CustomerConstants.STATUS_201, "Customer is removed successfully"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contact-info")
    public ResponseEntity<CustomerContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerContactInfoDto);
    }
}
