package com.brainstorm.order.controller;

import com.brainstorm.order.dto.OrderContactInfoDto;
import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.ResponseDTO;
import com.brainstorm.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private OrderContactInfoDto orderContactInfoDto;

    @PostMapping
    @RequestMapping(value = "/createOrder")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201", "Order has been created successfully"));
    }

    @GetMapping
    @RequestMapping(value = "/fetchOrder")
    public ResponseEntity<OrderDTO> fetchOrder(@RequestParam String orderId){
        OrderDTO orderDTO = orderService.fetchOrder(orderId);
        return  ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @PostMapping
    @RequestMapping(value = "/deleteOrder")
    public ResponseEntity<ResponseDTO> deleteOrder(@RequestParam String orderId){
        orderService.deleteOrder(orderId);
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("201", "Order has been deleted successfully"));
    }
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }
    @GetMapping("/contact-info")
    public ResponseEntity<OrderContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderContactInfoDto);
    }
}
