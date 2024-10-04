package com.brainstorm.stock.repository;

import com.brainstorm.stock.entity.WareHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<WareHouse, Long> {
    Iterable<WareHouse> findByItem(Long id);
}
