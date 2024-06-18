package com.brainstorm.order.controller;

import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.ResponseDTO;
import com.brainstorm.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping
    @RequestMapping(value = "/createOrder")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201", "Order has been created successfully"));
    }

    @GetMapping
    @RequestMapping(value = "/fetchOrder")
    public ResponseEntity<OrderDTO> fetchOrder(@RequestBody Long orderId){
        OrderDTO orderDTO = orderService.fetchOrder(orderId);

        return  ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }


}
