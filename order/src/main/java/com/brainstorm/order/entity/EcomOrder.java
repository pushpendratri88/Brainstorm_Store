package com.brainstorm.order.entity;

import com.brainstorm.order.dto.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ECOM_ORDER")
public class EcomOrder extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "order_id", strategy = "com.brainstorm.order.generator.OrderIdGenerator")
//    @GeneratedValue(generator = "order_id")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "amount")
    private double amount;

    @OneToMany
    @JoinColumn(name = "order_fk")
    private List<OrderEntry> orderEntryList;

}
