package com.brainstorm.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "address_id")
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private LocalDateTime createdAt;
}
