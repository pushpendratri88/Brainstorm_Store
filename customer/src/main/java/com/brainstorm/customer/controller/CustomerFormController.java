package com.brainstorm.customer.controller;

import com.brainstorm.customer.CustomerConstants;
import com.brainstorm.customer.dto.CustomerDTO;
import com.brainstorm.customer.dto.ResponseDTO;
import com.brainstorm.customer.mapper.CustomerMapper;
import com.brainstorm.customer.model.CustomerForm;
import com.brainstorm.customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
@Validated
public class CustomerFormController {
    @Autowired
    ICustomerService customerService;

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
}
