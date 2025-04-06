package com.brainstorm.customer.repository;

import com.brainstorm.customer.entity.Customer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM Customer c WHERE c.customerId = :id")
    Optional<Customer> findByCustomerId(@Param("id") String id); // Spring Data JPA will safely bind the parameter email, preventing SQL injection.

    @Query("SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber")
    Optional<Customer> findByMobileNumber(@Param("mobileNumber") Long  mobileNumber);

    @Query("SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber and c.email = :email")
    Optional<Customer> findByMobileNumberAndEmail(@Param("mobileNumber") Long mobileNumber, @Param("email")String email);

}
