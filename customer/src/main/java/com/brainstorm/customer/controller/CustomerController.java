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
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.createNewCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @GetMapping(value = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDTO> fetchCustomerDetails(@RequestParam String input){
        CustomerDTO customerDTO = customerService.fetchCustomerDetails(input);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @GetMapping(value = "/fetchCustomerDetailsWithEmail")
    public ResponseEntity<CustomerDTO> fetchCustomerDetailsWithMobileAndEmail(@RequestParam String mobileNumber, String email){
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


    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }
    @GetMapping("/contact-info")
    public ResponseEntity<CustomerContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerContactInfoDto);
    }
}
