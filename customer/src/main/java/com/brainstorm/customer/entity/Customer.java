package com.brainstorm.customer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
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

    @Column(name = "photo")
    private String photo;

    @ManyToMany
    @JoinTable(name="customer_address", joinColumns = @JoinColumn(name="customer_id", referencedColumnName = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    )
    private Set<Address> addresses;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
