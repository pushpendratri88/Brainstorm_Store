package com.brainstorm.customer.dto;

import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.Set;

public class CustomerDTO {
    private String id;
    private String name;
    private String email;
    private String mobileNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private Set<AddressDTO> customerAddress;

    private MultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<AddressDTO> getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Set<AddressDTO> customerAddress) {
        this.customerAddress = customerAddress;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
