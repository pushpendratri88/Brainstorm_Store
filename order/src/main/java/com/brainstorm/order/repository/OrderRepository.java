package com.brainstorm.order.repository;


import com.brainstorm.order.entity.EcomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<EcomOrder, Long> {
    Optional<EcomOrder> findByOrderId(Long orderId);
}
