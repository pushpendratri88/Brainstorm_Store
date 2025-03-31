package com.brainstorm.order.controller;

import com.brainstorm.order.dto.OrderContactInfoDto;
import com.brainstorm.order.dto.OrderDTO;
import com.brainstorm.order.dto.ResponseDTO;
import com.brainstorm.order.service.IOrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private OrderContactInfoDto orderContactInfoDto;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/createOrder")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO)  {
        orderService.createOrder(orderDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201", "Order created successfully"));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/fetchOrder")
    public ResponseEntity<OrderDTO> fetchOrder(@RequestParam @NotNull @Min(1) Long orderId){
        OrderDTO orderDTO = orderService.fetchOrder(orderId);
        return  ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/deleteOrder")
    public ResponseEntity<ResponseDTO> deleteOrder(@RequestParam  @NotNull @Min(1) Long orderId){
        orderService.deleteOrder(orderId);
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("201", "Order has been deleted successfully"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contact-info")
    public ResponseEntity<OrderContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderContactInfoDto);
    }
}
