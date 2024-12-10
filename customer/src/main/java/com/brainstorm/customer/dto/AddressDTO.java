package com.brainstorm.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private LocalDateTime createdAt;
}
