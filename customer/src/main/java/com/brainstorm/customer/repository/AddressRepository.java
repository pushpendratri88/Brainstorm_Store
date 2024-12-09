package com.brainstorm.customer.repository;

import com.brainstorm.customer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByAddressId(Long id);
    @Query(nativeQuery = true, value = "SELECT * FROM address AS a WHERE a.address_Id IN (:ids) ")
    Optional<Set<Address>> findByAddressIds(@Param("ids") Set<Long> ids);
}
