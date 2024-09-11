package com.brainstorm.customer.repository;

import com.brainstorm.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByCustomerId(String id);
    Optional<Customer> findByMobileNumber(Long  mobileNumber);

    Optional<Customer> findByMobileNumberAndEmail(Long mobileNumber,String email);

}
