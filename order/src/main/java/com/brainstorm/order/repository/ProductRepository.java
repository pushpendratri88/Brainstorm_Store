package com.brainstorm.order.repository;

import com.brainstorm.order.entity.EcomProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<EcomProduct , Long> {
    Optional<EcomProduct> findByProductCode(Long productCode);
}

