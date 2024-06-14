package com.brainstorm.order.repository;

import com.brainstorm.order.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderEntriesRepository extends JpaRepository<OrderEntry, Long> {
    Optional<OrderEntry> findByOrderEntryId(Long orderEntryId);
}
