package com.brainstorm.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @GenericGenerator(name = "customer_id", strategy = "com.brainstorm.customer.generator.CustomerIdGenerator")
//    @GeneratedValue(generator = "customer_id")
    @Column(name = "customer_id")
    private String customerId;
    private String name;
    private String email;
    @Column(name = "mobile_number")
    private Long mobileNumber;
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "photo")
    private String photo;
    @OneToMany
    @JoinColumn(name = "addressId_fk")
    private Set<Address> addresses;
}
