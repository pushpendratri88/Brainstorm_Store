package com.brainstorm.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse {
    @Id
    @GeneratedValue(generator = "UUID")
    private long id;

    @Column
    private int quantity;

    @Column
    private String item;

}
